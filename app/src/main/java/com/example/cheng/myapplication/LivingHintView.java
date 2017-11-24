package com.example.cheng.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lijianjian on 17/9/1.
 */

public class LivingHintView extends View {

    private static final String TAG = LivingHintView.class.getSimpleName();

    private static final int DEFAULT_LINE_COUNT = 4;

    private int mLineCount = DEFAULT_LINE_COUNT;

    private int mWidthSize = dp2px(2);

    private Paint mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mGap;

    private int mPadding = dp2px(1);

    private float[] mCurrentProgresses = {1f, 0.5f, 0.7f, 0.5f};
    private List<ValueAnimator> mValueAnimators = new ArrayList<>();

    public LivingHintView(Context context) {
        super(context);
    }

    public LivingHintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LivingHintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mGap = (getWidth() - mWidthSize * mLineCount) / (mLineCount - 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setStrokeWidth(mWidthSize);
        mBgPaint.setStrokeCap(Paint.Cap.ROUND);
//        float[] height = getLinesHeight();
        for (int i = 0; i < mLineCount; i++) {
            int startX = (mGap + mWidthSize) * i + mWidthSize / 2;
            int endY = getHeight() - mPadding;
            int startY = getHeight() - (int) ((getHeight() - 2 * mPadding) * mCurrentProgresses[i]) - mPadding;
            if (startY >= endY) {
                startY = endY - mPadding;
            }
            canvas.drawLine(startX, startY, startX, endY, mBgPaint);
        }
    }

    private void startViewAnim(float startF, final float endF, final int time) {
        for (int i = 0; i < mLineCount; i++) {
            final ValueAnimator valueAnimator = ValueAnimator.ofFloat(startF, endF);
            valueAnimator.setDuration((int) (time * (new Random().nextFloat() + 0.1)));
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setIntValues();

            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

            valueAnimator.setRepeatMode(ValueAnimator.REVERSE);

            final int index = i;
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedValue = (float) valueAnimator.getAnimatedValue();
                    mCurrentProgresses[index] = animatedValue;
                    invalidate();
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationRepeat(Animator animation) {
                    super.onAnimationRepeat(animation);
                    int duration = (int) (time * (new Random().nextFloat() + 0.1));
                    duration = duration < time / 2 || duration > time * 2 ? time : duration;
                    animation.setDuration(duration);
                }
            });
            valueAnimator.start();
            mValueAnimators.add(valueAnimator);
        }
    }

    public void startAnimation() {
        startViewAnim(0.3f, 0.9f, 500);
    }

    public void stopAnimation() {
        clearAnimation();
        for (ValueAnimator valueAnimator : mValueAnimators) {
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
        }
        invalidate();
    }
    public int dp2px( float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
