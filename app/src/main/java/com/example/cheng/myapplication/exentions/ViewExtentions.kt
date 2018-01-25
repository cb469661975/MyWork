package com.example.cheng.myapplication.exentions

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView

/**
 * Created by chengbiao on 2018/1/22.
 */
/**
 * 显示 View
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * 隐藏 View
 */
fun View.hide() {
    visibility = View.GONE
}

/**
 * 设置View为 View.INVISIBLE
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * 显示/隐藏 View
 * @param isShow {@code true } 显示<br>{@code false} 隐藏
 */
fun View.show(isShow: Boolean) {
    if (isShow) show() else hide()
}

/**
 * 显示/隐藏 View（注意：是 View.INVISIBLE 这个隐藏）
 * @param isShow {@code true} 显示<br>{@code false} 隐藏
 */
fun View.showOrInvisible(isShow: Boolean) {
    if (isShow) show() else invisible()
}

/**
 * 设置View 可用/不可用
 * @param enable {@code true} 可用<br>{@code false} 不可用
 */
fun View.enable(enable: Boolean) {
    isEnabled = enable
}

/**
 * 判断View是不是可见
 *
 * @return `true` 可见([View.getVisibility] == [View.VISIBLE])
 */
fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

/**
 * 判断View是不是可见
 *
 * @return `true` 可见([View.getVisibility] == [View.VISIBLE])
 */
fun View.isShow(): Boolean {
    return isVisible()
}

/**
 * 判断View是不是可见
 *
 * @return `true` 不可见([View.getVisibility] != [View.VISIBLE])
 */
fun View.isNotVisible(): Boolean {
    return !isVisible()
}

/**
 * 判断View是不是可见
 *
 * @return `true` 可见([View.getVisibility] == [View.INVISIBLE])
 */
fun View.isInvisible(): Boolean {
    return visibility == View.INVISIBLE
}

/**
 * 判断View是不是可见
 *
 * @return `true` 可见([View.getVisibility] != [View.INVISIBLE])
 */
fun View.isNotInvisible(): Boolean {
    return !isInvisible()
}

/**
 * 判断View是不是可见
 *
 * @return `true` 可见([View.getVisibility] == [View.GONE])
 */
fun View.isGone(): Boolean {
    return visibility == View.GONE
}

/**
 * 判断View是不是可见
 *
 * @return `true` 可见([View.getVisibility] != [View.GONE])
 */
fun View.isNotGone(): Boolean {
    return !isGone()
}

/**
 * 设置 [EditText] 文本，并且把光标定位到末尾
 */
fun EditText.setText2(text: CharSequence?) {
    if (text != null) {
        setText(text)
        setSelection(getText().length)
    }
}

/**
 * 设置 [EditText] 文本，并且全选文本
 */
fun EditText.setTextWithSelection(text: CharSequence?) {
    if (text != null) {
        setText(text)
        setSelection(0, getText().length)
    }
}

fun View.dp2px(value: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics).toInt()
}

fun View.px2dp(value: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, value, resources.displayMetrics).toInt()
}

fun TextView.setTextColor(colorId: Int) {
    setTextColor(resources.getColor(colorId))
}

fun TextView.setTextSizeSp(size: Float) {
    setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
}

fun TextView.setDrawable(resId: Int, enum: TextViewSealed) {
    var drawable = resources.getDrawable(resId)
    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    when (enum) {
        is TextViewSealed.Left -> setCompoundDrawables(drawable, null, null, null)
        is TextViewSealed.Right -> setCompoundDrawables(null, null, drawable, null)
        is TextViewSealed.Top -> setCompoundDrawables(null, drawable, null, null)
        is TextViewSealed.Bottom -> setCompoundDrawables(null, null, null, drawable)
    }
}

fun View.inflateView(resId: Int): View {
    return View.inflate(context, resId, null)
}












