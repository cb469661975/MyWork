package com.example.cheng.myapplication;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by chengbiao on 2018/1/16.
 */

public class TestA implements TextInterface {

    RadioGroup group;

    private void test() {

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checedId) {
                Log.i("aaa", group + "==" );
            }
        });
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        group.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }


}
