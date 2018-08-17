package com.example.cheng.myapplication.fresco;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import retrofit2.http.HEAD;

/**
 * Created by chengbiao on 2018/8/10
 */
public class ProgressView extends View {
    int width,height;
    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    private Paint paint;
    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
            paint =new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.GRAY);
            paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.draR
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeW= MeasureSpec.getMode(widthMeasureSpec);
        int modeH= MeasureSpec.getMode(heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        if(modeW!=MeasureSpec.UNSPECIFIED){
            width = wSize;
        }
        if(modeH!=MeasureSpec.UNSPECIFIED){
            height = hSize;
        }
    }

}
