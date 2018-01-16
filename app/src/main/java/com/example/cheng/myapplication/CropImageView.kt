package com.example.cheng.myapplication

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import com.example.cheng.myapplication.util.TestKotlinUtils

/**
 * Created by biao.cheng on 2017/10/11.
 */

class CropImageView : ImageView {
    internal var width = 0
    internal var height = 0

    private var mRadio = DEFAULT_RADIO

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs,
                    R.styleable.CropImageView)
            mRadio = a.getFloat(R.styleable.CropImageView_radio, DEFAULT_RADIO)
            a.recycle()
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        width = measureWidth
        height = (measureWidth / mRadio).toInt()
        Log.i("CropImageView", "width=" + width + "height=" + height)
        setMeasuredDimension(width, height)
    }

    companion object {
        private val DEFAULT_RADIO = 1.0f
    }
}
