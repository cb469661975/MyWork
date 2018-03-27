package com.example.cheng.myapplication;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.cheng.myapplication.adapter.KotlinRecycleAdapter;
import com.example.cheng.myapplication.ui.DividerGridItemDecoration;
import com.example.cheng.myapplication.ui.YLBRefreshFooter;
import com.example.cheng.myapplication.ui.YLBRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by biao.cheng on 2017/10/31.
 */

public class RecycleViewActivity extends Activity {
    RecyclerView recycleView;
    private RecyclerView.Adapter adapter;

    private int count = 14;
    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_layout);
        recycleView = findViewById(R.id.recycleView);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout);
        recycleView.setLayoutManager(new GridLayoutManager(this, 2));
        smartRefreshLayout.setEnableLoadmore(true);
        recycleView.setAdapter(new KotlinRecycleAdapter());
        initRefresh();

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                Toast.makeText(RecycleViewActivity.this, "refresh", Toast.LENGTH_SHORT).show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //结束加载
                        refreshlayout.finishRefresh();
                        //加载失败的话3秒后结束加载
//                        refreshlayout.finishRefresh(3000);
                    }
                }, 3000);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
//                Toast.makeText(RecycleViewActivity.this, "onLoadmore", Toast.LENGTH_SHORT).show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        refreshlayout.finishLoadmore();
                    }
                }, 3000);
            }
        });

        smartRefreshLayout.setHeaderHeight(100);
        smartRefreshLayout.setFooterHeight(60);
        recycleView.addItemDecoration(new DividerGridItemDecoration(this));

//        recycleView.setAdapter(adapter = new RecyclerView.Adapter() {
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new YViewHolder(View.inflate(parent.getContext(), R.layout.item_recycle, null));
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//            }
//
//            @Override
//            public int getItemCount() {
//                return count;
//            }
//
//            class YViewHolder extends RecyclerView.ViewHolder {
//                public YViewHolder(View itemView) {
//                    super(itemView);
//                }
//            }
//        });
    }

    private void initRefresh() {
        RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.smartRefreshLayout);
        refreshLayout.setRefreshHeader(new YLBRefreshHeader(this).setSpinnerStyle(SpinnerStyle.Translate));
//        refreshLayout.setRefreshFooter(new YLBRefreshFooter(this).setmSpinnerStyle(SpinnerStyle.Translate));
    }
}
