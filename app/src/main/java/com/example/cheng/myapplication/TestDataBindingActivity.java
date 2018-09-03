package com.example.cheng.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by chengbiao on 2018/8/21
 */
public class TestDataBindingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_vvm);
        // ActivityBaseUseBinding是根据布局名称自动生成的
        // 代替原来的setContentView(R.layout.activity_base_use)方法
//        DataBindingUtil.setContentView(this, R.layout.activity_test_vvm);
//        User user = new User("容华", "谢后");
//        // set方法是根据data标签下的variable名称自动生成的
//        binding.setUser(user);
    }
}
