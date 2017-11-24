package com.example.cheng.myapplication;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cheng.myapplication.model.ChatGroupGlobalGiftModel;
import com.example.cheng.myapplication.ui.MarqueeText;
import com.example.cheng.myapplication.util.AbsChatGroupUIComponent;

/**
 * Created by biao.cheng on 2017/11/3.
 */

public class GlobalNoticeView extends AbsChatGroupUIComponent {

    public static final String TAG = "GlobalNoticeView";
    ImageView mTvAroundSee;
    ImageView mIvLeftIcon;

    private boolean mIsPlaying;

    private IChatGroupGlobalGiftCallback mCallback;
    private MarqueeText mMaquneview;
    private HorizontalScrollView scrollview;
    private ChatGroupGlobalGiftModel mChatGroupGlobalGiftModel;


    public GlobalNoticeView(Context context) {
        super(context);
    }

    public GlobalNoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GlobalNoticeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void initView(Context context) {
        View.inflate(getContext(), R.layout.layout_global_notice, this);
        mTvAroundSee = (ImageView) findViewById(R.id.tv_around_see);
        mIvLeftIcon = (ImageView) findViewById(R.id.iv_left_icon);
        mMaquneview = (MarqueeText) findViewById(R.id.maquneview);
        updateView();
//        ChatGroupGiftManager.getInst().registerGlobalGiftView(this);

        mTvAroundSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChatGroupGlobalGiftModel != null) {
                    joinGlobalGiftRoom(mChatGroupGlobalGiftModel.httpGroupId);
                }
            }
        });
    }

    @Override
    public void updateView() {
        setVisibility(mIsPlaying ? VISIBLE : INVISIBLE);
    }

    @Override
    public void destroy() {
        mCallback = null;
//        ChatGroupGiftManager.getInst().unregisterGlobalGiftView(this);
        removeCallbacks(animationRunnable);
    }

    public boolean isPlaying() {
        return mIsPlaying;
    }

    public void showNotice(ChatGroupGlobalGiftModel chatGroupGlobalGiftModel, IChatGroupGlobalGiftCallback callback) {
        if (chatGroupGlobalGiftModel == null||mIsPlaying) {
            return;
        }
        mCallback = callback;
        mIsPlaying = true;
        bindModel(chatGroupGlobalGiftModel);
        transLateIn();
    }

    private void bindModel(ChatGroupGlobalGiftModel chatGroupGlobalGiftModel) {
        this.mChatGroupGlobalGiftModel = chatGroupGlobalGiftModel;
        mMaquneview.setText(Html.fromHtml("<font color= '#fff165'>" + chatGroupGlobalGiftModel.senderName +
                "</font>给" + "<font color= '#fff165'>" + chatGroupGlobalGiftModel.receiverName + "</font>送了"
                + "<font color= '#fff165'>" + chatGroupGlobalGiftModel.number + "</font>个"
                + "<font color= '#fff165'>" + chatGroupGlobalGiftModel.giftName + "</font>"
        ));
    }

    private Runnable animationRunnable = new Runnable() {
        @Override
        public void run() {
            translateOut();

        }
    };
    private Runnable delayRunnable = new Runnable() {
        @Override
        public void run() {
            mMaquneview.startFor0();
        }
    };

    private void joinGlobalGiftRoom(final String httpGroupId) {
//        ChatGroupNetWorkUtil.getSipIdFromServer(httpGroupId, new ReactChannel.RequestCallback() {
//            @Override
//            public void successCallback(String result) {
//                JSONObject resultJson = JSONObject.parseObject(result);
//                String sipId = null;
//                int status = 0;
//                if (resultJson.containsKey("sip_addr")) {
//                    sipId = resultJson.getString("sip_addr");
//                }
//                if (resultJson.containsKey("status")) {
//                    status = resultJson.getInteger("status");
//                }
//                if (status == 1 && !com.cootek.andes.utils.TextUtils.isEmpty(sipId)) {
//                    GroupEngineHandler.getInstance().joinGroup(TPApplication.getAppContext(), sipId, httpGroupId);
//                    TPApplication.getAppContext().sendBroadcast(new Intent(ChatGroupActivity.ACTION_CLOSE_CURRENT_ACTIVITY));
//                } else {
//                    ToastUtil.forceToShowToastInCenter("当前房间已经关闭");
//                }
//            }
//
//            @Override
//            public void failedCallback(int responseCode, int resultCode) {
//                ToastUtil.forceToShowToastInCenter("当前网络不佳");
//            }
//        });
    }

    private void transLateIn() {
        Animation rightInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bibi_world_notice_right_in);
        this.startAnimation(rightInAnim);
        rightInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                postDelayed(animationRunnable, 4000);
                postDelayed(delayRunnable,500);
                updateView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void show() {
        transLateIn();
    }


    private void translateOut() {
        Animation leftOutAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bibi_world_notice_left_out);
        this.startAnimation(leftOutAnim);
        leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mMaquneview.stopScroll();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "animation runnable call : " + (mCallback == null));
                if (mCallback != null) {
                    mCallback.onGiftEndPlay();
                    mCallback = null;
                }
                mIsPlaying = false;
                updateView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
