package com.example.cheng.myapplication.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by biao.cheng on 2017/10/24.
 */

public class PointDraw3View extends View {

    private int mWidth=dp2px(2);

    private Paint mPaint;
    private ValueAnimator animator;
    private int currentValue;

    private int mViewHeight;

    public PointDraw3View(Context context) {
        super(context);
        initView();
    }
    public PointDraw3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PointDraw3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(dp2px(mWidth));
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(currentValue==0||currentValue==1||currentValue==2){
            canvas.drawCircle(mWidth,mViewHeight*2/3,mWidth,mPaint);
            if(currentValue==1||currentValue==2){
                canvas.drawCircle(mWidth*4,mViewHeight*2/3,mWidth,mPaint);
                if(currentValue==2){
                    canvas.drawCircle(mWidth*7,mViewHeight*2/3,mWidth,mPaint);
                }
            }
        }
    }

    private int dp2px(int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        showPoint();
    }

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
//                    Log.i("currentvalue",current+"--");
                    currentValue=current;
                    invalidate();
                }
            }
        });
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
