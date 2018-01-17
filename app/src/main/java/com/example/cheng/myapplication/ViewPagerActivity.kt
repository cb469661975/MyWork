package com.example.cheng.myapplication

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TableLayout

import com.example.cheng.myapplication.fragment.TestFragment1
import com.example.cheng.myapplication.fragment.TestFragment2
import com.example.cheng.myapplication.fragment.TestFragment3

import com.example.cheng.myapplication.R.id.viewpager
import kotlin.collections.ArrayList

/**
 * Created by biao.cheng on 2017/11/16.
 */

class ViewPagerActivity : BaseActivity() {

    private var viewPager: ViewPager? = null
    private var listFragmemt: MutableList<Fragment>? = null
    private var iv_clip: ImageView? = null
    private var et_pwd: EditText? = null

    internal var level = 0

    internal var isShowNum = false

    var tabLayout: TableLayout? = null

    var titles = arrayOf("我的", "我的你的", "我", "大家有空来")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_viewpager)
        viewPager = findViewById<View>(viewpager) as ViewPager
        iv_clip = findViewById<View>(R.id.iv_clip) as ImageView
        et_pwd = findViewById<View>(R.id.et_pwd) as EditText

        listFragmemt = ArrayList()
        listFragmemt!!.add(TestFragment1())
        listFragmemt!!.add(TestFragment2())
        listFragmemt!!.add(TestFragment3())



        viewPager!!.adapter = MyViewPagerAdapter(listFragmemt as ArrayList<Fragment>, supportFragmentManager)
        viewPager!!.currentItem = 1
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.i("addOnPageChangeListener", position.toString() + "====" + positionOffset + "")
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


    private inner class MyViewPagerAdapter(private val list: List<Fragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list[position]
        }
    }

    fun onClickClip(v: View) {
        level += 1500
        val clipDrawable = iv_clip!!.drawable as ClipDrawable
        clipDrawable.level = level
    }

    fun onClickChangeEtStatus(v: View) {
        isShowNum = !isShowNum
        et_pwd!!.transformationMethod = if (isShowNum == false)
            PasswordTransformationMethod
                    .getInstance()
        else
            HideReturnsTransformationMethod.getInstance()
    }
}
