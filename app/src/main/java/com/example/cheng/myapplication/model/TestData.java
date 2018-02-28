package com.example.cheng.myapplication.model;

import java.io.Serializable;

/**
 * Created by chengbiao on 2018/2/27.
 */

public class TestData implements Serializable {
    public ChatGroupGlobalGiftModel model;
    public ShareBean shareBean;

    public TestData() {
    }

    public TestData(ChatGroupGlobalGiftModel model, ShareBean shareBean) {
        this.model = model;
        this.shareBean = shareBean;
    }

    public ChatGroupGlobalGiftModel getModel() {
        return model;
    }

    public void setModel(ChatGroupGlobalGiftModel model) {
        this.model = model;
    }

    public ShareBean getShareBean() {
        return shareBean;
    }

    public void setShareBean(ShareBean shareBean) {
        this.shareBean = shareBean;
    }
}
