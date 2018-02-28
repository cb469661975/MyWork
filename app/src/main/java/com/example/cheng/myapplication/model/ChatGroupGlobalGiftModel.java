package com.example.cheng.myapplication.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by yuanchao on 2017/11/3.
 */

public class ChatGroupGlobalGiftModel implements Serializable {

    @JSONField(name = "target_group_id")
    public String httpGroupId;

    @JSONField(name = "sender_nick_name")
    public String senderName;

    @JSONField(name = "receiver_nick_name")
    public String receiverName;

    @JSONField(name = "number")
    public int number;

    @JSONField(name = "unit")
    public String unit;

    @JSONField(name = "gift")
    public String giftName;

    public long lastShowTimeStamp;

    @Override
    public String toString() {
        return "ChatGroupGlobalGiftModel{" +
                "httpGroupId='" + httpGroupId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", number=" + number +
                ", unit='" + unit + '\'' +
                ", giftName='" + giftName + '\'' +
                ", lastShowTimeStamp=" + lastShowTimeStamp +
                '}';
    }
}
