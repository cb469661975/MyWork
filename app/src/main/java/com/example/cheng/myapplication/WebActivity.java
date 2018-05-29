package com.example.cheng.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.example.cheng.myapplication.ui.X5WebView;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;


/**
 * Created by chengbiao on 2018/1/2.
 */

public class WebActivity extends Activity {
    String webUrl = "https://pc31.ylb.net/h5/productdetail/index.html?d_id=6400255379355205632&type=scatter&isApp=android&origin=test&timestamp=1526438266779&token=sl4n46esosVQZ13iaE87T8N4mokUq4WG1NqwKdEJX6jgPeJbfPMROPIHB9Ama3rj";
    String  url = "http://x5.tencent.com/tbs/guide/sdkInit.html";
    X5WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_ac);
        QbSdk.initX5Environment(this, null);
        webView = (X5WebView) findViewById(R.id.webview);
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            webView.loadUrl(url);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            webView.loadUrl(url);
        }
    }
}
