package com.example.cheng.myapplication.util;

import android.support.annotation.IntDef;
import android.support.v4.app.FragmentActivity;

/**
 * Created by yuanchao on 2017/7/3.
 */

public interface ChatGroupUIDelegate {


    @IntDef({SKIN_NORMAL, SKIN_ORDER_DISPATCH, SKIN_FRIEND})
    public @interface SkinState {
    }

    int SKIN_NORMAL = 0;
    int SKIN_ORDER_DISPATCH = 1;
    int SKIN_FRIEND = 2;

    FragmentActivity getActivity();

    void showLoadingView();

    void stopLoadingView();

    void showKeyboard();

    void showManagerDialog();

    void showMicApplyDialog(int position);

    void showGiftSpecialAnimation(String giftId);

    void toggleGamePager(boolean forceClose);

    @SkinState
    int getSkinState();
}
