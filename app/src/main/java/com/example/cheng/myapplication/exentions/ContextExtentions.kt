package com.example.cheng.myapplication.exentions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.util.TypedValue
import android.widget.Toast
import java.io.Serializable

/**
 * Created by chengbiao on 2018/1/22.
 */

const val TAG = "YLB_TAG"

fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(this, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

inline fun <reified T : Activity> Context.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : Activity> Context.startActivityWithData(data: Serializable) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("data", data)
    startActivity(intent)
}

inline fun <reified T : Activity> Context.startActivityByIntent(intent: Intent) {
    startActivity(intent)
}


/**
 * 1表示wifi
 */
@SuppressLint("MissingPermission")
fun Context.getNetType(): Int {
    val connectService = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectService.activeNetworkInfo

    if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
        return 0
    } else {
        // NetworkInfo不为null开始判断是网络类型
        val netType = activeNetworkInfo?.getType()
        if (netType == ConnectivityManager.TYPE_WIFI) {
            // wifi net处理
            return 1
        }
    }
    return 0
}


fun Context.dp2px(value: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics).toInt()
}

fun Context.px2dp(value: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, value, resources.displayMetrics).toInt()
}

fun Context.getColor(colorId: Int): Int {
    return resources.getColor(colorId)
}

fun Context.getDrawable(resId: Int): Drawable {
    return resources.getDrawable(resId)
}







