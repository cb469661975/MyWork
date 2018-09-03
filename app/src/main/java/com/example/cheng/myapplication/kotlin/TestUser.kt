package com.example.cheng.myapplication.kotlin

import android.util.Log
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by chengbiao on 2018/8/31
 */
class TestUser {
    //    var name:String by Delegates.vetoable("xiaoming",o)
//    var name: String by Delegates.observable("<no name>", { property: KProperty<*>, oldValue: String, newValue: String ->
//        Log.i("TextObserable", "oldvalue=$oldValue,newVlaue=$newValue")
//    })
    var name:String =""
}