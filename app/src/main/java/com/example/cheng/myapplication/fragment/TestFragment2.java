package com.example.cheng.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by biao.cheng on 2017/11/16.
 */

public class TestFragment2 extends BasePageFragment {
    private TextView tv;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("BASE_LOG", this.getClass().getSimpleName() + ",method=" + "onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tv = new TextView(getActivity());
        tv.setText(this.getClass().getSimpleName());
        tv.setTextSize(30);

//        MyViewModel viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
//
//        viewModel.getSelected().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                Log.i("TestFragment2","I get this-->"+s);
//            }
//        });



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

    public void setTextView(String s) {
        if (tv != null) {
            tv.setText(s);
        }
    }
}
