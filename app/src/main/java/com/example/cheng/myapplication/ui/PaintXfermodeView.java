package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.cheng.myapplication.R;

public class PaintXfermodeView extends View {

    int mCircleColor = 0xffffcc44;//黄色
    int mRectColor = 0xff66aaff;//蓝色

    private Paint paint;
    public PaintXfermodeView(Context context) {
        this(context,null,0);
    }

    public PaintXfermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaintXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("draw_circle","rawable");

        super.onDraw(canvas);
        canvas.save();
        paint.setColor(mCircleColor);
        canvas.drawCircle(100,100,100,paint);
//        Layer栈--- saveLayer的时候都会新建一个透明的图层（离屏Bitmap-离屏缓冲），并且会将saveLayer之前的一些Canvas操作延续过来
//                后续的绘图操作都在新建的layer上面进行
//        当我们调用restore 或者 restoreToCount 时 更新到对应的图层和画布上
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        paint.setColor(mRectColor);
        canvas.drawCircle(200,200,100,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.restoreToCount(layerId);
//        int layerId2 = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
//        paint.setColor(Color.BLUE);
//        canvas.drawCircle(300,300,100,paint);
//        canvas.restoreToCount(layerId2);

//        paint.setXfermode( new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        canvas.drawCircle(150,150,100,paint);
//        paint.setXfermode(null);
//        canvas.restoreToCount(layerId);
//        paint.setColor(Color.GRAY);
//        canvas.drawLine(100,100,200,200,paint);
//        canvas.restore();
//        RectF rectF = new RectF(100,0,200,200);
//        paint.setColor(Color.GREEN);
//        canvas.drawRect(rectF,paint);

    }
}
