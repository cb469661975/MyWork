package com.example.cheng.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cheng.myapplication.adapter.GiftVipAdapter;
import com.example.cheng.myapplication.net.HttpUtil;
import com.example.cheng.myapplication.net.IRequestCallBack;
import com.example.cheng.myapplication.ui.MarqueeText;
import com.example.cheng.myapplication.ui.MarqueeView;
import com.example.cheng.myapplication.ui.MarqueeView2;
import com.example.cheng.myapplication.ui.WorldNoticeView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Main2Activity extends Activity implements ViewSwitcher.ViewFactory, View.OnTouchListener {

    String url = "http://blog.csdn.net/maplejaw_/article/details/52396175";
    TextView tv_result;
    TextSwitcher textswitcher;

    private String[] arrayTexts = {"文本01", "文本02", "文本03", "文本04"};
    private float touchDownX;
    private int textIndex;
    private float touchUpX;
    //    private WorldNoticeView worldnoticeview;
    private MarqueeView2 mMarqueeView;
    private MarqueeText marqueeText;
    private ViewPager viewpager;

    OkHttpClient okHttpClient;
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private MyPageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_result = (TextView) findViewById(R.id.tv_result);
//        worldnoticeview= (WorldNoticeView) findViewById(R.id.worldnoticeview);
        textswitcher = (TextSwitcher) findViewById(R.id.textswitcher);
        mMarqueeView = (MarqueeView2) findViewById(R.id.mMarqueeView);
        marqueeText = (MarqueeText) findViewById(R.id.marqueetest);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        Log.i(TAG, "onCreate");
        initData();
        textswitcher.setFactory(this);
        textswitcher.setOnTouchListener(this);
        pageAdapter = new MyPageAdapter();
        pageAdapter.setmGiftInfoDatas(list1);
        viewpager.setAdapter(pageAdapter);

        int a = 2;
        if (a < 3) {
            Log.i("aaaa", "aaaaaaa");
        } else if (a < 5) {
            Log.i("aaaa", "bbbbbb");
        }
//        doMemory();
        ImageView iv_gif = findViewById(R.id.iv_gif);
//        Glide.with(this).load(R.drawable.feeds_loading).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv_gif);
        Glide.with(this).load(R.drawable.pull_up).into(iv_gif);
        ImageView iv_gif2 = findViewById(R.id.iv_gif2);
//        Glide.with(this).load(R.drawable.pull_up_pic).into(iv_gif2);
        Glide.with(this).load(R.drawable.pull_up).into(iv_gif2);
    }

