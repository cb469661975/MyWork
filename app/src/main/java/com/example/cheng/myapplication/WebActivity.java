package com.example.cheng.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.cheng.myapplication.R;

/**
 * Created by chengbiao on 2018/1/2.
 */

public class WebActivity extends Activity {
    String webUrl = "https://www.feidianjr.com/h5/brand/index.html?isApp=android&origin=test&token=yKEZmwEonIfJ3K2oQplsda5kPzVlm9D1HiIQMqB1djRJbBhQe53Cqks5cyRRPubs";
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_ac);
        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(webUrl);

    }
}
