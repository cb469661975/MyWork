package com.example.cheng.myapplication

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.cheng.myapplication.adapter.KotlinRecycleAdapter
import com.example.cheng.myapplication.kotlin.toast
import com.example.cheng.myapplication.util.TextLengthFilter
import com.example.cheng.myapplication.util.ViewHolder
import kotlinx.android.synthetic.main.ac_coord.recycleView
import kotlinx.android.synthetic.main.ac_coord.fab

/**
 * Created by chengbiao on 2018/1/18.
 */
class CoordActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_coord)
        fab.setOnClickListener({
            //            toast("fab clicked")
            Toast.makeText(CoordActivity@ this, "${String.format(resources.getString(R.string.test), "hahahah")}", Toast.LENGTH_LONG).show()
        })
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = KotlinRecycleAdapter()

    }

    fun toast() {

    }
}

