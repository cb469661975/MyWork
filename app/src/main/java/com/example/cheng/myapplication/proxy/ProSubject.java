package com.example.cheng.myapplication.proxy;


import android.util.Log;

/**
 * Created by chengbiao on 2018/4/23.
 */
public class ProSubject implements Subject{
    @Override
    public String  doWhat(String s) {
        Log.i("proxy_be_invoke","doWhat="+s);
        return "return + "+s;
    }
}
