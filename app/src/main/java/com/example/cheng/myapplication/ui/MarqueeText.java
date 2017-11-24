package com.example.cheng.myapplication.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TimingLogger;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by biao.cheng on 2017/11/2.
 */

public class MarqueeText extends TextView implements Runnable {
    private int currentScrollX;// 当前滚动的位置
    private boolean isStop = false;
    private int textWidth;
    private boolean isMeasure = false;

    public MarqueeText(Context context) {
        super(context);
// TODO Auto-generated constructor stub
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
// TODO Auto-generated method stub
        super.onDraw(canvas);
        if (!isMeasure) {// 文字宽度只需获取一次就可以了
            getTextWidth();
            isMeasure = true;
        }
    }

    /**
     * 获取文字宽度
     */
    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        textWidth = (int) paint.measureText(str);

    }
    private long lastTime=0;

    public void run() {
        if(System.currentTimeMillis()-lastTime<16){
            return;
        }
        lastTime= System.currentTimeMillis();


         currentScrollX += 6;// 滚动速度.+号表示往左边-
         scrollTo(currentScrollX, 0);

        if (textWidth > this.getWidth()) {
            currentScrollX += 6;// 滚动速度.+号表示往左边-
            scrollTo(currentScrollX, 0);
        }
        if (getScrollX() >= (textWidth)) {
             scrollTo(this.getWidth(),0);
            currentScrollX = -(this.getWidth());// 当前出现的位置
        }
        postDelayed(this, 16);
    }//这里面实现的是没有省略号的效果。文字没有超出框的长度就不滚，超出就滚

    //    @Override
//    public void run() {
//        currentScrollX += 2;// 滚动速度
//        scrollTo(currentScrollX, 0);
//        if (isStop) {
//            return;
//        }
//        if (getScrollX() <= -(this.getWidth())) {
//            scrollTo(textWidth, 0);
//            currentScrollX = textWidth;
//// return;
//        }
//        postDelayed(this, 5);
//    }
    // 开始滚动
    public void startScroll() {
        Log.i("startScroll",textWidth+"=="+dp2px(175)+"=="+(textWidth>dp2px(175)));
        if(textWidth>dp2px(175)){
            isStop = false;
//            this.removeCallbacks(this);
            post(this);
        }

    }

    // 停止滚动
    public void stopScroll() {
        currentScrollX =0;
        this.removeCallbacks(this);
        isStop = true;
        isMeasure=false;
    }

    // 从头开始滚动
    public void startFor0() {
        if(textWidth>dp2px(100)){
            isStop = false;
//            currentScrollX =-dp2px(175)/2;
            post(this);
        }
    }

    private int dp2px(int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }
}
