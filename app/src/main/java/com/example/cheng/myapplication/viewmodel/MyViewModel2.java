package com.example.cheng.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

/**
 * Created by chengbiao on 2018/8/30
 */
public class MyViewModel2 extends AndroidViewModel {

    private final MutableLiveData<String> selected = new MutableLiveData<String>();

    public MyViewModel2(@NonNull Application application) {
        super(application);
    }

    public void select(String item) {
        selected.setValue(item);
    }

    public LiveData<String> getSelected() {
        return selected;
    }

}
