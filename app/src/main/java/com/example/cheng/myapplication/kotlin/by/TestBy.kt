package com.example.cheng.myapplication.kotlin.by

import android.util.Log
import kotlin.reflect.KProperty

/**
 * Created by chengbiao on 2018/1/22.
 */
class TestBy {


    companion object {
        class Delgte() {

            operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
                return "a"
            }

            operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
                Log.i("KProperty", value)
            }
        }
    }
}