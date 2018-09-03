package com.example.cheng.myapplication.model;

/**
 * Created by chengbiao on 2018/8/21
 */
public class TestUserInfoBean {
    private String name;
    private String info;

    public TestUserInfoBean() {
    }

    public TestUserInfoBean(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
