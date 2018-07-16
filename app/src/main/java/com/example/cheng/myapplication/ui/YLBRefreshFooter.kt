package com.example.cheng.myapplication.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cheng.myapplication.R
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import kotlinx.android.synthetic.main.ylb_loadmore_footer.view.*

/**
 * Created by chengbiao on 2018/3/13.
 */

class YLBRefreshFooter : FrameLayout, com.scwang.smartrefresh.layout.api.RefreshFooter {

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun onMoving(isDragging: Boolean, percent: Float, offset: Int, height: Int, maxDragHeight: Int) {
        //        printLog("up,percent:" + percent + ",offset:" + offset + ",footerHeight:" + footerHeight + ",extendHeight:" + extendHeight);
        if (percent <= 1) {
            ll_refresh_bottom.visibility = View.VISIBLE
            mFooterIv!!.scaleX = percent
            mFooterIv!!.scaleY = percent
            mFooterTvMsg!!.scaleX = percent
            mFooterTvMsg!!.scaleY = percent
        }
    }

    override fun setNoMoreData(noMoreData: Boolean): Boolean {
        return false
    }

    private var mFooterIv: ImageView? = null
    private var mFooterTvMsg: TextView? = null

    private val DEBUG = true

    private var mSpinnerStyle = SpinnerStyle.Translate

    private fun printLog(msg: String) {
        if (DEBUG) {
            Log.i("YLBRefreshFooter", msg)
        }
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

    private lateinit var roate: RotateAnimation
    private lateinit var scale: ScaleAnimation

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.ylb_loadmore_footer, this)
        mFooterIv = findViewById(R.id.footer_iv)
        mFooterTvMsg = findViewById(R.id.footer_tv_msg)

        roate = RotateAnimation(-10f, 10f, Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 1f)
        roate.duration = ANIMATION_DUR.toLong()
        roate.interpolator = LinearInterpolator( )
        roate.repeatCount = Animation.INFINITE
        roate.repeatMode = Animation.REVERSE

        scale = ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scale.duration = ANIMATION_DUR.toLong()
    }

//    override fun onPullingUp(percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {
//
//    }

//    override fun onPullReleasing(percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {
//        //        printLog("release,percent:" + percent + ",offset:" + offset + ",footerHeight:" + footerHeight + ",extendHeight:" + extendHeight);
//
//    }

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

    override fun onStartAnimator(layout: RefreshLayout, height: Int, extendHeight: Int) {}

    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
        return ANIMATION_DUR
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {
        printLog("PullToUpLoad，oldState：$oldState,newState$newState")
        when (newState) {
            RefreshState.None -> {
                mFooterTvMsg!!.text = REFRESH_FOOTER_ALLLOADED
                    loadGifToImageView(R.drawable.pull_up)
                mFooterTvMsg!!.text = REFRESH_FOOTER_PULLUP
            }
            RefreshState.PullUpToLoad -> {
                mFooterTvMsg!!.text = REFRESH_FOOTER_PULLUP
//                printLog("PullToUpLoad，oldState：$oldState,newState$newState")
            }
            RefreshState.Loading -> {
                //                mFooterIv.setGifResource(R.drawable.pull_up_pic);
                mFooterTvMsg!!.text = REFRESH_FOOTER_LOADING
//                printLog("Loading")
                loadGifToImageView(R.drawable.pull_up_pic)
                mFooterIv?.startAnimation(roate)
            }
            RefreshState.ReleaseToLoad -> {
//                printLog("ReleaseToLoad")
                mFooterTvMsg!!.text = REFRESH_FOOTER_RELEASE
            }
            RefreshState.LoadFinish -> {
//                printLog("LoadFinish")
                mFooterIv?.startAnimation(scale)
                mFooterTvMsg?.startAnimation(scale)
                postDelayed({
                    ll_refresh_bottom.visibility = View.INVISIBLE
                    reset()
                }, ANIMATION_DUR.toLong())
            }
        }
    }

    fun setmSpinnerStyle(mSpinnerStyle: SpinnerStyle): YLBRefreshFooter {
        this.mSpinnerStyle = mSpinnerStyle
        return this
    }

    private fun loadGifToImageView(resource: Int) {
        try {
            Glide.with(this.context).load(resource).into(mFooterIv!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun reset() {
        mFooterTvMsg?.scaleX = 1f
        mFooterTvMsg?.scaleY = 1f
        mFooterIv?.scaleX = 1f
        mFooterIv?.scaleY = 1f
        mFooterIv?.rotation = 0f
    }

    companion object {
        var REFRESH_FOOTER_PULLUP = "加载更多"
        var REFRESH_FOOTER_RELEASE = "立即加载"
        var REFRESH_FOOTER_LOADING = "正在加载"
        var REFRESH_FOOTER_REFRESHING = "正在刷新"
        var REFRESH_FOOTER_FINISH = "加载完成"
        var REFRESH_FOOTER_FAILED = "加载失败"
        var REFRESH_FOOTER_ALLLOADED = "加载完成"
        private const val ANIMATION_DUR = 500
    }
}
