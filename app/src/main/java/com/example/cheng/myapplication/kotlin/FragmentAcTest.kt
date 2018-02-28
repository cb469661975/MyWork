package com.example.cheng.myapplication.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.cheng.myapplication.BaseActivity
import com.example.cheng.myapplication.R
import com.example.cheng.myapplication.fragment.TestFragment1
import com.example.cheng.myapplication.fragment.model.Functions
import com.example.cheng.myapplication.util.OnDoubleClickListener

/**
 * Created by chengbiao on 2018/2/7.
 */
class FragmentAcTest : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fg_test_trans)

        addFragment();

        initTrans();
    }
//    .addFunction(TestFragment1.TAG)
    private fun initTrans() {

        var tF1 =supportFragmentManager.findFragmentByTag(TestFragment1.TAG) as TestFragment1
//        tF1.addFunction(Functions.NoParamNoResultFunction(TestFragment1.Fun_NO_PARAMS_RESULT) as Functions)

    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction().add(R.id.framelayout, TestFragment1(), "TestFragment1").commit()
    }
}