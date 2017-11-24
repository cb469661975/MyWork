package com.example.cheng.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.cheng.myapplication.ui.DividerGridItemDecoration;

/**
 * Created by biao.cheng on 2017/10/31.
 */

public class RecycleViewActivity extends Activity {
    RecyclerView recycleView;
    private LinearLayoutManager llM;
    private RecyclerView.Adapter adapter;

    private int count = 14;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_layout);
        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(llM = new GridLayoutManager(this, 2));
        recycleView.addItemDecoration(new DividerGridItemDecoration(this));
        recycleView.setAdapter(adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new YViewHolder(View.inflate(parent.getContext(), R.layout.item_recycle, null));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            }

            @Override
            public int getItemCount() {
                return count;
            }

            class YViewHolder extends RecyclerView.ViewHolder {
                public YViewHolder(View itemView) {
                    super(itemView);
                }
            }

        });


    }


}
