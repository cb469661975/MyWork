package com.example.cheng.myapplication;

import android.os.Binder;
import android.os.IBinder;

/**
 * Created by biao.cheng on 2017/9/21.
 */

public    interface MyTestInterface {

    public static abstract class  Stub implements MyTestInterface {


    }



    public void toast();
}
