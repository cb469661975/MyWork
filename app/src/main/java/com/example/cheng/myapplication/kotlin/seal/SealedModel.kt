package com.example.cheng.myapplication.kotlin.seal

/**
 * Created by chengbiao on 2018/1/22.
 */
sealed class SealedModel {
    object ModelA : SealedModel()
    object ModelB : SealedModel()
    object ModelC : SealedModel()
    object ModelD : SealedModel()
}