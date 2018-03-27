package com.example.cheng.myapplication.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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

/**
 * Created by chengbiao on 2018/3/13.
 */

class YLBRefreshFooter : FrameLayout, com.scwang.smartrefresh.layout.api.RefreshFooter {
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

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.ylb_loadmore_footer, this)
        mFooterIv = findViewById(R.id.footer_iv)
        mFooterTvMsg = findViewById(R.id.footer_tv_msg)
    }

    override fun onPullingUp(percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {
        //        printLog("up,percent:" + percent + ",offset:" + offset + ",footerHeight:" + footerHeight + ",extendHeight:" + extendHeight);
        if (percent <= 1) {
            mFooterIv!!.scaleX = percent
            mFooterIv!!.scaleY = percent
            mFooterTvMsg!!.scaleX = percent
            mFooterTvMsg!!.scaleY = percent
        }
    }

    override fun onPullReleasing(percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {
        //        printLog("release,percent:" + percent + ",offset:" + offset + ",footerHeight:" + footerHeight + ",extendHeight:" + extendHeight);

    }

    override fun setLoadmoreFinished(finished: Boolean): Boolean {
        return false
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

    override fun onStartAnimator(layout: RefreshLayout, height: Int, extendHeight: Int) {}

    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
        return 0
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {
        when (newState) {
            RefreshState.None -> {
                mFooterTvMsg!!.text = REFRESH_FOOTER_ALLLOADED
                if (oldState != RefreshState.LoadFinish) {
                    loadGifToImageView(R.drawable.pull_up)
                }
                printLog("None，oldState：$oldState,newState$newState")
                mFooterTvMsg!!.text = REFRESH_FOOTER_PULLUP
                //                mFooterIv.setGifResource(R.drawable.pull_up);
                printLog("PullToUpLoad，oldState：$oldState,newState$newState")
            }
            RefreshState.PullToUpLoad -> {
                mFooterTvMsg!!.text = REFRESH_FOOTER_PULLUP
                printLog("PullToUpLoad，oldState：$oldState,newState$newState")
            }
            RefreshState.Loading -> {
                //                mFooterIv.setGifResource(R.drawable.pull_up_pic);
                mFooterTvMsg!!.text = REFRESH_FOOTER_LOADING
                printLog("Loading")
                loadGifToImageView(R.drawable.pull_up_pic)
            }
            RefreshState.ReleaseToLoad -> {
                printLog("ReleaseToLoad")
                mFooterTvMsg!!.text = REFRESH_FOOTER_RELEASE
            }
            RefreshState.Refreshing -> {
                printLog("Refreshing")
                mFooterTvMsg!!.text = REFRESH_FOOTER_REFRESHING
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

    companion object {
        var REFRESH_FOOTER_PULLUP = "上拉加载更多"
        var REFRESH_FOOTER_RELEASE = "释放立即加载"
        var REFRESH_FOOTER_LOADING = "正在加载..."
        var REFRESH_FOOTER_REFRESHING = "正在刷新..."
        var REFRESH_FOOTER_FINISH = "加载完成"
        var REFRESH_FOOTER_FAILED = "加载失败"
        var REFRESH_FOOTER_ALLLOADED = "全部加载完成"
    }
}
