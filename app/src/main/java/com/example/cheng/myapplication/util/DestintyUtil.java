package com.example.cheng.myapplication.util;

import android.content.Context;
import android.util.TypedValue;


/**
 * Created by chengbiao on 2018/8/10
 */
public class DestintyUtil {
    public static float dp2px(Context context,float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,context.getResources().getDisplayMetrics());
    }
}
