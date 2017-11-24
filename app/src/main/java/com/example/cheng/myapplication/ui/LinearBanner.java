package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.cheng.myapplication.R;

/**
 * Created by biao.cheng on 2017/11/15.
 */

public    class LinearBanner extends LinearLayout {

    public LinearBanner(Context context) {
        super(context);init ();
    }

    public LinearBanner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init ();
    }

    public LinearBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init ();
    }

    public void init (){
        View.inflate(getContext(), R.layout.item,this);
    }
}
