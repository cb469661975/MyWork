package com.example.cheng.myapplication.manager;

import android.os.CountDownTimer;


/**
 * Created by chengbiao on 2018/2/27.
 */

public class TimeCountManager {

    private static TimeCountManager mTimeCountManager;

    private CountDownTimer timer;

    public static TimeCountManager getInstance() {
        synchronized (TimeCountManager.class) {
            if (mTimeCountManager == null) {
                mTimeCountManager = new TimeCountManager();
            }
        }
        return mTimeCountManager;
    }

    public TimeCountManager() {
    }

    public synchronized void beginTimeCount(ITimeCount timeCountTest) {

        if (timer == null) {
            timer = new MyCountDownTimer(10000, 1000, timeCountTest);
        }
        timer.start();
    }

    public void finishTimeCount() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private class MyCountDownTimer extends CountDownTimer {
        private ITimeCount mTimeCount;

        public MyCountDownTimer(long millisInFuture, long countDownInterval, ITimeCount iTimeCount) {
            super(millisInFuture, countDownInterval);
            mTimeCount = iTimeCount;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTimeCount.beginCount(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            mTimeCount.finishCount();
        }
    }
}
