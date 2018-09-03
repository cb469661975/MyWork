package com.example.cheng.myapplication.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by chengbiao on 2018/8/30
 */
public class MyViewModel extends ViewModel {

    private final MutableLiveData<String> selected = new MutableLiveData<String>();

    public void select(String item) {
        selected.setValue(item);
    }

    public LiveData<String> getSelected() {
        return selected;
    }
}
