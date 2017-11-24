package com.example.cheng.myapplication;


import android.view.animation.Interpolator;

/**
 * Created by biao.cheng on 2017/9/18.
 */

public class MyObserable implements Interpolator {
    private float value;

    public MyObserable(float value) {
        this.value = value;
    }

    @Override
    public float getInterpolation(float v) {
        return (float) (Math.pow(2,-10*v)*Math.sin((v-value/4)*(2*Math.PI)/value+1));
    }
}
