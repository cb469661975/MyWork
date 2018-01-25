package com.example.cheng.myapplication.kotlin.seal

import android.provider.CalendarContract
import android.util.Log
import com.example.cheng.myapplication.kotlin.instance.Util
import com.example.cheng.myapplication.kotlin.model.PersonKotlin

/**
 * Created by chengbiao on 2018/1/22.
 */
class ChildMoldelA() {

    fun checkoutModel(model: SealedModel) {
        when (model) {
            SealedModel.ModelA -> {
                Log.i("checkoutModel", "ModelA")
            }
            SealedModel.ModelB -> {
                Log.i("checkoutModel", "ModelB")
            }
            SealedModel.ModelC -> {
                Log.i("checkoutModel", "ModelC")
            }
            SealedModel.ModelD -> {
                Log.i("checkoutModel", "ModelD")
                Util.toasts("aaa")
            }
        }
    }


}