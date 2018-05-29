package com.cheng.glide2.cache

import android.graphics.Bitmap

/**
 * Created by chengbiao on 2018/5/11.
 */
class Resource {
    var bitmap: Bitmap? = null
    var acquired: Int = 0
    private var key: key? = null
    private var resourceLinstener: ResourceLinstener? = null

    fun recycle() {
        if (acquired > 0) return
        if (bitmap?.isRecycled!!)
            bitmap?.recycle()
    }

    fun released() {
        if (--acquired == 0) {
            resourceLinstener?.onResourceReleased(key!!, this)
        }
    }

    fun acquired() {
        if (bitmap!!.isRecycled) {
            throw Exception("bitmap is recycled")
        }
        ++acquired
    }

    interface ResourceLinstener {
        fun onResourceReleased(key: key, resource: Resource)
    }
}