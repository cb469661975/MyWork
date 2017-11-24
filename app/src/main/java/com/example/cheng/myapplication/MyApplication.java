package com.example.cheng.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by biao.cheng on 2017/9/14.
 */

public  class MyApplication extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;

    }

}
