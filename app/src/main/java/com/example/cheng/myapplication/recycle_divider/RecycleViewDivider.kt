package com.example.cheng.myapplication.recycle_divider

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.example.cheng.myapplication.R

/**
 * Created by chengbiao on 2018/5/28.
 * 支持左边上边右边的控制
 */
class RecycleViewDivider() : RecyclerView.ItemDecoration() {
    val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var mDivider: Drawable
    private var mDividerSize: Int = 0

    var needRightDivider = false
    var needLeftDivider = false
    var needTopDivider = false

    constructor(mDivider: Drawable) : this() {
        this.mDivider = mDivider
        mDividerSize = mDivider.intrinsicHeight
    }

    constructor(mDivider: Drawable, needRightDivider: Boolean, needLeftDivider: Boolean, needTopDivider: Boolean) : this() {
        this.mDivider = mDivider
        this.needLeftDivider = needLeftDivider
        this.needRightDivider = needRightDivider
        this.needTopDivider = needTopDivider
        mDividerSize = mDivider.intrinsicHeight
    }

    private val DEFAULTRESOURCE: Int = R.drawable.shape_divider

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (mDivider == null) {
            mDivider = parent?.context?.resources?.getDrawable(DEFAULTRESOURCE, parent.context.theme)!!
        }
        val layoutManager = parent?.layoutManager
        when (layoutManager) {
            is GridLayoutManager -> {
                drawHDivider(c, parent, true)
            }
            is LinearLayoutManager -> {
                drawHDivider(c, parent, false)
            }
            is StaggeredGridLayoutManager -> {
                drawHDivider(c, parent, true)
            }
            else -> {
                throw Exception("error layoutManager")
            }
        }
    }

    private fun isNeedRightDivider(itemPosition: Int, parent: RecyclerView): Boolean {
        return needRightDivider && ((itemPosition + 1) % getSpanCount(parent) == 0 || itemPosition == parent.adapter.itemCount - 1)
    }

    private fun isNeedTopDivider(itemPosition: Int, parent: RecyclerView): Boolean {
        return needTopDivider && (itemPosition < getSpanCount(parent))
    }

    private fun getSpanCount(parent: RecyclerView): Int {
        // 列数
        val layoutManager = parent.layoutManager
        return (layoutManager as? GridLayoutManager)?.spanCount
                ?: if (layoutManager is StaggeredGridLayoutManager) {

                    layoutManager.spanCount
                } else {
                    1
                }
    }

    /**
     * vh divider
     */
    private fun drawHDivider(c: Canvas?, parent: RecyclerView?, isDrawVertical: Boolean) {
        val childCount: Int = parent?.childCount!!
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            if (parent.layoutManager is GridLayoutManager || parent.layoutManager is StaggeredGridLayoutManager) {
                //画底部
                drawDivider(child.left, child.bottom, child.right, child.bottom + mDividerSize, c)
                //右侧
                if (isNeedRightDivider(i, parent)) {
                    drawDivider(child.right, child.top, child.right + mDividerSize, child.bottom + mDividerSize, c)
                }
                //左侧
                if (needLeftDivider) {
                    drawDivider(child.left - mDividerSize, child.top, child.left, child.bottom + mDividerSize, c)
                }

            } else if (parent.layoutManager is LinearLayoutManager) {
                if (!isLastClum(i, childCount)) {
                    drawDivider(child.left, child.bottom, child.right, child.bottom + mDividerSize, c)
                }
            }
            if (isNeedTopDivider(i, parent)) {
                drawDivider(child.left - mDividerSize, child.top - mDividerSize, child.right + mDividerSize, child.top, c)
            }
        }
    }

    private fun drawDivider(left: Int, top: Int, right: Int, bottom: Int, c: Canvas?) {
        mDivider.setBounds(left, top, right, bottom)
        mDivider.draw(c)
    }

    private fun isLastClum(i: Int, childCount: Int): Boolean {
        return i > 0 && i == childCount - 1
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val layoutParams = view?.layoutParams as RecyclerView.LayoutParams
        val itemPosition = layoutParams.viewLayoutPosition

        var bottom = if (itemPosition == parent?.adapter?.itemCount!! - 1) {
            0
        } else {
            mDividerSize
        }

        if (parent.layoutManager is GridLayoutManager) {
            var row = if (parent.adapter.itemCount % getSpanCount(parent) == 0) {
                parent.adapter.itemCount / getSpanCount(parent)
            } else {
                parent.adapter.itemCount / getSpanCount(parent) + 1
            }
            if ((row - 1) * getSpanCount(parent) - 1 < itemPosition) {
                bottom = mDividerSize
            }
        }

        var left = if (needLeftDivider) mDividerSize else 0
        var right = if (needRightDivider && (itemPosition + 1) % getSpanCount(parent) == 0) {
            mDividerSize
        } else {
            0
        }
        var top = if (isNeedTopDivider(itemPosition, parent)) mDividerSize else 0
        outRect?.set(left, top, right, bottom)
    }
}