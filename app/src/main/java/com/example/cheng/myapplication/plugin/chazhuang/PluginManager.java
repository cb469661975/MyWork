package com.example.cheng.myapplication.plugin.chazhuang;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;


import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by chengbiao on 2018/4/19.
 */
public class PluginManager {

    private static PluginManager instance;
    private Resources resources;
    private DexClassLoader dexClassLoader;
    private Context context;
    private PackageInfo packageInfo;

    public PluginManager() {
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void loadPath(Context context) {

        File filesDir = context.getDir("plugin", Context.MODE_PRIVATE);
        String name = "testplugin-debug.apk";
        String path = new File(filesDir, name).getAbsolutePath();

        PackageManager packageManager = context.getPackageManager();
        packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);

//        activity 名字
        File dexOutFile = context.getDir("dex", Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(path, dexOutFile.getAbsolutePath()
                , null, context.getClassLoader());
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager,path);
            resources =new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PluginManager getInstance() {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager();
                }
            }
        }
        return instance;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }
}
