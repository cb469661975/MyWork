package com.example.cheng.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by biao.cheng on 2017/11/16.
 */

public abstract class BasePageFragment extends Fragment {

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public abstract void fetchData();

//    public boolean prepareFetchData() {
//        return prepareFetchData(false);
//    }

    public boolean prepareFetchData() {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated )) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

}