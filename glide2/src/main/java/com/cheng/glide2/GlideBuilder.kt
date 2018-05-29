package com.cheng.glide2

import com.cheng.glide2.request.RequestOptions

/**
 * Created by chengbiao on 2018/5/11.
 */
class GlideBuilder {
    var glide: Glide? = null
    var bitmapPool: BitmapPool? = null
    var memoryCache: MemoryCache? = null
    var requestOptions: RequestOptions? = null

    constructor() {
        if (glide == null) {
            glide = Glide()
        }
    }

    fun build(): Glide? {
        return glide
    }
}