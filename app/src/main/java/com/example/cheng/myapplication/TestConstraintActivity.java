package com.example.cheng.myapplication;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by biao.cheng on 2017/10/20.
 */

public class TestConstraintActivity extends Activity {
    ImageView iv_src_2;
    ImageView iv_src_3;
    ImageView iv_src;
    ImageView iv_bg;
    ConstraintLayout firstLayout;
    private ConstraintSet first;
    private ConstraintSet second;
//    private boolean isFirst;
//    private Button start;

//    private ConstraintLayout firstLayout;
//    private Button start;
    boolean isFirst=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_con);
        iv_src_2 = findViewById(R.id.iv_src_2);
        iv_src_3 = findViewById(R.id.iv_src_3);
        iv_src = findViewById(R.id.iv_src);
        iv_bg = findViewById(R.id.iv_bg);
        firstLayout = findViewById(R.id.constraintLayout);

        first=new ConstraintSet();
        second=new ConstraintSet();

//        firstLayout=findViewById(R.id.contentPanel);
//        start=findViewById(R.id.start);

        first.clone(firstLayout);
        second.clone(this,R.layout.activity_constaint);

        findViewById(R.id.iv_src_2).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(isFirst){
                    isFirst=false;
                    TransitionManager.beginDelayedTransition(firstLayout);// 动画效果
                    second.applyTo(firstLayout);
                }else {
                    isFirst=true;
                    TransitionManager.beginDelayedTransition(firstLayout);
                    first.applyTo(firstLayout);
                }
            }
        });

    }
}
