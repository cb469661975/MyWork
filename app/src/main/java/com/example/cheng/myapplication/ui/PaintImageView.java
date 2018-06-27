package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cheng.myapplication.R;

public class PaintImageView extends View {


    private final ColorMatrix colorMartrix;
    private Paint paint;
    private Bitmap bitmap;

    public PaintImageView(Context context) {
        this(context, null, 0);
    }

    public PaintImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);


        colorMartrix = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f, 1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0,
        });
//        colorMartrix = new ColorMatrix(new float[]{
//                1, 0, 0, 0, 0,
//                0, 0, 0, 0, 0,
//                0, 0, 0, 0, 0,
//                0, 0, 0, 1, 0,
//        });
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.timg);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMartrix));
        RectF rectF = new RectF(0, 100, bitmap.getWidth()/4, bitmap.getHeight()/4);
        canvas.drawBitmap(bitmap, null, rectF, paint);
    }
}
