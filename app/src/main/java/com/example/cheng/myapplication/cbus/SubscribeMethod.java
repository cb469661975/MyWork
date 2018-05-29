package com.example.cheng.myapplication.cbus;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by chengbiao on 2018/4/24.
 */
public class SubscribeMethod {

    private Method method;
    private Class[] classParamters;
    private String label;

    public SubscribeMethod(Method method, Class[] classParamters, String label) {
        this.method = method;
        this.classParamters = classParamters;
        this.label = label;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class[] getClassParamters() {
        return classParamters;
    }

    public void setClassParamters(Class[] classParamters) {
        this.classParamters = classParamters;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "SubscribeMethod{" +
                "method='" + method + '\'' +
                ", classParamters=" + Arrays.toString(classParamters) +
                ", label='" + label + '\'' +
                '}';
    }
}
