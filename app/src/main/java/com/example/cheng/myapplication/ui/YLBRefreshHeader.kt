package com.example.cheng.myapplication.ui

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.RelativeLayout

import com.bumptech.glide.Glide
import com.example.cheng.myapplication.R
import com.example.cheng.myapplication.ui.pathanim.ViewPath
import com.example.cheng.myapplication.ui.pathanim.ViewPathEvaluator
import com.example.cheng.myapplication.ui.pathanim.ViewPoint
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle

import kotlinx.android.synthetic.main.ylb_refresh_header.view.*

/**
 * Created by chengbiao on 2018/3/13.
 * FlipLoadingLayout head
RotateLoadingLayout foot
 */

class YLBRefreshHeader : RelativeLayout, com.scwang.smartrefresh.layout.api.RefreshHeader {

    lateinit var mSpinnerStyle: SpinnerStyle

    companion object {
        var REFRESH_HEADER_PULLDOWN = "下拉刷新"
        var REFRESH_HEADER_LOADING = "正在加载"
        var REFRESH_HEADER_FINISH = "刷新完成"
        var REFRESH_HEADER_FAILED = "刷新失败"
        var REFRESH_HEADER_HAND_RELEASE = "松手刷新"
        const val mLateFinishTime: Int = 1000
        const val ANIMATION_DUR = 500
    }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.ylb_refresh_header, this)
        loadGifToImageView(R.drawable.pull_down_pic)
    }

    private val Debug = true

    private fun printLog(msg: String) {
        if (Debug) {
            Log.i("YLBRefreshHeader", msg)
        }
    }

    private var msgTop = 0
    override fun onPulling(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {
        printLog("percent=$percent,offset=$offset,headerHeight=$headerHeight,extendHeight=$extendHeight")
        if (msgTop == 0) {
            msgTop = header_tv_msg.top
        }
        if (percent>0.2f&&percent<0.9f) {
            headerIv.visibility = View.VISIBLE
            var percentMove = (offset - msgTop).toFloat() / (headerHeight - msgTop).toFloat()
            headerIv.translationY = -dp2px(20) * percentMove
        }
    }

    override fun onReleasing(percent: Float, offset: Int, height: Int, extendHeight: Int) {
    }

    override fun onReleased(refreshLayout: RefreshLayout?, height: Int, extendHeight: Int) {
    }

    private var viewX: Float = 0f
    private var viewY: Float = 0f

    fun setImageLoc(newLoc: ViewPoint) {
        headerIv.translationX = newLoc.x
        headerIv.translationY = newLoc.y
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return mSpinnerStyle
    }

    override fun setPrimaryColors(vararg colors: Int) {

    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, extendHeight: Int) {

    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {

    }

    override fun onStartAnimator(layout: RefreshLayout, height: Int, extendHeight: Int) {

    }


    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
        if (success) {
            header_tv_msg.text = REFRESH_HEADER_FINISH
        } else {
            header_tv_msg.text = REFRESH_HEADER_FAILED
        }
        finishAnim()
        return mLateFinishTime
    }

    private fun startReleasedAnim() {
        if (viewX == 0f || viewY == 0f) {
            viewX = view.x
            viewY = view.y
        }
        val set = AnimatorSet()
        val rotateAnim = ObjectAnimator.ofFloat(headerIv, "rotation", -30f, 0f)
        rotateAnim.duration = ANIMATION_DUR.toLong()
        val path = ViewPath() //偏移坐标
        val currentX = 0f
        val currentY = -dp2px(20).toFloat()
        path.moveTo(currentX, currentY)
        path.curveTo(currentX + resources.getDimensionPixelSize(R.dimen.pull_width) / 8,
                currentY - resources.getDimensionPixelSize(R.dimen.pull_height) / 8,
                currentX + resources.getDimensionPixelSize(R.dimen.pull_width) * 2 / 3,
                currentY - resources.getDimensionPixelSize(R.dimen.pull_height) * 2 / 3,
                currentX + resources.getDimensionPixelSize(R.dimen.pull_width),
                currentY - resources.getDimensionPixelSize(R.dimen.pull_height) / 2 + resources.getDimensionPixelSize(R.dimen.pull_gif_leve) - resources.getDimensionPixelSize(R.dimen.pull_gif_leve_1))
        val anim = ObjectAnimator.ofObject(this, "imageLoc", ViewPathEvaluator(), *path.points.toTypedArray())
        anim.duration = ANIMATION_DUR.toLong()
        set.playTogether(rotateAnim, anim)
        set.start()
    }


    private fun finishAnim() {
        printLog("finishAnim")
        val set = AnimatorSet()
        val rotateAnim = ObjectAnimator.ofFloat(headerIv, "rotation", 0f, 30f)
        rotateAnim.interpolator = AccelerateDecelerateInterpolator()
        rotateAnim.duration = ANIMATION_DUR.toLong()
        val path = ViewPath() //偏移坐标
        val currentX = resources.getDimensionPixelSize(R.dimen.pull_width).toFloat()
        // currentY 是 pull height 高度的 一半 减去 海报 上浮的高度
        val currentY = (-dp2px(20) - resources.getDimensionPixelSize(R.dimen.pull_height) / 2 + resources.getDimensionPixelSize(R.dimen.pull_gif_leve) - resources.getDimensionPixelSize(R.dimen.pull_gif_leve_1)).toFloat()
        path.moveTo(currentX, currentY)
        path.curveTo(currentX + resources.getDimensionPixelSize(R.dimen.pull_width) / 8,
                currentY + resources.getDimensionPixelSize(R.dimen.pull_height) / 10,
                currentX + resources.getDimensionPixelSize(R.dimen.pull_width) * 3 / 5,
                currentY + resources.getDimensionPixelSize(R.dimen.pull_height) / 10,
                currentX + resources.getDimensionPixelSize(R.dimen.pull_width),
                currentY + resources.getDimensionPixelSize(R.dimen.pull_height) / 2
                        + resources.getDimensionPixelSize(R.dimen.pull_gif_leve))
        val anim = ObjectAnimator.ofObject(this, "imageLoc", ViewPathEvaluator(), *path.points.toTypedArray())
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.duration = ANIMATION_DUR.toLong()
        set.playTogether(rotateAnim, anim)
        set.start()
        set.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                resetAnim()
            }

            override fun onAnimationCancel(animation: Animator?) {
                resetAnim()
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }

    private fun resetAnim() {
        headerIv.visibility= View.INVISIBLE
        val valueAnimator = ValueAnimator.ofInt(0, 10)
        valueAnimator.duration = mLateFinishTime.toLong()
        val aniSet = AnimatorSet()
        val objectAnimatorY = ObjectAnimator.ofFloat(headerIv, "translationY", headerIv.translationY, viewY)
        val objectAnimatorX = ObjectAnimator.ofFloat(headerIv, "translationX", headerIv.translationX, viewX)
        headerIv.translationX = 0f
        headerIv.translationY = 0f
        headerIv.rotation = -30f
        aniSet.duration = 100
        aniSet.startDelay =ANIMATION_DUR.toLong()
        aniSet.playTogether(objectAnimatorX, objectAnimatorY)
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {
        when (newState) {
            RefreshState.None -> {
                loadGifToImageView(R.drawable.pull_down_pic)
                header_tv_msg.text = REFRESH_HEADER_PULLDOWN
                printLog("None")
            }
            RefreshState.PullDownToRefresh -> {
                header_tv_msg.text = REFRESH_HEADER_PULLDOWN
                printLog("PullDownToRefresh")
            }
            RefreshState.Refreshing -> {
                printLog("Refreshing")
                header_tv_msg.text = REFRESH_HEADER_LOADING
                loadGifToImageView(R.drawable.pull_down)
                startReleasedAnim()
            }
            RefreshState.ReleaseToRefresh -> {
                printLog("ReleaseToRefresh")
                header_tv_msg.text = REFRESH_HEADER_HAND_RELEASE
            }
            RefreshState.Loading -> {
                printLog("Loading")
                header_tv_msg.text = REFRESH_HEADER_LOADING
            }
            RefreshState.LoadFinish -> {
                printLog("LoadFinish")
                header_tv_msg.text = REFRESH_HEADER_FINISH
            }
        }
    }

    fun setSpinnerStyle(style: SpinnerStyle): YLBRefreshHeader {
        mSpinnerStyle = style
        return this
    }

    private fun dp2px(value: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), resources.displayMetrics).toInt()
    }


    private fun loadGifToImageView(resource: Int) {
        try {
            Glide.with(this.context).asGif().load(resource).into(headerIv!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
