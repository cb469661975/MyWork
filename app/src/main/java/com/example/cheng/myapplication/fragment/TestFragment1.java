package com.example.cheng.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheng.myapplication.BaseFragment;
import com.example.cheng.myapplication.fragment.model.Functions;
import com.example.cheng.myapplication.kotlin.TestFragmentTransActivity;
import com.example.cheng.myapplication.util.OnDoubleClickListener;

/**
 * Created by biao.cheng on 2017/11/16.
 */

public class TestFragment1 extends BasePageFragment {

    public static final String TAG = TestFragment1.class.getSimpleName();
    public static final String Fun_NO_PARAMS_RESULT = TestFragment1.class.getSimpleName()+"_NPNR";
    private Functions functions ;

    private TestFragmentTransActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TestFragmentTransActivity) {
            mActivity = (TestFragmentTransActivity) context;
            mActivity.setFragment();
        }
    }

    public Functions getFunctions() {
        return functions;
    }

    public void setFunctions(Functions functions) {
        this.functions = functions;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText(this.getClass().getSimpleName());
        tv.setTextSize(30);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Fun_NO_PARAMS_RESULT","click-Fun_NO_PARAMS_RESULT");
                functions.invokeFunction(Fun_NO_PARAMS_RESULT);
            }
        });

        return tv;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("BASE_LOG", this.getClass().getSimpleName() + ",method=" + "onResume");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("BASE_LOG", this.getClass().getSimpleName() + ",method=" + "setUserVisibleHint.result=" + isVisibleToUser);
    }

    @Override
    public void fetchData() {
        Log.i("BASE_LOG", this.getClass().getSimpleName() + ",fetchData=" + "fetchData");
    }
}
