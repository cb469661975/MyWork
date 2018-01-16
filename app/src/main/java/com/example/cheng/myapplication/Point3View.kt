package com.example.cheng.myapplication

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.TextView

/**
 * Created by biao.cheng on 2017/10/24.
 */

class Point3View : TextView {
    private var animator: ValueAnimator? = null

    private var currentValue: Int = 0

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
        this.text = "..."
        this.setTextColor(Color.WHITE)
        this.textSize = 20f
        main()
    }

    fun main(): Unit {

//        Log.i("String",it)

        "Strubf".run {
            Log.i("setTextColor", this.toString())
        }.let {
            Log.i("setTextColor", it.toString())
        }

    }

    fun showPoint() {
        animator = ValueAnimator.ofInt(0, 3)
        animator!!.interpolator = LinearInterpolator()
        animator!!.duration = 2500
        animator!!.repeatCount = 100
        animator!!.start()

        animator!!.addUpdateListener(ValueAnimator.AnimatorUpdateListener { valueAnimator ->
            val current = valueAnimator.animatedValue as Int
            if (current == currentValue) {
                return@AnimatorUpdateListener
            } else {
                currentValue = current
            }
            Log.i("testView", current.toString() + "====")
            when (current) {
                0 -> text = "."
                1 -> text = ".."
                2 -> text = "..."
            }
        })
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        showPoint()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (animator != null) {
            animator!!.repeatCount = 0
            animator!!.cancel()
            animator!!.cancel()
        }
    }
}
