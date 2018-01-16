package com.example.cheng.myapplication.kotlin;

/**
 * Created by chengbiao on 2018/1/4.
 */

public class SingleInstance {

    private static SingleInstance _insance;

    public static SingleInstance getInstance() {

        if (_insance == null) {
            synchronized (_insance) {
                if (_insance == null) {
                    _insance = new SingleInstance();
                }

            }
        }
        return _insance;
    }
}
