package com.example.cheng.myapplication.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View

class RecordProgressView : View {
    private var mWidth = 0
    private var mHeight = 0

    var topTextColor: Int = Color.parseColor("#ff38404b")
    var bottomTextColor: Int = Color.parseColor("#ff828282")
    var progressColor = Color.GRAY

    var textTopArray = mutableListOf<String>("提交申请", "系统处理中")

    var topTextSize = dp2px(12f)
    var bottomTextSize = dp2px(12f)
    var progressHeight = dp2px(5f)
    var progressCircleHeight = dp2px(10f)

    var topOffset = dp2px(4f)
    var bottomOffset = dp2px(8f)

     var textTopPaint: Paint
     var textBottomPaint: Paint
     var progressPaint: Paint
     var progressCirclePaint: Paint

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        textTopPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textBottomPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        progressCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)

        textTopPaint.textSize = topTextSize
        textBottomPaint.textSize = bottomTextSize
        progressPaint.strokeWidth = progressHeight
        progressCirclePaint.strokeWidth = progressCircleHeight

        textTopPaint.color = topTextColor
        textBottomPaint.color = bottomTextColor
        progressPaint.color = progressColor
        progressCirclePaint.color = progressColor
        log("init")
    }

    private fun log(s:String){
        Log.i("RecordProgressView",s+"")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        log("onSizeChanged,w=$w,h=$h")
    }

    /**
     * 宽度默认给match
     * 高度warp
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var measureHeight = textTopPaint.measureText(textTopArray[0])
        +topOffset + bottomOffset + Math.max(progressHeight, progressCircleHeight)
        paddingTop + paddingBottom+ textBottomPaint.measureText(textTopArray[0])
        log("onMeasure,w=$mWidth,h=$measureHeight")


        setMeasuredDimension(dp2px(200f).toInt(), measureHeight.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //draw上方文字
        canvas?.save()
        canvas?.drawText(textTopArray[0], paddingLeft.toFloat(), paddingTop.toFloat(), textTopPaint)
        //draw中部progress
        val progressHeightY = paddingTop + textTopPaint.measureText(textTopArray[0]) + topOffset + progressCircleHeight / 2
        log("onDraw,progressHeightY,$progressHeightY")

        canvas?.translate(paddingLeft.toFloat(),progressHeightY)
        log("onDraw,paddingLeft,$paddingLeft")
        canvas?.drawLine(0f,0f,(mWidth-paddingLeft-paddingRight).toFloat(),0f,progressPaint)

        canvas?.drawCircle(progressCircleHeight/2,0f,progressCircleHeight/2,progressCirclePaint)

        //draw下方文字
        canvas?.translate(0f,bottomOffset+progressCircleHeight/2+textBottomPaint.measureText(textTopArray[0]))
        canvas?.drawText(textTopArray[1],0f,0f,textBottomPaint)
        canvas?.restore()
    }

    private fun dp2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
    }

}