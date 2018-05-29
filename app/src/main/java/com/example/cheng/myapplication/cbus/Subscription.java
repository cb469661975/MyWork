package com.example.cheng.myapplication.cbus;

/**
 * Created by chengbiao on 2018/4/24.
 */
public class Subscription {

    private SubscribeMethod subscribeMethod;
    private Object subscribe;

    public Subscription(SubscribeMethod subscribeMethod, Object subscribe) {
        this.subscribeMethod = subscribeMethod;
        this.subscribe = subscribe;
    }

    public SubscribeMethod getSubscribeMethod() {
        return subscribeMethod;
    }

    public void setSubscribeMethod(SubscribeMethod subscribeMethod) {
        this.subscribeMethod = subscribeMethod;
    }

    public Object getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Object subscribe) {
        this.subscribe = subscribe;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscribeMethod=" + subscribeMethod +
                ", subscribe=" + subscribe +
                '}';
    }
}
