package com.example.cheng.myapplication

import android.os.Bundle
import android.view.View
import com.example.cheng.myapplication.kotlin.FragmentAcTest

import com.example.cheng.myapplication.kotlin.KotlinActivity
import com.example.cheng.myapplication.kotlin.TestFragmentTransActivity


/**
 * Created by biao.cheng on 2017/11/16.
 */

class ScrollStartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_scroll)

    }


    fun onClickViewPager(v: View) {
        startActivity(ViewPagerActivity::class.java)
    }

    fun onClickClipDrawable(v: View) {
        startActivity(TestActivity::class.java)
    }

    fun onClickLotteanim(view: View) {
        startActivity(LottieActivity::class.java)
    }

    fun onClickKotlin(view: View) {
        startActivity(KotlinActivity::class.java)
    }

    fun onClickTest(view: View) {
        startActivity(TestActivity::class.java)
    }

    fun onClickWebview(view: View) {
        startActivity(WebActivity::class.java)
    }

    fun onClickTablayout(view: View) {
        startActivity(TabLayoutActivity::class.java)
    }

    fun onClickCoord(view: View) {
        startActivity(CoordActivity::class.java)
    }

    fun onClickFragTestTras(view: View) {
        startActivity(TestFragmentTransActivity::class.java)
    }
    fun testViewTracker(view: View) {
        startActivity(TestViewTouchActivity::class.java)
    }
    fun onTestNestScroll(view: View) {
        startActivity(TestNestScrollActivity::class.java)
    }
}
