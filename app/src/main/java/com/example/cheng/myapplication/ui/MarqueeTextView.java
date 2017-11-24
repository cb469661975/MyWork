package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by biao.cheng on 2017/11/4.
 */

public class MarqueeTextView extends TextView {

    public MarqueeTextView(Context context) {
        super(context);
        initView();
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        super.setClickable(false);
        super.setFocusable(true);
        super.setFocusableInTouchMode(true);
        super.setSingleLine();
        super.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        super.setMarqueeRepeatLimit(1);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
