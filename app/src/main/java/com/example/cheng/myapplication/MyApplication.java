package com.example.cheng.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.cheng.myapplication.jsbridge.vassonic.HostSonicRuntime;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;

/**
 * Created by biao.cheng on 2017/9/14.
 */

public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new HostSonicRuntime(this), new SonicConfig.Builder().build());
        }
    }
}
