package com.example.cheng.myapplication.fresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.cheng.myapplication.BaseActivity;
import com.example.cheng.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by chengbiao on 2018/8/8
 */
public class FrescoActiviy extends BaseActivity {

    private SimpleDraweeView draweeView;
    String imageUrl = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201505/26/222D8C3F1F4D79EA5CC40ACD1303DF0E.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frasco);
        draweeView = findViewById(R.id.draweeView);
        draweeView.setImageURI(Uri.parse(imageUrl));


    }
}
