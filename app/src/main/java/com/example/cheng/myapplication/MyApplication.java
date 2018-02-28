package com.example.cheng.myapplication;

import android.app.Application;
import android.content.Context;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by biao.cheng on 2017/9/14.
 */

public class MyApplication extends TinkerApplication {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public MyApplication() {
        //com.example.cheng.myapplication.SampleApplicationLike
        super(ShareConstants.TINKER_ENABLE_ALL, "com.example.cheng.myapplication.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
