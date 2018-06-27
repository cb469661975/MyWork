package com.example.cheng.myapplication.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.ImageView

class CouponArrowView: ImageView{
     var isUp = false
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setArrowUp(isUp:Boolean){
        var from = 0
        var to = -180
        if (!isUp) {
            from = 0
            to = -180
        } else {
            from = -180
            to = 0
        }
        this.isUp = !isUp

    }
}