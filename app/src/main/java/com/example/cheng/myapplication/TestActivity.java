package com.example.cheng.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cheng.myapplication.model.RxTestModel;
import com.example.cheng.myapplication.net.NetUtil;
import com.example.cheng.myapplication.rxjava.ApiService;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chengbiao on 2017/12/8.
 */

public class TestActivity extends Activity {


    private CompositeSubscription subscriptions;
    private ProgressDialog progressDialog;

    private TextView tv_clock;
    private int retryCount;
    private final int maxRetries = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        tv_clock = (TextView) findViewById(R.id.tv_clock);
        subscriptions = new CompositeSubscription();
//        initRxShow();
//        initClock();
        initRetry();
        initZip();
        initC();
    }



    private void initC() {


    }

    private void initZip() {


//        Observable.zip(NetUtil.getInstance().getObserable(ApiService.class).getNetData(), NetUtil.getInstance().getObserable(ApiService.class).getNetData(), new Func2<RxTestModel, RxTestModel, Object>() {
//            @Override
//            public RxTestModel call(RxTestModel rxTestModel) {
//                return rxTestModel;
//            }
//        });

    }

    private void initRetry() {
        NetUtil.getInstance().getObserable(ApiService.class).getNetData().retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable
                        .flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                if (throwable instanceof HttpException) {
                                    if (++retryCount <= maxRetries) {
                                        // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                                        Log.i("retryTest", "get error, it will try after " + " millisecond, retry count " + retryCount);
                                        return Observable.timer(3,
                                                TimeUnit.SECONDS);
                                    }
                                }
                                return Observable.error(throwable);
                            }
                        });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RxTestModel>() {
                    @Override
                    public void onCompleted() {
                        Log.i("retryTest", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("retryTest", "onError==" + e.toString());
                    }

                    @Override
                    public void onNext(RxTestModel rxTestModel) {
                        Log.i("retryTest", "onError==" + rxTestModel.toString());
                    }
                });

    }

    private void initClock() {
        Subscription subscribe =
//                rx.Observable.interval(1, TimeUnit.SECONDS)
                rx.Observable.interval(1, 2, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Long>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Long aLong) {
                                tv_clock.setText("aLong=" + aLong);
                                Log.i("RxTestModel", "Clock=----=");
                            }
                        });
        subscriptions.add(subscribe);
    }

    private void initRxShow() {
        Subscription subscribe = NetUtil.getInstance().getObserable(ApiService.class).getNetData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RxTestModel>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (progressDialog == null) {
                            progressDialog = new ProgressDialog(TestActivity.this, ProgressDialog.STYLE_SPINNER);
                            progressDialog.setTitle("加载中");
                            progressDialog.setMessage("加载。。。。。。");
                        }
                        progressDialog.show();
                    }

                    @Override
                    public void onCompleted() {
                        Log.i("RxTestModel", "OK");
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RxTestModel", "error||" + e.toString());
                    }

                    @Override
                    public void onNext(RxTestModel rxTestModel) {
                        Log.i("RxTestModel", rxTestModel.getResult().size() + "==");
                    }
                });
        subscriptions.add(subscribe);

    }

    public static void startActiv(Context context) {

        Intent intent = new Intent(context, TestActivity.class);

    }


    public void a(View v) {
        v.bringToFront();
    }

    public void b(View v) {
        v.bringToFront();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.clear();
    }
}
