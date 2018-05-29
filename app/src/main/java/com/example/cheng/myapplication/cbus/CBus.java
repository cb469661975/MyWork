package com.example.cheng.myapplication.cbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chengbiao on 2018/4/24.
 */
public class CBus {
    private static volatile CBus cBus;
    private static Map<Class, List<SubscribeMethod>> CACHE = new HashMap<>();
    private static Map<String, List<Subscription>> SUBSCRIBE = new HashMap<>();
    private static Map<Class, List<String>> REGISTER = new HashMap<>();

    private CBus() {
    }

    public static CBus getDefault() {
        if (cBus == null) {
            synchronized (CBus.class) {
                if (cBus == null) {
                    cBus = new CBus();
                }
            }
        }
        return cBus;
    }

    public void post(String label, Object... params) {
        List<Subscription> subscriptions = SUBSCRIBE.get(label);
        if (subscriptions == null) return;
        for (Subscription subscription : subscriptions) {
            SubscribeMethod subscribeMethod = subscription.getSubscribeMethod();
            //处理参数个数不一致的情况
            Class[] classParameters = subscribeMethod.getClassParamters();
            Object[] realParameters = new Object[classParameters.length];
            for (int i = 0; i < classParameters.length; i++) {
                if (params != null) {
                    if (i < params.length && classParameters[i].isInstance(params[i])) {
                        realParameters[i] = params[i];
                    } else {
                        realParameters[i] = null;
                    }
                }
            }
            try {
                subscribeMethod.getMethod().invoke(subscription.getSubscribe(), realParameters);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void register(Object object) {
        Class<?> aClass = object.getClass();
        List<SubscribeMethod> subscribes = findSubscribe(aClass);

        List<String> labels = REGISTER.get(object);
        if (null == labels) {
            labels = new ArrayList<>();
            REGISTER.put(aClass, labels);
        }
        //subscribe do
        for (SubscribeMethod subscribe : subscribes) {
            String label = subscribe.getLabel();
            List<Subscription> subscriptions = SUBSCRIBE.get(label);

            if (subscriptions == null) {
                subscriptions = new ArrayList<>();

                subscriptions.add(new Subscription(subscribe, object));
            }
            SUBSCRIBE.put(label, subscriptions);
        }
    }

    private List<SubscribeMethod> findSubscribe(Class<?> aClass) {

        List<SubscribeMethod> subscribeMethods = CACHE.get(aClass);
        if (subscribeMethods == null) {
            subscribeMethods = new ArrayList<>();
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                declaredMethod.setAccessible(true);
                if (declaredMethod != null) {

                    Subscribe annotation = declaredMethod.getAnnotation(Subscribe.class);
                    if (annotation != null) {

                        String[] values = annotation.value();
                        Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                        if (values != null) {
                            for (String value : values) {
                                subscribeMethods.add(new SubscribeMethod(declaredMethod, parameterTypes, value));
                            }
                        }
                    }
                }
            }
            CACHE.put(aClass, subscribeMethods);
        }
        return subscribeMethods;
    }

    public void unregister(Object object) {
        List<String> labels = REGISTER.get(object.getClass());
        if (labels != null) {

            for (String label : labels) {
                List<Subscription> subscriptions = SUBSCRIBE.get(label);
                if (subscriptions != null) {
                    Iterator<Subscription> iterator = subscriptions.iterator();
                    while (iterator.hasNext()) {
                        Subscription next = iterator.next();
                        //如果是同一个对象才移除
                        if (next.getSubscribe() == object) {
                            subscriptions.remove(next);
                        }
                    }
                }
            }
        }
    }

    public static void clear() {
        CACHE.clear();
        SUBSCRIBE.clear();
        REGISTER.clear();
    }
}
