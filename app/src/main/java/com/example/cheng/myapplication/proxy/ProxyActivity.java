package com.example.cheng.myapplication.proxy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.cheng.myapplication.BaseActivity;
import com.example.cheng.myapplication.R;
import com.example.cheng.myapplication.cbus.CBus;
import com.example.cheng.myapplication.cbus.Subscribe;


/**
 * Created by chengbiao on 2018/4/23.
 */
public class ProxyActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_proxy);
    }

    private static final String CBUS_NOTI_1 = "12";
    private static final String CBUS_NOTI_2 = "23";

    @Subscribe(value = {CBUS_NOTI_1, CBUS_NOTI_2})
    public void test1(String msg1, String msg2) {
        Log.i("test1", "msg1：" + msg1 + ", msg2：" + msg2);
    }

    public void onClick1(View v) {
        CBus.getDefault().post(CBUS_NOTI_1, "第1个");
    }

    public void onClick2(View v) {
        CBus.getDefault().post(CBUS_NOTI_1,2);
    }

    public void onClick3(View v) {
        CBus.getDefault().post(CBUS_NOTI_2, "第二个", "第三个","第4个");
    }

    public void onClick4(View v) {
        startActivity(new Intent(this,ProxyActivity.class));
    }
    @Override
    protected void onStart() {
        super.onStart();
        CBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CBus.getDefault().unregister(this);
    }
}
