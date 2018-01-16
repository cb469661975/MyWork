package com.example.cheng.myapplication.kotlin

import android.util.Log

/**
 * Created by chengbiao on 2017/12/18.
 */
class MainKotlin() {
    private val TAG = "MainKotlin";

    val num1: Int = 1
    val str1: String = "i am str"
    var num2: Int = 0
    var str2 = null
    lateinit var list: ArrayList<String>

    fun main() {
        Log.i(
                TAG,
                "do  main==${num1}==$num1==${str1.length}" + checkoutString(str2)
        )
        fun1("what")
        fun1(this)
        showList()
        showContinue()
        showLabel()
    }

    fun MutableList<Int>.swip(x: Int, y: Int) {
        val temp = this[x] // this 对应 list
        this[x] = this[y]
        this[y] = temp
    }

    private fun showLabel() {
        val l: MutableList<Int> = mutableListOf(1, 2, 3)
        l.swip(0, 2)

        for ((index, value) in l.withIndex()) {
            Log.i(TAG, "show index=$index,value=$value")
        }
    }


    private fun showContinue(): Unit {

    }

    private fun showList() {
        list = ArrayList()
        list.add("a")
        list.add("b")
        list.add("c")
        list.add("d")
        //循环体 循环输出的方式
        for1@ for ((index: Int, value) in list.withIndex()) {
            Log.i(TAG, "i=$index$value   11111")
        }
        loop@ for (index: Int in list.indices) {
            Log.i(TAG, "i=$index${list.get(index)}  2222")
            if (index == 1) {
                break
            }
        }
        for3@ for (i in list) {
            for (i in list) {
                Log.i(TAG, "i=$i 3333")
                if (i == "a") {
                    break@for3
                }
            }
        }
    }

    fun fun1(x: Any): Unit {
        if (x is String) {
            Log.i(TAG, x + "")
        } else if (x is MainKotlin) {
            Log.i(TAG, "MainKotlin")
        }
    }

    fun checkoutString(str: String?): String {
        return str ?: "空"
        val asd: String = "1"
        return asd;
    }
}