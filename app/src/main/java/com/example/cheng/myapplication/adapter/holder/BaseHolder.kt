package com.example.cheng.myapplication.adapter.holder

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.View
import java.io.ObjectInput
import java.util.*

/**
 * Created by chengbiao on 2018/1/23.
 */
abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindHolder(t: T) {

    }

    fun unBindHolder() {

    }

    fun bindHolder(t: T, vararg : Any) {

    }

}