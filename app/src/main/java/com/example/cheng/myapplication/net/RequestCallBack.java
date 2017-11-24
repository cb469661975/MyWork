package com.example.cheng.myapplication.net;

import java.util.Map;

/**
 * Created by biao.cheng on 2017/9/25.
 */

public interface RequestCallBack  {
    public void post(String url, Map params,IRequestCallBack iRequestCallBack);
    public void get(String url, Map params,IRequestCallBack iRequestCallBack);
}