//    private void doMemory() {
//        Observable.interval(1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        Log.i("interval","aaaaaa");
//                    }
//                });
//    }

    private class MyPageAdapter extends PagerAdapter {

        private List<String> mGiftInfoDatas = new ArrayList<>();

        public List<String> getmGiftInfoDatas() {
            return mGiftInfoDatas;
        }

        public void setmGiftInfoDatas(List<String> mGiftInfoDatas) {
            this.mGiftInfoDatas = mGiftInfoDatas;

        }

        @Override
        public int getCount() {
            return mGiftInfoDatas.size() == 0 ? 0 : (mGiftInfoDatas.size() - 1) / 8 + 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), R.layout.item_in_gridview, null);
            int maxSize = mGiftInfoDatas.size() > position * 8 + 8 ? position * 8 + 8 : mGiftInfoDatas.size();
            final List<String> giftInfos = mGiftInfoDatas.subList(position * 8, maxSize);
            GridView gridView = (GridView) view.findViewById(R.id.gridView);

            GiftVipAdapter giftadapter;
            Log.i("instantiateItem", giftInfos.size() + "===" + mGiftInfoDatas.size());

            gridView.setAdapter(giftadapter = new GiftVipAdapter(container.getContext()));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.i("instantiateItem", "click+" + giftInfos.get(i));
                }
            });
            giftadapter.setData(giftInfos);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }

    private void initData() {

        for (int i = 0; i < 15; i++) {
            list1.add("我是" + i);
        }
        list2.add("条目222");
        list2.add("条目223");
        list2.add("条目224");
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String networkRequest = VoiceActorNetworkUtil.getNetworkRequest("http://blog.csdn.net/maplejaw_/article/details/52396175");
                subscriber.onNext(networkRequest);
                subscriber.onCompleted();
                Log.i("aaaaaa", Thread.currentThread() + "=c=111111");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
//                        Log.i("ssss",s+"===");
                        Log.i("aaaaaa", Thread.currentThread() + "=c=22222");

                    }
                });
        Log.i("HttpUtil", Thread.currentThread().getId() + "====");
        HttpUtil.getInstance().get(url, null, new IRequestCallBack() {
            @Override
            public void onSuccess(String s) {
//            tv_result.setText(s);
                Log.i("HttpUtil", Thread.currentThread().getId() + "====");

                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        Log.i("HttpUtil", Thread.currentThread().getId() + "==CA==");
                    }
                }).observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i("HttpUtil", Thread.currentThread().getId() + "==C2=");
                    }
                });
            }

            @Override
            public void onFaile(String s) {
//                tv_result.setText(s);
            }
        });

    }

    String TAG = "Main2Activity";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    public void onClickMain(View xc) {
        startActivity(new Intent(this, RecycleViewActivity.class));
    }

    public void onClick1(View xc) {
//        pageAdapter=new MyPageAdapter();
//        pageAdapter.setmGiftInfoDatas(list1);
//        viewpager.setAdapter(pageAdapter);

        int a = 1;
        if (a < 3) {
            Log.i("aaaa", "aaaaaaa");
        } else if (a < 5) {
            Log.i("aaaa", "bbbbbb");
        } else if (a < 7) {
            Log.i("aaaa", "cccccc");
        }
    }

    public void onClick2(View xc) {
//        pageAdapter=new MyPageAdapter();
//        pageAdapter.setmGiftInfoDatas(list2);
//        viewpager.setAdapter(pageAdapter);
        int a = 4;
        if (a < 3) {
            Log.i("aaaa", "aaaaaaa");
        } else if (a < 5) {
            Log.i("aaaa", "bbbbbb");
        } else if (a < 7) {
            Log.i("aaaa", "cccccc");
        }
    }

    public void onClickAnim(View imageView) {

        ObjectAnimator ox = ObjectAnimator.ofFloat(imageView, "scaleX", 0.8f, 1.8f);
        ObjectAnimator oy = ObjectAnimator.ofFloat(imageView, "scaleY", 0.8f, 1.8f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new MyObserable(0.4F));
        set.playTogether(ox, oy);
        set.start();
        Log.i("imageView", "imageView+click");
//        startActivity(new Intent(this,TestConstraintActivity.class));

//        mMarqueeView.setText("a");
        mMarqueeView.startScroll();

        marqueeText.startScroll();

    }

    @Override
    public View makeView() {
        TextView textView = new TextView(this);
        textView.setText("=" + arrayTexts[textIndex]);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLUE);
        return textView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            touchUpX = event.getX();
            // 从左往右，看前一文本
            if (touchUpX - touchDownX > 100) {
                // 取得当前要看的文本的index
                textIndex = textIndex == 0 ? arrayTexts.length - 1
                        : textIndex - 1;
                // 设置文本切换的动画
                textswitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.slide_in_left));
                textswitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.slide_out_right));
                // 设置当前要看的文本
                textswitcher.setText(arrayTexts[textIndex]);
                // 从右往左，看下一张
            } else if (touchDownX - touchUpX > 100) {
                // 取得当前要看的文本的index
                textIndex = textIndex == arrayTexts.length - 1 ? 0
                        : textIndex + 1;
                // 设置文本切换的动画
                // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
                textswitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.slide_out_left));
                textswitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.slide_in_right));
                // 设置当前要看的文本
                textswitcher.setText(arrayTexts[textIndex]);
            }
            return true;
        }
        return false;
    }
}

