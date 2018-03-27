package com.example.cheng.myapplication.ui;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by chengbiao on 2018/3/14.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    int width;
    int height;

    public BezierEvaluator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue,
                           PointF endValue) {
        final float t = fraction;
        float oneMinusT = 1.0f - t;
        PointF point = new PointF();

        PointF point0 = (PointF) startValue;

        PointF point1 = new PointF();
        point1.set(width, 0);

        PointF point2 = new PointF();
        point2.set(0, height);

        PointF point3 = (PointF) endValue;

        point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
                + 3 * oneMinusT * oneMinusT * t * (point1.x)
                + 3 * oneMinusT * t * t * (point2.x)
                + t * t * t * (point3.x);

        point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
                + 3 * oneMinusT * oneMinusT * t * (point1.y)
                + 3 * oneMinusT * t * t * (point2.y)
                + t * t * t * (point3.y);
        return point;
    }

}