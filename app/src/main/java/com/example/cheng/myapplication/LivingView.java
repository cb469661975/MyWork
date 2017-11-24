package com.example.cheng.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by biao.cheng on 2017/9/28.
 */

public class LivingView extends View {

    Paint mPaint;
    public LivingView(Context context) {
        super(context);
        init();
    }

    public LivingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LivingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(dp2px(1));
        canvas.drawLine(getPaddingLeft(),getPaddingTop(),getPaddingLeft(),dp2px(13),mPaint);
        canvas.drawLine(getPaddingLeft()+dp2px(4),getPaddingTop()+dp2px(6),getPaddingLeft()+dp2px(4),dp2px(13),mPaint);
        canvas.drawLine(getPaddingLeft()+dp2px(8),getPaddingTop()+dp2px(3),getPaddingLeft()+dp2px(8),dp2px(13),mPaint);
        canvas.drawLine(getPaddingLeft()+dp2px(12),getPaddingTop()+dp2px(5),getPaddingLeft()+dp2px(12),dp2px(13),mPaint);

    }
    private int dp2px(int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }

}
