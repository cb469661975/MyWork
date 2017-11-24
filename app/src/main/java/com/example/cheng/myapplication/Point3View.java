package com.example.cheng.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created by biao.cheng on 2017/10/24.
 */

public    class Point3View extends TextView {
    private ValueAnimator animator;

    public Point3View(Context context) {
        super(context);
        init();
    }
    public Point3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init();
    }

    public Point3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }

    private void init() {
        this.setText("...");
        this.setTextColor(Color.WHITE);
        this.setTextSize(20);
    }

    private int currentValue;

    public void showPoint(){
         animator = ValueAnimator.ofInt(0,3);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2500);
        animator.setRepeatCount(100);
        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int current = (int) valueAnimator.getAnimatedValue();
                if(current==currentValue){
                    return;
                }else {
                    currentValue=current;
                }
                Log.i("testView",current+"====");
                switch (current){
                    case 0:
                        setText(".");
                        break;
                    case 1:
                        setText("..");
                        break;
                    case 2:
                        setText("...");
                        break;
                }
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        showPoint();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(animator!=null){
            animator.setRepeatCount(0);
            animator.cancel();
            animator.cancel();
        }
    }
}
