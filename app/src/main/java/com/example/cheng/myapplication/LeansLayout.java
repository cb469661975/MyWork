package com.example.cheng.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by cheng on 2017/9/7.
 */

public class LeansLayout extends LinearLayout
{
    private int mHeight;
    private int mWidth;


    public LeansLayout(Context context) {
        super(context);
        init();
    }

    public LeansLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeansLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setOrientation(LinearLayout.VERTICAL);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    public void addChildView(View child) {
        if (child == null) {
            throw new IllegalArgumentException("Cannot add a null child view to a ViewGroup");
        }else{
            Context ctx=MyApplication.mContext;
            WindowManager  W = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        }
        Log.i("addCHildView",mWidth+"=="+mHeight);

        LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if(lp!=null){
            lp.leftMargin=getChildCount()*100;
            child.setLayoutParams(lp);
            Log.i("addCHildViewq",child.getMeasuredHeight()+"=="+child.getWidth());

        }
        addView(child);
    }
}
