package com.example.cheng.myapplication.kotlin

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.cheng.myapplication.R


/**
 * Created by chengbiao on 2017/12/4.
 */
class KotlinActivity : Activity(), View.OnClickListener {

    var id: Int = 100_201

    val tag = "KotlinActivity"

    override fun onClick(p0: View?) {
        Toast.makeText(this, "哈哈哈哈${getName(null)}", Toast.LENGTH_LONG).show()
        var m: ModelTestKotlin = ModelTestKotlin()
        m.needPrind()

//        ChildMoldelA().checkoutModel(SealedModel.ModelA)
//        ChildMoldelA().checkoutModel(SealedModel.ModelB)
//        ChildMoldelA().checkoutModel(SealedModel.ModelD)
        showTest()
        var user =TestUser()

        user.name ="xiaozhang"
        user.name ="xiaoLi"

    }

    private fun showTest() {

        test1ParamsM(1)
        ::testNoParamsM
    }

    var testNoParamsM = fun() {
            Log.i("testNoParamsM", "type=====testNoParamsM")
    }
    var test1ParamsM = fun(type: Int) {
        Log.i("test1ParamsM", "type$type")
    }

    lateinit var tv_click: TextView
    var mMainKotlin: MainKotlin = MainKotlin()

    lateinit var iv_animlist: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_kotlin)
        tv_click = findViewById(R.id.tv_click)
//        tv_click.setOnClickListener(this)
        tv_click.setOnClickListener(View.OnClickListener { view ->
            onClick(view)
        })
        mMainKotlin.main()
        iv_animlist = findViewById(R.id.iv_animlist)

        val drawable = iv_animlist.drawable;
        if (drawable is AnimationDrawable) {
            drawable.start()
        }
        main()
    }

    //类型后面加?表示可为空
    var age: String? = "23"
    //抛出空指针异常
    val ages = age!!.toInt()
    //不做处理返回 null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt() ?: -1

    fun getName(id: Integer?): String? = "1" + id

    fun main(): Unit {

//        Log.i("String",it)

        "Strubf".run {
            Log.i("setTextColor", this.toString())
        }.let {
            Log.i("setTextColor", it.toString())
        }

    }
}


