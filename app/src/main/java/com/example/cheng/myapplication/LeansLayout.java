package com.example.cheng.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by cheng on 2017/9/7.
 */
public class LeansLayout extends FrameLayout {
    /**
     * 默认是宽度/长度
     */
    private float mRadioWH = 0;
    private float DEFAULT_RADIOWH = 0;

    private String TAG = "LeansLayout";

    public LeansLayout(Context context) {
        super(context);
        init(context, null);
    }

    public LeansLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LeansLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LeansLayout);
        mRadioWH = typedArray.getFloat(R.styleable.LeansLayout_roateWH, mRadioWH);
        typedArray.recycle();
    }

    /**
     * 获得宽高的同时，取得宽高比
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (DEFAULT_RADIOWH > 0) {
        } else {
            DEFAULT_RADIOWH = w * 1.0f / h;
        }
        if (mRadioWH > 0) {
        } else {
            mRadioWH = DEFAULT_RADIOWH;
        }
        Log.i(TAG, "roateWH=" + mRadioWH);
    }

    /**
     * 对子View进行测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }
    /***
     * 手动帮子类布局成倾斜的
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int totalheight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            totalheight = totalheight + height;
            //获取子View的margin
            FrameLayout.LayoutParams frameLayoutParams = (LayoutParams) childView.getLayoutParams();

            int left = frameLayoutParams.leftMargin;
            int top = frameLayoutParams.topMargin;
            int bottom = frameLayoutParams.bottomMargin;
            //保证第一个View的位置不显示到布局外面
            if (i != 0) {
                int yPoint = totalheight - height / 2;
                left = left + (int) (mRadioWH * yPoint - width / 2);
                top = top + totalheight - height;
            }
            //布局  默认的左上右下
            childView.layout(left, top, left + width, top + height + bottom);
        }
    }
}
