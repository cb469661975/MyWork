package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.example.cheng.myapplication.R;

public class PathMeasureLoadView extends View {

    private Paint mPaintLine;
    private Paint mPaintPath;

    private int mWidth, mHeight;
    private Bitmap mBitmap;
    private double currentValue;
    private float[] pos;
    private float[] tan;

    private Matrix matrix;


    public PathMeasureLoadView(Context context) {
        this(context, null, 0);

    }

    public PathMeasureLoadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureLoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setStrokeWidth(5);
        mPaintLine.setColor(Color.BLUE);

        mPaintPath = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.path_arrow);
        matrix = new Matrix();

        pos =new float[2];
        tan =new float[2];
      }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);
        canvas.save();
        Path path = new Path();
        path.addCircle(100, 100, 80, Path.Direction.CW);
        PathMeasure pathMeasure = new PathMeasure(path, true);

        currentValue += 0.005;
        if (currentValue >= 1) {
            currentValue = 0;
        }
        //装载 pos+tan
        pathMeasure.getPosTan(pathMeasure.getLength(), pos, tan);
        matrix.reset();
        //图片旋转的角度
        float degress = (float) (Math.atan2(tan[0], tan[1]) * 180f / Math.PI);
        matrix.postRotate(degress);

        canvas.drawPath(path,mPaintPath);
        canvas.drawBitmap(mBitmap,matrix,mPaintPath);
        invalidate();
    }

    private float dp2px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

}
