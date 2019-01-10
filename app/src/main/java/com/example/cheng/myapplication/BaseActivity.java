package com.example.cheng.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

/**
 * Created by biao.cheng on 2017/9/14.
 */

public class BaseActivity extends AppCompatActivity {
     Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        startAD();
    }

    private long time = 1000 * 5;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.removeCallbacks(runnable);
                break;
            case MotionEvent.ACTION_UP:
//                startAD();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            PrefUtils.setBoolean(BaseActivity.this, "isLogin", false);
            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
            builder.setTitle("温馨提示")
                    .setCancelable(false)
                    .setMessage("当前登录已失效，请重新登录")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Intent intent = new Intent();
//                            intent.setClass(BaseActivity.this, LoginActivity.class);
//                            startActivity(intent);
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    };


    public void startAD() {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void startActivity(Class<? extends Activity> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}