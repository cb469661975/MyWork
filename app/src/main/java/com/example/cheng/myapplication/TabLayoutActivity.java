package com.example.cheng.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by chengbiao on 2018/1/17.
 */

public class TabLayoutActivity extends Activity {

    AutoTabTabLayout tablayout;
    private Subscription subscribe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tablayut);
        tablayout = (AutoTabTabLayout) findViewById(R.id.tablayout1);

        tablayout.addTab(tablayout.newTab().setText("我我多带带我多带带"));
        tablayout.addTab(tablayout.newTab().setText("我的"));
        tablayout.addTab(tablayout.newTab().setText("我多带带"));
        tablayout.addTab(tablayout.newTab().setText("我的点"));
        tablayout.addTab(tablayout.newTab().setText("我的"));
        tablayout.addTab(tablayout.newTab().setText("我"));
        tablayout.addTab(tablayout.newTab().setText("我多带带我多带带"));

        tablayout.post(new Runnable() {
            @Override
            public void run() {
                post();
            }
        });

        subscribe = rx.Observable.interval(1000, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.i("Observable=interval", "along=" + aLong);
            }
        });

    }

    private void post() {

        try {
            //拿到tabLayout的mTabStrip属性
            Field mTabStripField = tablayout.getClass().getDeclaredField("mTabStrip");
            mTabStripField.setAccessible(true);

            LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tablayout);

            int dp10 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                View tabView = mTabStrip.getChildAt(i);

                //拿到tabView的mTextView属性
                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);

                TextView mTextView = (TextView) mTextViewField.get(tabView);

                tabView.setPadding(0, 0, 0, 0);

                //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                int width = 0;
                width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }

                //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.width = width;
                params.leftMargin = dp10;
                params.rightMargin = dp10;
                tabView.setLayoutParams(params);

                tabView.invalidate();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null) {
            subscribe.unsubscribe();
        }
    }
}
