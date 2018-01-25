package com.example.cheng.myapplication.kotlin

import android.content.Context
import android.widget.Toast

/**
 * Created by chengbiao on 2018/1/16.
 */


fun Context.toast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_LONG).show()
}