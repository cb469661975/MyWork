package com.cheng.glide2.cache

import java.io.File

/**
 * Created by chengbiao on 2018/5/11.
 */
interface DiskCache {
    fun get(key: key): File

    fun put(key: key, writer: Writer)
    fun delete(key: key)
    fun clear()
    interface Writer {
        fun write(file: File): Boolean
    }
}