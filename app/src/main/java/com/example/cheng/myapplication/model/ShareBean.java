package com.example.cheng.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GodXj on 2016/6/16.
 */
public class ShareBean implements Parcelable {

    public String title;
    public String content;
    public int useDefault;
    public String imgUrl;
    public String shareUrl;
    public int isNeedCreate;
    public String delayShare;
    public String stayWhich;//wechat,wx,qq

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeInt(this.useDefault);
        dest.writeString(this.imgUrl);
        dest.writeString(this.shareUrl);
        dest.writeString(this.delayShare);
        dest.writeString(this.stayWhich);
    }

    public ShareBean() {
    }

    protected ShareBean(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.useDefault = in.readInt();
        this.imgUrl = in.readString();
        this.shareUrl = in.readString();
        this.delayShare = in.readString();
        this.stayWhich = in.readString();
    }

    public static final Parcelable.Creator<ShareBean> CREATOR = new Parcelable.Creator<ShareBean>() {
        public ShareBean createFromParcel(Parcel source) {
            return new ShareBean(source);
        }

        public ShareBean[] newArray(int size) {
            return new ShareBean[size];
        }
    };
}
