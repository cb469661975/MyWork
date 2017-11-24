package com.example.cheng.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ScrollView;


import com.example.cheng.myapplication.R;
import com.example.cheng.myapplication.util.CommonAdapter;
import com.example.cheng.myapplication.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by biao.cheng on 2017/10/23.
 */

public class StartOrderFragment extends Fragment {

    private MyGridView myGvCategory;
    private MyGridView mGvGender;
    private MyGridView myGvGender;
    private EditText mEtRequire;
    private ScrollView scrollView;

    private List<String> listGender;
    private List<String> listCategory;
    private int selectedGender=0;
    private int selectedCategory=0;
    private CommonAdapter<String> categoryAdapter;
    private CommonAdapter<String> genderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_start_order, null);
        myGvCategory = (MyGridView) view.findViewById(R.id.gv_category);
        myGvGender = (MyGridView) view.findViewById(R.id.gv_gender);
        mEtRequire = (EditText) view.findViewById(R.id.et_require);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCategory();
        initGender();
        initEdittext();

    }

    private void initEdittext() {

    }


    private int dp2px(int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }

    private void initGender() {
        listGender = new ArrayList<>();
        listGender.add("男女不限");
        listGender.add("男");
        listGender.add("女");

        myGvGender.setAdapter(genderAdapter=new CommonAdapter<String>(getActivity(), listGender, R.layout.item_category_order) {
            @Override
            public void convert(ViewHolder helper, String item,int position) {
                helper.setText(R.id.tv_item,item);
                if(selectedGender==position){
                    helper.getView(R.id.tv_item).setBackgroundResource(R.drawable.shape_category_selected);
                }else{
                    helper.getView(R.id.tv_item).setBackgroundResource(R.drawable.shape_category_normal);
                }
            }
        });
        myGvGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedGender=i;
                genderAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initCategory() {
        listCategory = new ArrayList<>();
        listCategory.add("男女不限");
        listCategory.add("男");
        listCategory.add("女");
        listCategory.add("男女不限");
        listCategory.add("男");
        listCategory.add("女");
        listCategory.add("男女不限");
        listCategory.add("男");
        listCategory.add("女");

        myGvCategory.setAdapter(categoryAdapter=new CommonAdapter<String>(getActivity(), listCategory, R.layout.item_category_order) {
            @Override
            public void convert(ViewHolder helper, String item,int position) {
                helper.setText(R.id.tv_item,item);
                if(selectedCategory==position){
                    helper.getView(R.id.tv_item).setBackgroundResource(R.drawable.shape_category_selected);
                }else{
                    helper.getView(R.id.tv_item).setBackgroundResource(R.drawable.shape_category_normal);
                }
            }
        });
        myGvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategory=i;
                categoryAdapter.notifyDataSetChanged();
            }
        });
    }
}
