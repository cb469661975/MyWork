package com.example.cheng.myapplication

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.cheng.myapplication.exentions.dp2px

import com.example.cheng.myapplication.kotlin.KotlinActivity
import com.example.cheng.myapplication.kotlin.TestFragmentTransActivity
import kotlinx.android.synthetic.main.ac_scroll.*


/**
 * Created by biao.cheng on 2017/11/16.
 */

class ScrollStartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_scroll)

    }

    var isShow = false
    var isAnim = false
    fun onClickAnimShow(v: View) {
        if (!isShow && !isAnim) {
            isShow = !isShow
            isAnim = true

            var movey = if (isShow) 50f else -50f
            val animator = ObjectAnimator.ofFloat(tv_show, "translationY", -dp2px(50f).toFloat(), 0f)
            animator.duration = 400
            animator.interpolator = LinearInterpolator()
            animator.start()
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator?) {
                    isAnim = false; tv_show.visibility = View.VISIBLE
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        } else {
            isShow = !isShow
            val animator = ObjectAnimator.ofFloat(tv_show, "translationY", 0f, -dp2px(50f).toFloat())
            animator.duration = 400
            animator.interpolator = LinearInterpolator()
            animator.start()
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator?) {
                    isAnim = false
                    tv_show.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        }
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

    fun onWebJs(view: View) {
        startActivity(TestWebActivity::class.java)
    }
}
