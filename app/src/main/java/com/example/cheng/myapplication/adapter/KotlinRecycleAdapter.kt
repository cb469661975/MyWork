package com.example.cheng.myapplication.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.example.cheng.myapplication.R

/**
 * Created by chengbiao on 2018/1/18.
 */
class KotlinRecycleAdapter : RecyclerView.Adapter<KotlinRecycleAdapter.MyViewHolder>() {
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        Log.i("holder","$position")
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(View.inflate(parent!!.context, R.layout.item_recycle, null))
    }


    override fun getItemCount(): Int {
        return 15
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }
    }

}
