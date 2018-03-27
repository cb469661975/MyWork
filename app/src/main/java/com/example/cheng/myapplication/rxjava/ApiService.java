package com.example.cheng.myapplication.rxjava;

import com.example.cheng.myapplication.model.RxTestModel;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chengbiao on 2018/3/6.
 */

public interface ApiService {
    @GET("1historyWeather/province?key=6dcaaeea46f34369b8cdd0bda5462d6c")
    Observable<RxTestModel> getNetData();

    @POST("/name")
    Observable<RxTestModel> getNet2Data(@Field("name") String name);
}
