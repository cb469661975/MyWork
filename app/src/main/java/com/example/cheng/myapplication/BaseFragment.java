package com.example.cheng.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by biao.cheng on 2017/11/16.
 */

public class BaseFragment extends Fragment {

    private boolean isUI;
    private boolean isVisiable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        Log.i("BASE_LOG", this.getClass().getSimpleName() + ",method=" + "onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText(this.getClass().getSimpleName());
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(30);
        isUI = true;

        return tv;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.i("BASE_LOG", this.getClass().getSimpleName() + ",method=" + "onResume");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisiable = isVisibleToUser;
//        Log.i("BASE_LOG", this.getClass().getSimpleName() + ",method=" + "setUserVisibleHint.result=" + isVisibleToUser);
    }

    public void log(String s) {
        Log.i("BASE_LOG", this.getClass().getSimpleName() + ",method=" + s);
    }

    public void loadData() {

    }
}
