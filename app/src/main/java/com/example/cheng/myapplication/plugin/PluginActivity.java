package com.example.cheng.myapplication.plugin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.cheng.myapplication.R;
import com.example.cheng.myapplication.plugin.chazhuang.PluginManager;
import com.example.cheng.myapplication.plugin.chazhuang.ProxyActivity;
import com.example.cheng.myapplication.util.FileUtil;
import com.tencent.tinker.android.dex.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chengbiao on 2018/4/19.
 */
public class PluginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        PluginManager.getInstance().setContext(this);

    }

    public void loadPlugin(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            } else {
                loadPlugin();
            }
        } else {
            loadPlugin();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadPlugin();
                }
                break;
        }

    }

    public void loadPlugin() {
        File fileDir = getDir("plugin", Context.MODE_PRIVATE);
        String apkName = "testplugin-debug.apk";
        log("fileDir",fileDir.getAbsolutePath());

        String filePath = new File(fileDir, apkName).getAbsolutePath();
        File file = new File(filePath);
        log(filePath);
        if (file.exists()) {
            file.delete();
        }

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(Environment.getExternalStorageDirectory()+File.separator+"plugin", apkName).getAbsolutePath());
            os = new FileOutputStream(filePath);
            int len = 0;
            byte[] readArray = new byte[1024];

            while ((len = is.read(readArray)) != -1) {
                os.write(readArray, 0, len);
            }
            PluginManager.getInstance().loadPath(this);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null){
                    is.close();
                }
                if(os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void forward(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }

    private void log(String s1, String s) {
        Log.i("plugin_load",s1 + "====" + "plugin_load==="+s);
    }
    private void log( String s) {
        Log.i( "plugin_load", s);
    }
}

