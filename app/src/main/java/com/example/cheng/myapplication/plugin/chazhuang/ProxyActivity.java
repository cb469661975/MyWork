package com.example.cheng.myapplication.plugin.chazhuang;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.reflect.Constructor;

/**
 * Created by chengbiao on 2018/4/19.
 */
public class ProxyActivity extends Activity {

    private String mClassNames;
    private com.example.cheng.myapplication.plugin.chazhuang.inter.ActivityLifeCycleInterface lifeCycleInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassNames = getIntent().getStringExtra("className");
        try {
            Class<?> aClass = getClassLoader().loadClass(mClassNames);
            Constructor<?> constructor = aClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            lifeCycleInterface = (com.example.cheng.myapplication.plugin.chazhuang.inter.ActivityLifeCycleInterface) instance;
            lifeCycleInterface.attach(this);
            lifeCycleInterface.onCreate(new Bundle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(new Intent(this,ProxyActivity.class).putExtra("className", intent.getStringExtra("className")));
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifeCycleInterface.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifeCycleInterface.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifeCycleInterface.onPause();
    }
}
