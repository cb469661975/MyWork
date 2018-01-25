package com.example.cheng.myapplication.kotlin.by

import android.util.Log

/**
 * Created by chengbiao on 2018/1/22.
 */
class Do(var s: String, var a: Int) : IDo {

    var doType: String = "play"


    override fun doIt(str: String) {
        Log.i("doInit", s + "$a")

    }

}

class Do2(var iDo: IDo) : IDo by iDo