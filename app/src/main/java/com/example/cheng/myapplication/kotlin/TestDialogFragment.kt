package com.example.cheng.myapplication.kotlin

import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cheng.myapplication.R
import kotlinx.android.synthetic.main.dialog_bt_2.*

/**
 * Created by chengbiao on 2018/4/16.
 */
class TestDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater!!.inflate(R.layout.dialog_bt_2, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }
}