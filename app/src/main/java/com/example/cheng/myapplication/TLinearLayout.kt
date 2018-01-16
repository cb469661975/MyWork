package com.example.cheng.myapplication

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by biao.cheng on 2017/10/20.
 */

class TLinearLayout : LinearLayout {

    private var childNum = 3

    private var w: Int = 0

    fun setChildNum(childNum: Int) {
        this.childNum = childNum
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        this.orientation = LinearLayout.HORIZONTAL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.w = w
        Log.i("aaa", "onSizeChanged=$measuredWidth,w=$w")
        //        setItemNum(4);
    }

    fun setItemNum(num: Int) {
        this.removeAllViews()
        Log.i("aaaa", "addView" + num)
        for (i in 0 until num) {
            val tv = TextView(context)
            val lp = LinearLayout.LayoutParams(getwidth() / num, ViewGroup.LayoutParams.MATCH_PARENT)
            tv.text = "num" + i
            tv.setTextColor(Color.BLUE)
            tv.gravity = Gravity.CENTER
            this.addView(tv, lp)
        }
    }

    private fun getwidth(): Int {
        return w
    }

}
