package com.example.cheng.myapplication.exentions

/**
 * Created by chengbiao on 2018/1/22.
 */
sealed class TextViewSealed {
    class Left : TextViewSealed()
    class Right : TextViewSealed()
    class Top : TextViewSealed()
    class Bottom : TextViewSealed()
}