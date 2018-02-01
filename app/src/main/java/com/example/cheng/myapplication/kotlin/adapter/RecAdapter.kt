package com.example.cheng.myapplication.kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.cheng.myapplication.R

/**
 * Created by chengbiao on 2018/2/1.
 */

class RecAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

    internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            val tv = itemView.findViewById<TextView>(R.id.tv)
        }
    }

}
