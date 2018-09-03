package com.example.cheng.myapplication

import android.animation.Animator
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View

import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.ac_lottieanim.*

/**
 * Created by chengbiao on 2017/11/29.
 */

class LottieActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_ccc -> {

            }
        }

    }

    private var animationView: LottieAnimationView? = null
    internal var viewPager: ViewPager? = null

    //0 默认
    //1 正
    //2 反
    internal var status = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_lottieanim)
        animationView = findViewById<View>(R.id.animationView) as LottieAnimationView
        bt_ccc.setOnClickListener(this)

        bt_ccc.setOnClickListener {
        }

        //        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        //            @Override
        //            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //
        //            }
        //
        //            @Override
        //            public void onPageSelected(int position) {
        //
        //            }
        //
        //            @Override
        //            public void onPageScrollStateChanged(int state) {
        //
        //            }
        //        });
    }

    fun onClickfalse(view: View) {
        //        animation.setRepeatMode(Animation.REVERSE);
        if (animationView!!.isAnimating) {
            Log.i("reverse-anim", "onAnimationEnd-----ing")
            return
        }
        Log.i("reverse-anim", "onAnimationEnd-----play")
        if (status == 0) {
            animationView!!.reverseAnimationSpeed()
        } else if (status == 1) {
            animationView!!.reverseAnimationSpeed()

        } else {
            //            animationView.playAnimation();
        }
        animationView!!.playAnimation()
        status = 2
        //        animationView.playAnimation();
        //        animation.start();
        animationView!!.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {}

            override fun onAnimationEnd(animator: Animator) {
                Log.i("reverse-anim", "onAnimationEnd" + animator.duration)
                animator.removeListener(this)
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
    }

    fun onClickTrue(view: View) {

        animationView!!.playAnimation()

        //        if (animationView.isAnimating()) {
        //            Log.i("true-anim", "onAnimationEnd-----ing");
        //            return;
        //        }
        //        Log.i("true-anim", "onAnimationEnd-----play");
        //        if (status == 0) {
        //            animationView.playAnimation();
        //        } else if (status == 1) {
        //            animationView.playAnimation();
        //        } else {
        //            animationView.reverseAnimationSpeed();
        //            animationView.playAnimation();
        //        }
        //        status = 1;
        //        animationView.addAnimatorListener(new Animator.AnimatorListener() {
        //            @Override
        //            public void onAnimationStart(Animator animator) {
        //            }
        //
        //            @Override
        //            public void onAnimationEnd(Animator animator) {
        //                Log.i("true-anim", "onAnimationEnd" + animator.getDuration());
        //                animator.removeListener(this);
        //            }
        //
        //            @Override
        //            public void onAnimationCancel(Animator animator) {
        //
        //            }
        //
        //            @Override
        //            public void onAnimationRepeat(Animator animator) {
        //
        //            }
        //        });
    }
}
