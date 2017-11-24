package com.example.cheng.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by biao.cheng on 2017/11/16.
 */

public class ScrollStartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_scroll);
    }

    public void onClickViewPager(View v) {
        startActivity(ViewPagerActivity.class);
    }
    public void onClickClipDrawable(View v) {
        startActivity(ViewPagerActivity.class);
    }
}
