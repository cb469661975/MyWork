package com.example.cheng.myapplication.net;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by biao.cheng on 2017/9/25.
 */

public class HttpUtil implements RequestCallBack {
    private OkHttpClient mOkhttp;
    private static HttpUtil instance;

    private HttpUtil() {
        mOkhttp = new OkHttpClient();
        mOkhttp.newBuilder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

    }
    public static HttpUtil getInstance() {
        if (instance == null) {
                synchronized (HttpUtil.class){
                    instance=new HttpUtil();
                }
        }
        return instance;
    }

    @Override
    public void post(String url, Map params, final IRequestCallBack iRequestCallBack) {
        FormBody requestBody=new FormBody.Builder()
                .build();
        Request request = new Request.Builder().url(url).post(requestBody).build();

        mOkhttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iRequestCallBack.onSuccess(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iRequestCallBack.onSuccess(response.toString());
            }
        });


    }

    @Override
    public void get(String url, Map params, final IRequestCallBack iRequestCallBack) {
        Request request = new Request.Builder().url(url).get().build();

        mOkhttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iRequestCallBack.onSuccess(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iRequestCallBack.onSuccess(response.toString());
            }
        });
    }
    
}

