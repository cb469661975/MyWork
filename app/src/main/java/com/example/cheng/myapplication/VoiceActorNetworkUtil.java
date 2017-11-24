package com.example.cheng.myapplication;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tao.wang on 2017/5/9.
 */

public class VoiceActorNetworkUtil {

    private static final String TAG = "VoiceActorNetworkUtil";

    public static String getNetworkRequest(String notifyUrl) {
        int retCode = -1;
        HttpURLConnection urlConnection = null;
        try {
//            String notifyUrl = getNotifyHttpServerUrl();H
            URL url = new URL(notifyUrl);

//            SockAddr addr = ProxyProvider.getHttpProxy();
//            if (addr != null) {
//                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(addr.ip, addr.port));
//                urlConnection = (HttpURLConnection) url.openConnection(proxy);
//            } else {
            urlConnection = (HttpURLConnection) url.openConnection();
//            }
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5 * 1000);
            // 设置文件类型
            urlConnection.setRequestProperty("Content-type", "text/html");
            urlConnection.setRequestProperty("Accept-Charset", "utf-8");
            urlConnection.setRequestProperty("contentType", "utf-8");

            retCode = urlConnection.getResponseCode();

            if (retCode == 200) {
                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();

                // 创建字节输出流对象
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    os.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                os.close();
                // 返回字符串
                String result = new String(os.toByteArray());
                Log.d(TAG, "getNetworkRequest result : " + result);

                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    public static String postNetworkRequest(String notifyUrl, String postBody) {
        int retCode = -1;
        HttpURLConnection urlConnection = null;
        try {
//            String notifyUrl = getNotifyHttpServerUrl();
            URL url = new URL(notifyUrl);

//            SockAddr addr = ProxyProvider.getHttpProxy();
//            if (addr != null) {
//                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(addr.ip, addr.port));
//                urlConnection = (HttpURLConnection) url.openConnection(proxy);
//            } else {
            urlConnection = (HttpURLConnection) url.openConnection();
//            }
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5 * 1000);
            // 设置文件类型

            urlConnection.setRequestProperty("Content-type", "text/html");
            urlConnection.setRequestProperty("Accept-Charset", "utf-8");
            urlConnection.setRequestProperty("contentType", "utf-8");

            urlConnection.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
            outputStreamWriter.write(postBody);
            outputStreamWriter.flush();


            retCode = urlConnection.getResponseCode();

            if (retCode == 200) {
                InputStream is = urlConnection.getInputStream();

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                is.close();
                os.close();
                String result = new String(os.toByteArray());
                Log.d(TAG, "postNetworkRequest result : " + result);

                return result;
            }
        } catch (Exception e) {
           
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
