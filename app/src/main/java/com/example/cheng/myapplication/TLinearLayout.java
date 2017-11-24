package com.example.cheng.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by biao.cheng on 2017/10/20.
 */

public class TLinearLayout extends LinearLayout {

    private int childNum = 3;

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    private int w;

    public TLinearLayout(Context context) {
        super(context);
        init();
    }

    public TLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setOrientation(LinearLayout.HORIZONTAL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        Log.i("aaa", "onSizeChanged=" + getMeasuredWidth() + ",w=" + w);
//        setItemNum(4);
    }

    public void setItemNum(int num) {
        this.removeAllViews();
        Log.i("aaaa", "addView" + num);
        for (int i = 0; i < num; i++) {
            TextView tv = new TextView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(getwidth() / num, ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setText("num" + i);
            tv.setTextColor(Color.BLUE);
            tv.setGravity(Gravity.CENTER);
            this.addView(tv, lp);
        }
    }

    private int getwidth() {
        return w;
    }

}
