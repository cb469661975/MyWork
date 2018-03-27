package com.example.cheng.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.cheng.myapplication.jsbridge.BridgeImpl;
import com.example.cheng.myapplication.jsbridge.JSBridge;
import com.example.cheng.myapplication.jsbridge.JSBridgeWebChromeClient;

/**
 * Created by chengbiao on 2018/3/26.
 */

public class TestWebActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_ac);
        mWebView = findViewById(R.id.webview);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new JSBridgeWebChromeClient());
        mWebView.loadUrl("file:///android_asset/index.html");
        JSBridge.register("bridge", BridgeImpl.class);
    }
}
