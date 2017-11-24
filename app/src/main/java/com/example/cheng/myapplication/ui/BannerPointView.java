package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by biao.cheng on 2017/11/14.
 */

public class BannerPointView extends View {

    private Paint mIndicatorPaint;
    private Paint mBgPaint;

    private int mHeight;
    private int mWidth;
    private int mIndicatorWidth;
    private int count = 3;

    private int position = 0;

    public BannerPointView(Context context) {
        this(context, null, 0);
    }

    public BannerPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth() ;
        mWidth = getMeasuredWidth() - dp2px(2);
        mIndicatorWidth = mWidth / count;
    }

    public BannerPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeight = dp2px(10);
        mIndicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorPaint.setStrokeWidth(mHeight);
        mIndicatorPaint.setColor(Color.parseColor("#994c4c4c"));
        mIndicatorPaint.setStrokeCap(Paint.Cap.ROUND);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStrokeWidth(mHeight);
        mBgPaint.setStrokeCap(Paint.Cap.ROUND);
        mBgPaint.setColor(Color.parseColor("#d2d2d2"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(dp2px(5), mHeight / 2, mWidth, mHeight / 2, mBgPaint);
        canvas.drawLine(dp2px(5) + position * mIndicatorWidth, mHeight / 2, (position + 1) * mIndicatorWidth, mHeight / 2, mIndicatorPaint);
    }

    private int dp2px(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    public void setSelctPosiiton(int position) {
        if (position > count - 1) {
            this.position = 0;
        } else {
            this.position = position;
        }
        invalidate();
    }

    public int getPosition() {
        return position;
    }

    public void setCount(int count) {
        this.count = count;
        if (count <= 1) {
            setVisibility(GONE);
        }
    }

    public void retSetCount(int count) {
        setCount(count);
        mIndicatorWidth = mWidth / count;
        invalidate();
    }

    public int getCount() {
        return count;
    }
}
