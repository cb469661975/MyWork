package com.example.cheng.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.cheng.myapplication.aidl.IAidlTestInterface;

/**
 * Created by biao.cheng on 2017/9/13.
 */

public    class TestService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ISlefBind();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    class ISlefBind extends IAidlTestInterface.Stub {
        @Override
        public void showToast(String testStr) throws RemoteException {
            Log.i("showToast","show++"+testStr);
        }
    }

}

