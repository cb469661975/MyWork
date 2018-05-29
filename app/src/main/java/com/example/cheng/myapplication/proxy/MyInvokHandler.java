package com.example.cheng.myapplication.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chengbiao on 2018/4/23.
 */
public class MyInvokHandler implements InvocationHandler {
    private Object delegate;


    public MyInvokHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(delegate, args);
        Log.i("invoke", "invoke=" + invoke);
        return null;
    }
}
