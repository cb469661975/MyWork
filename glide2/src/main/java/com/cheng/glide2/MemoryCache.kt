package com.cheng.glide2

import com.cheng.glide2.cache.Resource
import com.cheng.glide2.cache.key

interface MemoryCache {

    interface ResourceMoveLinstener {
        fun removeResource(resource: Resource)
    }

    fun setResourceMoveLinstener(linstener: ResourceMoveLinstener)

    fun remove(key: key)

    fun put(key: key, resource: Resource)

    fun clearMemory()

    fun trimMemory(levle: Int)
}
