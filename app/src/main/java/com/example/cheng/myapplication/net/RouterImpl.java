package com.example.cheng.myapplication.net;

import java.lang.annotation.Annotation;

/**
 * Created by chengbiao on 2018/3/22.
 */

public class RouterImpl implements Router{

    public int test() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
