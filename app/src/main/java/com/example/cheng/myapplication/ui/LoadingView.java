package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lijianjian on 17/2/21.
 */

public class LoadingView extends View {

    private int mWidth;
    private int mHeight;
    private int mWidthRect;
    private int mHeightRect;
    private Paint mRectPaint;
    private Rect mRect;

    private int mPos = 0;
    private String[] mColors = {"#ffffff", "#bbffffff", "#88ffffff", "#55ffffff", "#33ffffff","#11ffffff"};

    public LoadingView(Context context) {
        this(context, null);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {
            mWidth = 200;
        } else {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
            mWidth = Math.min(mWidth, mHeight);
        }

        mWidthRect = mWidth / 12;
        mHeightRect =4 * mWidthRect;
        setMeasuredDimension(mWidth, mWidth);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < 8; i++) {
            if (i - mPos >= 3) {
                mRectPaint.setColor(Color.parseColor(mColors[5]));
            } else if (i - mPos >= 0 && i - mPos < 4) {
                mRectPaint.setColor(Color.parseColor(mColors[i - mPos]));
            } else if (i - mPos >= -4 && i - mPos < 0) {
                mRectPaint.setColor(Color.parseColor(mColors[5]));
            } else if (i - mPos >= -7 && i - mPos < -3) {
                mRectPaint.setColor(Color.parseColor(mColors[8 + i - mPos]));
            }

//            canvas.drawRect(mRect, mRectPaint);
              canvas.drawRoundRect((mWidth - mWidthRect) / 2, 0, (mWidth + mWidthRect) / 2, mHeightRect,4,4,mRectPaint);
            canvas.rotate(45, mWidth / 2, mWidth / 2);
        }

        mPos++;
        if (mPos > 6) {
            mPos = 0;
        }

        postInvalidateDelayed(100);
    }
}
