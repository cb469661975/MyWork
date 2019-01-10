package com.example.cheng.myapplication.recycle_divider;

import android.graphics.drawable.Drawable;

/**
 * Created by chengbiao on 2017/12/20.
 */

public class DividerLineModel {
    public int color = 0xaa000000;
    public boolean isShow;
    public int width;
    public int endPadding = 0;
    public int startPadding = 0;

    public DividerLineModel(boolean isShow, int color, int width, int endPadding, int startPadding) {
        this.color = color;
        this.isShow = isShow;
        this.width = width;
        this.endPadding = endPadding;
        this.startPadding = startPadding;
    }
}
