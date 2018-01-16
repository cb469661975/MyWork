package com.example.cheng.myapplication

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by chengbiao on 2017/12/4.
 */

class CropTestImageView:ImageView{


    internal var width:Int=10;

    constructor(context:Context) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun init(context:Context){


    }

}
