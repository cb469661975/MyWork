package com.cheng.glide2.request

class RequestOptions {

    var errorId: Int = -1
    var overrideWidth: Int = -1
    var overrideHeigth: Int = -1
    var placeHolder: Int = -1

    fun setErrorId(errorId: Int): RequestOptions {
        this.errorId = errorId
        return this
    }

    fun setOverrideWidth(overrideWidth: Int): RequestOptions {
        this.overrideWidth = overrideWidth
        return this
    }

    fun setOverrideHeigth(overrideHeight: Int): RequestOptions {
        this.overrideHeigth = overrideHeight
        return this
    }

    fun setPlaceHolder(placeHolder: Int): RequestOptions {
        this.placeHolder = placeHolder
        return this
    }
}
