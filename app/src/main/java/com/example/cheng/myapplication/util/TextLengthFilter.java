package com.example.cheng.myapplication.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lijianjian on 17/10/25.
 */


public class TextLengthFilter implements InputFilter {
    private  ToastCall toastCall;
    private int MAX_EN;// 最大英文/数字长度 一个汉字算两个字母
    private static final String REGEX = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字


    public TextLengthFilter(int mAX_EN,ToastCall toastCall) {
        super();
        MAX_EN = mAX_EN;
        this.toastCall = toastCall;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int destCount = dest.toString().length()
                + getChineseCount(dest.toString());
        int sourceCount = source.toString().length()
                + getChineseCount(source.toString());
        String name = "";
        int count = 0;
        int i = 0;


        Log.i("start","end="+dend);
        if (destCount + sourceCount > MAX_EN) {
            Log.i("start","MAX_EN");
            toastCall.toast();
            if (destCount < MAX_EN) {
                while (!(destCount + count >= MAX_EN)) {
                    ++i;
                    name = source.subSequence(0, i).toString();
                    count = name.length()
                            + getChineseCount(name);
                    if (destCount + count > MAX_EN) {
                        --i;
                    }
                }
                return i == 0 ? "" : source.subSequence(0, i).toString();
            }
            return "";
        } else {
            return source;
        }
    }

    private int getChineseCount(String str) {
        int count = 0;
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(str);
        while (m.find()) {
            //楼下的朋友提供更简洁的代谢
            count++;
        }
        return count;
    }

    public interface ToastCall{
        void toast();
    }
}

