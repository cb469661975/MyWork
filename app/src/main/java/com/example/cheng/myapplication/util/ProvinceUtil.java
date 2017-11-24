package com.example.cheng.myapplication.util;


import android.text.TextUtils;
import android.util.Log;

/**
 * Created by biao.cheng on 2017/10/24.
 */

public class ProvinceUtil {

    public static String getProvinceCity(String addr) {
        String result = addr;

        if (!TextUtils.isEmpty(addr)) {
            if (addr.startsWith("香港")) {
                result = "香港特别行政区";
            } else if (addr.startsWith("澳门")) {
                result = "澳门特别行政区";
            } else {
                result = addr.substring(0, addr.indexOf("市") + 1);
            }
        }
        Log.i("result;",result);
        return result;
    }




}
