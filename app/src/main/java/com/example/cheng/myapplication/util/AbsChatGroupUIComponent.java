package com.example.cheng.myapplication.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by yuanchao on 2017/7/3.
 */

public abstract class AbsChatGroupUIComponent extends RelativeLayout {

    protected ChatGroupUIDelegate mUIDelegate;

    public AbsChatGroupUIComponent(Context context) {
        super(context);
        initView(context);
    }

    public AbsChatGroupUIComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AbsChatGroupUIComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setDelegate(ChatGroupUIDelegate delegate) {
        mUIDelegate = delegate;
    }

    protected abstract void initView(Context context);

    public abstract void updateView();

    public abstract void destroy();

}

