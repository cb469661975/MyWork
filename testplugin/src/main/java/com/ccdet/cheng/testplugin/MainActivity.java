package com.ccdet.cheng.testplugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by chengbiao on 2018/4/19.
 */
public class MainActivity extends BaseActivity {
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (that != null) {
            context = that;
        } else {
            context = this;
        }
        Log.i("MainActivity", "mainId" + findViewById(R.id.bt).toString());
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "show-----", Toast.LENGTH_LONG).show();
                startActivity(new Intent(context, SecondActivity.class));
            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bt2click", "show 2");
                Toast.makeText(context, "show--2222---", Toast.LENGTH_LONG).show();
            }
        });
    }
}
