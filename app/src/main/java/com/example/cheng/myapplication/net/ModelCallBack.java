package com.example.cheng.myapplication.net;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by biao.cheng on 2017/9/25.
 */

public abstract class ModelCallBack<T> implements IRequestCallBack {

    @Override
    public void onSuccess(String s) {
        onSuccess(new Gson().fromJson(s, getType(getClass())));
    }

    private Class<? extends T> getType(Object object) {
        Type genericSuperclass = object.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        return (Class<? extends T>) actualTypeArguments[0];
    }

    public abstract void onSuccess(T t);
}
