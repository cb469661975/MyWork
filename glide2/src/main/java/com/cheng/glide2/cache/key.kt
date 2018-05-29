package com.cheng.glide2.cache

import java.security.MessageDigest

/**
 * Created by chengbiao on 2018/5/11.
 */
interface key {

    abstract fun updateDiskCacheKey(messageDigest: MessageDigest)

    abstract fun getKeyBytes(): ByteArray

    abstract override fun equals(o: Any?): Boolean

    abstract override fun hashCode(): Int
}