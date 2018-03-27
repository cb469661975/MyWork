package com.example.cheng.myapplication.net;

import android.content.Context;

import com.example.cheng.myapplication.rxjava.ApiService;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chengbiao on 2018/3/7.
 */

public class NetUtil {

    private static NetUtil instance;
    private final Retrofit retrofit;

    private NetUtil() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava
//                .client()
                .build();
    }

    public static NetUtil getInstance() {
        synchronized (NetUtil.class) {
            if (instance == null) {
                instance = new NetUtil();
            }
        }
        return instance;
    }


    public ApiService getObserable(Class<ApiService> apiService) {
        return retrofit.create(apiService);
    }

}
