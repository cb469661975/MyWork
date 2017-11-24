package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheng.myapplication.R;

/**
 * Created by biao.cheng on 2017/11/2.
 */

public class WorldNoticeView extends LinearLayout implements View.OnClickListener {

    private int rightIn = 1000;
    private int leftOut = 500;

    private int stayLeft = 4000;
    TextView tv_around_see;
    ImageView iv_left_icon;
    MarqueeText  maquneview;


    private boolean isShowing=false;


    public WorldNoticeView(Context context) {
        super(context);
        initView(context);
    }

    public WorldNoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public WorldNoticeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.layout_world_notice, this);
        tv_around_see = (TextView) findViewById(R.id.tv_around_see);
        iv_left_icon = (ImageView) findViewById(R.id.iv_left_icon);
        maquneview = (MarqueeText) findViewById(R.id.maquneview);
        maquneview.setText("aaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccc");
        tv_around_see.setOnClickListener(this);
        iv_left_icon.setOnClickListener(this);
    }

    private  Runnable animationEndRunnable =new Runnable() {
        @Override
        public void run() {
            translateOut();
        }
    };


    private void transLateIn() {
        Animation rightInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bibi_world_notice_right_in);
        this.startAnimation(rightInAnim);

        maquneview.setText(Html.fromHtml("<font color= '#fff165'>" + "我" +
                "</font>给"+  "<font color= '#fff165'>" + "你" + "</font>送了"
                +"<font color= '#fff165'>" +2 + "</font>个"
                +"<font color= '#fff165'>" + "大船"+ "</font>"));

        rightInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                maquneview.startScroll();
                postDelayed(animationEndRunnable,4000);
                WorldNoticeView.this.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void show(){
        if(!isShowing){
            isShowing=true;
            transLateIn();
        }else{
            Toast.makeText(getContext(),"我在dongle",Toast.LENGTH_LONG).show();
        }
    }


    private void translateOut() {

        Animation leftOutAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bibi_world_notice_left_out);
        this.startAnimation(leftOutAnim);
        leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                maquneview.stopScroll();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                WorldNoticeView.this.setVisibility(INVISIBLE);
                isShowing=false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_left_icon:
                translateOut();
                break;
            case R.id.tv_around_see:
                transLateIn();
                break;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
