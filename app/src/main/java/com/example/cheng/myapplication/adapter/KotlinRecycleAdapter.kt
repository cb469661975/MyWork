package com.example.cheng.myapplication.adapter

import android.animation.ValueAnimator
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.cheng.myapplication.R

/**
 * Created by chengbiao on 2018/1/18.
 */
class KotlinRecycleAdapter : RecyclerView.Adapter<KotlinRecycleAdapter.MyViewHolder>() {

    var isSlected: Boolean = false
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

        holder?.itemView?.setOnClickListener {
//            val params = holder.llbottom.layoutParams
//            params.height  = 300
//            holder.llbottom.requestLayout()
            isSlected = !isSlected
            if(isSlected){
                holder.llbottom.visibility = View.VISIBLE
            }else{
                holder.llbottom.visibility = View.GONE
            }
            notifyItemChanged(position)
//            val animator = ValueAnimator.ofInt(0, 300)
//            animator.addUpdateListener { animation ->
//                val animatedValue = animation?.animatedValue as Int
//                if(animatedValue>=300){
//                    lastValue = 0
//                }
//                if(animatedValue -lastValue<20){
//
//                }else{
//                    lastValue = animatedValue
//                    val params = holder.llbottom.layoutParams
//                    params.height  = animatedValue
//                    holder.llbottom.requestLayout()
//                    notifyItemChanged(position)
//
//                }
//            }
//            animator.duration = 300
//            animator.start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(View.inflate(parent!!.context, R.layout.item_recycle, null))
    }


    override fun getItemCount(): Int {
        return 10
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var llbottom: LinearLayout

        init {
            imageView = itemView.findViewById(R.id.iv_image)
            llbottom = itemView.findViewById(R.id.ll_bottom)
        }
    }

}
