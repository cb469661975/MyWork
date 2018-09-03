package com.example.cheng.myapplication.kotlin;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.cheng.myapplication.BaseActivity;
import com.example.cheng.myapplication.R;
import com.example.cheng.myapplication.fragment.TestFragment1;
import com.example.cheng.myapplication.fragment.model.Functions;
import com.example.cheng.myapplication.manager.ITimeCount;
import com.example.cheng.myapplication.manager.TimeCountManager;
import com.example.cheng.myapplication.viewmodel.MyViewModel;

/**
 * Created by chengbiao on 2018/2/9.
 */

public class TestFragmentTransActivity extends BaseActivity implements ITimeCount {

    private TestFragment1 testFragment1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fg_test_trans);
        addFragment();

        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        viewModel.getSelected().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //这里是收不到的。
                Log.i("TestFragment2","activigty_I get this-->"+s);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("TestFragment2","TestFragment2--get");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            Log.i("TestFragment2","---?"+savedInstanceState.getString("TestFragment2"));
        }
    }

    public void setFragment() {
        TestFragment1 testFragment1 = (TestFragment1) getSupportFragmentManager().findFragmentByTag(TestFragment1.TAG);
        testFragment1.setFunctions(Functions.getInstance());

        try {
            Log.i("Fun_NO_PARAMS_RESULT", "init——show-Fun_NO_PARAMS_RESULT");
            testFragment1.getFunctions().addFunction(new Functions.NoParamNoResultFunction(TestFragment1.Fun_NO_PARAMS_RESULT) {
                @Override
                public void invokeFunction() {
                    Toast.makeText(TestFragmentTransActivity.this, "拿到了点击事件==", Toast.LENGTH_LONG).show();
                    TimeCountManager.getInstance().beginTimeCount(TestFragmentTransActivity.this);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addFragment() {
        testFragment1 = new TestFragment1();
        testFragment1.setCallBack(new TestFragment1.CallBack() {
            @Override
            public void callTest() {
                //在这里处理你的Fragment回调逻辑。
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, testFragment1, TestFragment1.TAG).commit();
    }

    @Override
    public void beginCount(long millisUntilFinished) {
        Log.i(TestFragmentTransActivity.class.getSimpleName(), "millisUntilFinished=" + millisUntilFinished / 1000);
    }

    @Override
    public void finishCount() {
        TimeCountManager.getInstance().finishTimeCount();
        Log.i(TestFragmentTransActivity.class.getSimpleName(), "millisUntilFinished");
    }

}
