package com.example.cheng.myapplication.httptest;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by chengbiao on 2018/4/28.
 */
public class TestSocket {

    public static void main(String[] s) throws Exception {
//        doHttp();
                doHttps();

    }

    public void testHttps(Context context) throws Exception {

        SSLContext tls = SSLContext.getInstance("TLS");
        TrustManagerFactory x509 = TrustManagerFactory.getInstance("X509");
        //证书
        KeyStore keyStore = KeyStore.getInstance("BKS");
        keyStore.load(context.getAssets().open("12306.bks"),"dongnao".toCharArray());
        x509.init(keyStore);

        tls.init(null,x509.getTrustManagers(),null);
        SSLSocketFactory socketFactory = tls.getSocketFactory();
        SSLSocket socket = (SSLSocket) socketFactory.createSocket("www.12306.cn", 443);
        doHttps(socket);
    }
    static void doHttps(Socket socket) throws Exception {

        //接受数据的输入流
        final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //发送数据 输出流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    String line = null;
                    try {
                        while ((line = br.readLine()) != null) {
                            System.out.println("recv :" + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        bw.write("GET / HTTP/1.1\r\n");
        bw.write("Host: www.12306.cn\r\n\r\n");
        bw.flush();
    }

    private static void doHttps() throws IOException {
        Socket socket = SSLSocketFactory.getDefault().createSocket("www.baidu.com", 443);

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String line = null;
                    try {
                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println("recv :" + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        bufferedWriter.write("GET / HTTP/1.1\r\n");
        bufferedWriter.write("Host: www.baidu.com\r\n\r\n");
        bufferedWriter.flush();
    }

    private static void doHttp() throws MalformedURLException {
        URL url = new URL("http://www.kuaidi100.com/query?type=yuantong&postid=11111111111");
        String file = url.getFile();
        file=null==file||file.length()==0?"/":file;
        String protocol = url.getProtocol();
        int port =url.getPort();
        port=port==-1?url.getDefaultPort():port;

        System.out.println("Host:"+url.getHost());
        System.out.println("file:" + file);
        System.out.println(protocol);
        System.out.println(port);
    }
}
