package com.example.cheng.myapplication.kotlin.model

/**
 * Created by chengbiao on 2017/12/18.
 */
class PersonKotlin : InterPerson {

    fun aadd(): Unit {
    }

    var age: Int = 0
        get() = field + 10

    var name: String = ""

}