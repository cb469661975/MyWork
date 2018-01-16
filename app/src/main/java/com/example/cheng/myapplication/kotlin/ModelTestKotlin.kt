package com.example.cheng.myapplication.kotlin

import android.util.Log

/**
 * Created by chengbiao on 2018/1/4.
 */
class ModelTestKotlin {
    val TAG = "ModelTestKotlin"
    fun needPrind() {
        Log.i(TAG, "needPrint to do $b")
        var c = Derived(BaseImpl(11))

        Log.i(TAG, "${c.needImpl()}===")

    }

    interface InModel {
        fun needImpl()
    }

    companion object {
        val b = 1
    }

    class Derived(b: InModel) : InModel by b

    class BaseImpl(val x: Int) : InModel {
        override fun needImpl() {
            Log.i("ModelTestKotlin","x==$x")
        }
    }

    fun main(args: Array<String>): Unit {


    }
}