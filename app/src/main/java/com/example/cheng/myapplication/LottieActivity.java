package com.example.cheng.myapplication;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.Cancellable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;

/**
 * Created by chengbiao on 2017/11/29.
 */

public class LottieActivity extends BaseActivity {

    private LottieAnimationView animationView;
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_lottieanim);
        animationView = (LottieAnimationView) findViewById(R.id.animationView);

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

    //0 默认
    //1 正
    //2 反
    int status = 0;

    public void onClickfalse(View view) {
//        animation.setRepeatMode(Animation.REVERSE);
        if (animationView.isAnimating()) {
            Log.i("reverse-anim", "onAnimationEnd-----ing");
            return;
        }
        Log.i("reverse-anim", "onAnimationEnd-----play");
        if (status == 0) {
            animationView.reverseAnimationSpeed();
        } else if (status == 1) {
            animationView.reverseAnimationSpeed();

        } else {
//            animationView.playAnimation();
        }
        animationView.playAnimation();
        status = 2;
//        animationView.playAnimation();
//        animation.start();
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.i("reverse-anim", "onAnimationEnd" + animator.getDuration());
                animator.removeListener(this);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public void onClickTrue(View view) {

 animationView.playAnimation();

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
