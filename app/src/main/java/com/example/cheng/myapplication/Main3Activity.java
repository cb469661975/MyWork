package com.example.cheng.myapplication;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cheng.myapplication.ui.MyDialogFragment;
import com.example.cheng.myapplication.ui.StartOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    CropImageView imageView;
    private Point3View pointView;
    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        for (int i = 0; i < 10; i++) {
            list.add("aaa");
        }

        imageView = (CropImageView) findViewById(R.id.image);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
int notifyNum=30;
        int acceptNum =20;
//        tv_msg.setText(Html.fromHtml("交易明细：<font color= '#ff9b35'>日子好好的</font> \"+\"日期：17-01-06  20:00\")"));
        tv_msg.setText(Html.fromHtml("已推送<font color= '#ff9b35'>"+notifyNum+"人</font>,<font color= '#ff9b35'>"+acceptNum+"人</font>接单"));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("CropImageView", view.getMeasuredWidth() + "===" + view.getMeasuredHeight() + "==" + view.getMeasuredWidth() * 1.0f / view.getMeasuredHeight());


            }
        });

        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new StartOrderFragment()).commit();
    }

    String TAG = "Main3Activity";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    public void onClickMain(View xc) {
//        startActivity(new Intent(this, Main2Activity.class));
        showDialogFrag();
    }

    private void showDialogFrag() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        MyDialogFragment md=new MyDialogFragment();
        md.show(ft,"123");
    }

}
