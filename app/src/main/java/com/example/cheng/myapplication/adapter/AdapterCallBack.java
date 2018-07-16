package com.example.cheng.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class AdapterCallBack extends ItemTouchHelper.Callback {

    //    法用于返回可以滑动的方向，比如说允许从右到左侧滑，允许上下拖动等。
    // 我们一般使用makeMovementFlags(int,int)或makeFlag(int, int)来构造我们的返回值。
    //    例如：要使RecyclerView的Item可以上下拖动，同时允许从右到左侧滑，但不许允许从左到右的侧滑，我们可以这样写：
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return 0;
    }
//    当用户拖动一个Item进行上下移动从旧的位置到新的位置的时候会调用该方法，在该方法内，
// 我们可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，最后返回true，
// 表示被拖动的ViewHolder已经移动到了目的位置。所以，如果要实现拖动交换位置，可以重写该方法（前提是支持上下拖动）：
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
