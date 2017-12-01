package com.example.cheng.myapplication;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cheng.myapplication.fragment.TestFragment1;
import com.example.cheng.myapplication.fragment.TestFragment2;
import com.example.cheng.myapplication.fragment.TestFragment3;

import java.util.ArrayList;
import java.util.List;

import static com.example.cheng.myapplication.R.id.viewpager;

/**
 * Created by biao.cheng on 2017/11/16.
 */

public class ViewPagerActivity extends BaseActivity {

    private ViewPager viewPager;
    private List<Fragment> listFragmemt;
    private ImageView iv_clip;
    private EditText et_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_viewpager);
        viewPager = (ViewPager) findViewById(viewpager);
        iv_clip = (ImageView) findViewById(R.id.iv_clip);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        listFragmemt = new ArrayList<>();
        listFragmemt.add(new TestFragment1());
        listFragmemt.add(new TestFragment2());
        listFragmemt.add(new TestFragment3());

        viewPager.setAdapter(new MyViewPagerAdapter(listFragmemt, getSupportFragmentManager()));
        viewPager.setCurrentItem(1);
    }


    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public MyViewPagerAdapter(List<Fragment> list, FragmentManager fm) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
    }

    int level = 0;

    public void onClickClip(View v) {
        level += 1500;
        ClipDrawable clipDrawable = (ClipDrawable) iv_clip.getDrawable();
        clipDrawable.setLevel(level);
    }

    boolean isShowNum = false;

    public void onClickChangeEtStatus(View v) {
        isShowNum = !isShowNum;
        et_pwd.setTransformationMethod(isShowNum == false ? PasswordTransformationMethod
                .getInstance() : HideReturnsTransformationMethod.getInstance());
    }
}
