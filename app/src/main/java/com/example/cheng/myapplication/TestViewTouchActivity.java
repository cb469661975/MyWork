package com.example.cheng.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cheng.myapplication.ui.pathanim.ViewPath;
import com.example.cheng.myapplication.ui.pathanim.ViewPathEvaluator;
import com.example.cheng.myapplication.ui.pathanim.ViewPoint;

/**
 * Created by chengbiao on 2018/2/27.
 */

public class TestViewTouchActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    private ImageView imageView;
    private TextView header_tv_msg;

    private float x = 0;
    private float y = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_test_touchview);
        imageView = findViewById(R.id.imageView);
        header_tv_msg = findViewById(R.id.header_tv_msg);
        findViewById(R.id.rl_parent).setOnTouchListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if (x == 0 || y == 0) {
            x= view.getX();
            y= view.getX();
        }
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator rotateAnim  = ObjectAnimator.ofFloat(imageView , "rotation" , -30 , 0);
        rotateAnim.setDuration(14000);
        ViewPath path = new ViewPath(); //偏移坐标
        path.moveTo(0, 0);
        float x1 = (header_tv_msg.getX() + x) / 2;
        float y1 = (header_tv_msg.getY() - y) / 2 + dp2px(10);
        Log.i("ViewPath--", "y1:" + y1 + ",x1:" + x1);
        Log.i("ViewPath--header_tv_msg", "getY:" + header_tv_msg.getY() + ",getX:" + header_tv_msg.getX());
        Log.i("ViewPath--view", "getY:" + y + ",getX:" + x);
        path.quadTo(x1+dp2px(30), -(header_tv_msg.getY()+dp2px(10)), header_tv_msg.getX(), -header_tv_msg.getY());
//        path.lineTo(0, view.getY() - header_tv_msg.getY());
        ObjectAnimator anim = ObjectAnimator.ofObject(this, "imageLoc", new ViewPathEvaluator(), path.getPoints().toArray());
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(14000);
        set.playTogether(rotateAnim,anim);
        set.start();
    }


    public void setImageLoc(ViewPoint newLoc) {
        imageView.setTranslationX(newLoc.getX());
        imageView.setTranslationY(newLoc.getY());
    }

    private int dp2px(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
}
