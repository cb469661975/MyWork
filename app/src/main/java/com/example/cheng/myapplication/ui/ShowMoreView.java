package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ShowMoreView extends LinearLayout {

    public ShowMoreView(Context context) {
        super(context);
    }

    public ShowMoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowMoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        invalidate();
    }
}
