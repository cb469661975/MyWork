package com.example.cheng.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.cheng.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taohong on 2017/7/27.
 */
public class GiftVipAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> list = new ArrayList<>();
    private int selectedPosition = -1;
    private boolean isVipSelected = false;
    private boolean isShow1Coin1Minuate = false;


    public boolean isShow1Coin1Minuate() {
        return isShow1Coin1Minuate;
    }

    public void setShow1Coin1Minuate(boolean show1Coin1Minuate) {
        isShow1Coin1Minuate = show1Coin1Minuate;
    }

    public boolean isVipSelected() {
        return isVipSelected;
    }

    public void setVipSelected(boolean vipSelected) {
        isVipSelected = vipSelected;
    }

    public GiftVipAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setSelected(int index) {
        if (index >= list.size()) return;
        selectedPosition = index;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
            convertView = mInflater.inflate(R.layout.vip_gift_item, parent, false);
            holder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String data = list.get(position);
        holder.tv_item.setText(data);

        return convertView;
    }

    static class ViewHolder {
        TextView tv_item;
    }

    public int getRank() {
        return 3;
    }
}
