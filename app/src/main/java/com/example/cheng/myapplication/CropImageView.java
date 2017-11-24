package com.example.cheng.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by biao.cheng on 2017/10/11.
 */

public class CropImageView extends ImageView {
    int width=0;
    int height=0;
    private static final float DEFAULT_RADIO =1.0f;

    private float mRadio=DEFAULT_RADIO;

    public CropImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CropImageView(Context context) {
        super(context);
        init(context,null);
    }

    public CropImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs){
        if(attrs!=null){
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CropImageView);
            mRadio=a.getFloat(R.styleable.CropImageView_radio, DEFAULT_RADIO);
                a.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        width=measureWidth;
        height= (int) (measureWidth/mRadio);
        Log.i("CropImageView","width="+width+"height="+height);
        setMeasuredDimension(width,height);
    }
}
