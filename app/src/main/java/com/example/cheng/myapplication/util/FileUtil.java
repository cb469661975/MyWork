package com.example.cheng.myapplication.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by chengbiao on 2018/4/19.
 */
public class FileUtil {

    /**
     * 创建文件， 如果不存在则创建，否则返回原文件的File对象
     *
     * @param path 文件路径
     * @return 创建好的文件对象, 返回为空表示失败
     */
    synchronized public static File createFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }

        File file = new File(path);
        if (file.isFile()) {
            return file;
        }

        File parentFile = file.getParentFile();
        if (parentFile != null && (parentFile.isDirectory() || parentFile.mkdirs())) {
            try {
                if (file.createNewFile()) {
                    return file;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void clearFile(String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if(!file.exists()){
                try {
                    file.mkdir();
                    Log.i("deletes",file.exists()+"=");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            deleteFiles(file);
        }
    }

    public void deleteFiles(File file) {
        if (file.isFile()) {
            file.delete();




        } else if(file.isDirectory()) {
            File[] files = file.listFiles();
            if(files==null||files.length==0){
                file.delete();
            }else{
                for (File file1 : files) {
                    if (file1.isFile()) {
                        file1.delete();
                    } else {
                        deleteFiles(file1);
                    }
                }
            }
        }else{
            Log.i("deletes","delete"+file.exists()+"=="+file.getPath());
        }
    }
}
