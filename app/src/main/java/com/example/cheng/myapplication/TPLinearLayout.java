package com.example.cheng.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

/**
 * Created by biao.cheng on 2017/10/20.
 */

public class TPLinearLayout extends LinearLayout {
    private int w,h;

    private int nums=10;
    private int itemHeight =dp2px(50);

    public TPLinearLayout(Context context) {
        super(context);
        init();
    }

    public TPLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TPLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w=w;this.h=h;
        setItemNum(nums);
    }


    public void setItemNum(int num) {
        this.removeAllViews();
        Log.i("aaaa", "addView" + num);
        int count = 0;
        if(num%3==0){
            count = num/3;
        }else{
            count = num/3+1;
        }
        for (int i = 0; i < count; i++) {
            TLinearLayout tl = new TLinearLayout(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(w, itemHeight);
            if(count==count-1&&num%3!=0){
                tl.setItemNum(num%3);
            }else{
                tl.setItemNum(3);
            }
            this.addView(tl, lp);
        }
    }

    private int dp2px(int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getContext().getResources().getDisplayMetrics());
    }

}
