package com.example.cheng.myapplication.kotlin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.cheng.myapplication.BaseActivity;
import com.example.cheng.myapplication.R;
import com.example.cheng.myapplication.fragment.TestFragment1;
import com.example.cheng.myapplication.fragment.model.Functions;
import com.example.cheng.myapplication.manager.ITimeCount;
import com.example.cheng.myapplication.manager.TimeCountManager;

/**
 * Created by chengbiao on 2018/2/9.
 */

public class TestFragmentTransActivity extends BaseActivity implements ITimeCount {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fg_test_trans);
        addFragment();
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
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new TestFragment1(), TestFragment1.TAG).commit();
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
