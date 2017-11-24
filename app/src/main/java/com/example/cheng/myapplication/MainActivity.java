package com.example.cheng.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.TimePickerView;
import com.example.cheng.myapplication.model.ChatGroupGlobalGiftModel;
import com.example.cheng.myapplication.service.TestService;
import com.example.cheng.myapplication.ui.AutoScrollTextView;
import com.example.cheng.myapplication.ui.BannerPointView;
import com.example.cheng.myapplication.ui.WorldNoticeView;
import com.example.cheng.myapplication.util.ProvinceUtil;
import com.example.cheng.myapplication.util.TextLengthFilter;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Random;

public class MainActivity extends BaseActivity {


    LeansLayout leans_layout;
    TimePickerView timePickerView;
    private EditText et_cardNumber;
    private LivingHintView vvv;
    private WorldNoticeView worldnoticeview;


    private JSONObject jsonObject1;
    private AutoScrollTextView autoTextView;
    private GlobalNoticeView globalnotice;
    private BannerPointView bannerPoint;
    private LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leans_layout = (LeansLayout) findViewById(R.id.leans_layout);
        bannerPoint = (BannerPointView) findViewById(R.id.bannerPoint);
        worldnoticeview = (WorldNoticeView) findViewById(R.id.worldnoticeview);
        globalnotice = (GlobalNoticeView) findViewById(R.id.globalnotice);
        autoTextView = (AutoScrollTextView) findViewById(R.id.tv_text);
        ll = (LinearLayout) findViewById(R.id.ll);
//        timePickerView= (TimePickerView) findViewById(R.id.timerpick);
        autoTextView.init(getWindowManager());
        Log.i(TAG, "onCreate");
        String SS = "  {\"is_ios\": false, \"timestamp\": \"1508303716\", \"err_msg\": \"\", \"result\": {}, \"req_id\": 0, \"result_code\": 2000}";

        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(SS);
        Log.i("LivingHintView", jsonObject.getIntValue("result_code") + "===");

        startService(new Intent(this, TestService.class));

        for (int i = 0; i < 10; i++) {

            TextView tv = new TextView(this);

            tv.setTextSize(30);
            tv.setText("position=" + i);

            leans_layout.addChildView(tv);
        }
        initPickView();
        et_cardNumber = (EditText) findViewById(R.id.et_cardNumber);
//        et_cardNumber.addTextChangedListener(watcher);
        InputFilter[] filters = {new TextLengthFilter(30, new TextLengthFilter.ToastCall() {
            @Override
            public void toast() {
                Toast.makeText(MainActivity.this, "超过了", Toast.LENGTH_SHORT).show();
            }
        })};
        et_cardNumber.setFilters(filters);


        vvv = (LivingHintView) findViewById(R.id.vvv);
        vvv.startAnimation();

        String city_province_dict = getFromAssets("city_province_dict");
        try {
            jsonObject1 = new JSONObject(city_province_dict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        main();
    }

    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }

            inputReader.close();
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString().trim().replace(" ", "");
            String result = "";
            if (str.length() >= 4) {
                et_cardNumber.removeTextChangedListener(watcher);
                for (int i = 0; i < str.length(); i++) {
                    result += str.charAt(i);
                    if ((i + 1) % 4 == 0) {
                        result += " ";
                    }
                }
                if (result.endsWith(" ")) {
                    result = result.substring(0, result.length() - 1);
                }
                et_cardNumber.setText(result);
                et_cardNumber.addTextChangedListener(watcher);
                et_cardNumber.setSelection(et_cardNumber.getText().toString().length());//焦点到输入框最后位置
            }
        }
    };

    private void initPickView() {


        TimePickerView timePickerView = new TimePickerView(new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

            }
        }));
        timePickerView.show();
    }

    public void onClickMain(View xc) {
//        startActivity(new Intent(this, Main2Activity.class));
//        startActivity(new Intent(this, RecycleViewActivity.class));


        bannerPoint.setSelctPosiiton(bannerPoint.getPosition()+1);
        BannerPointView bannerPointView = getBannerPointView(this, 3);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        l.gravity = Gravity.CENTER_HORIZONTAL;
        ll.addView(bannerPointView, l);

    }

    public BannerPointView getBannerPointView(final Context ctx, final int bannerCount) {
        BannerPointView view = new BannerPointView(ctx);
        view.setSelctPosiiton(0);
        view.setCount(bannerCount);
//        if (bannerCount == 0) {
//            view.setVisibility(View.GONE);
//        }
        return view;
    }
    String TAG = "MainActivity";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    public void main() {

        Log.i("cityProvinceResult", cityProvinceResult("澳门"));
        Log.i("cityProvinceResult", cityProvinceResult("香港省"));
        Log.i("cityProvinceResult", cityProvinceResult("澳门啥回事"));
        Log.i("cityProvinceResult", cityProvinceResult("平顶山市"));
        Log.i("cityProvinceResult", cityProvinceResult("沙河"));
        Log.i("cityProvinceResult", cityProvinceResult("北京"));
        Log.i("cityProvinceResult", cityProvinceResult("重庆莎莎和"));

        String addr = "";
        String city = "2沙河";

//        int i = addr.indexOf(city);
//        Log.i("cityProvinceResult",i+"==========");
//        Log.i("cityProvinceResult",qurey.substring(0,qurey.indexOf(city))+city);
        if (!TextUtils.isEmpty(addr) && !TextUtils.isEmpty(city) && addr.contains(city)) {
            city = addr.substring(0, addr.indexOf(city)) + city;
            if (city.contains("中国")) {
                city = city.replace("中国", "");
            }
            if (!city.endsWith("市")) {
                city = city + "市";
            }
        }
        Log.i("cityProvinceResult", city + "==========");

//        ProvinceUtil.getProvinceCity("香港省");
//        ProvinceUtil.getProvinceCity("澳门啥回事");
//        ProvinceUtil.getProvinceCity("河南省平顶山市");
//        ProvinceUtil.getProvinceCity("福建省省沙河县城");
    }

    private String formatQureyCity(String city) {
        String result = city;
        if (!TextUtils.isEmpty(city)) {
            if (city.endsWith("市")) {
                result = city.substring(0, city.length() - 1);
            }
        }
        return result;
    }


    private String qureyProvince(String city) {
        String result = "";
        try {
            result = jsonObject1.getString(formatQureyCity(city));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String cityProvinceResult(String city) {
        String result = "";

//        if(TextUtils.isEmpty(city)){
//            return "中国";
//        }
        if (city.startsWith("上海")) {
            return "上海市";
        }
        if (city.startsWith("北京")) {
            return "北京市";
        }
        if (city.startsWith("天津")) {
            return "天津市";
        }
        if (city.startsWith("重庆")) {
            return "重庆市";
        }
        if (city.startsWith("香港")) {
            return "香港特别行政区";
        }
        if (city.startsWith("澳门")) {
            return "澳门特别行政区";
        }
        if (city.startsWith("澳门")) {
            return "澳门特别行政区";
        }
        if (TextUtils.isEmpty(qureyProvince(city))) {
            return city;
        }
        if (qureyProvince(city).startsWith("内蒙古")) {
            result = "内蒙古自治区" + city;
        }
        if (qureyProvince(city).startsWith("新疆")) {
            result = "新疆自维吾尔自治区" + city;
        }
        if (qureyProvince(city).startsWith("广西")) {
            result = "广西壮族自治区" + city;
        }
        if (qureyProvince(city).startsWith("宁夏")) {
            result = "宁夏回族自治区" + city;
        }
        if (qureyProvince(city).startsWith("西藏")) {
            result = "西藏自治区" + city;
        }
        return result;
    }

    public void start(View view) {
//        worldnoticeview.show();
//        autoTextView.init(getWindowManager());
//        autoTextView.startScroll();
        bannerPoint.setCount(3);

        ChatGroupGlobalGiftModel chatGroupGlobalGiftModel = new ChatGroupGlobalGiftModel();
        chatGroupGlobalGiftModel.giftName=new Random().nextInt()%2==0?"锥子大锥子":"锥子";
        chatGroupGlobalGiftModel.receiverName=new Random().nextInt()%2==0?"我是个":"我是个asdasdasdasdasd";
        chatGroupGlobalGiftModel.senderName=new Random().nextInt()%2==0?"我是第十个":"我是个asdasdasdasdasd";
        chatGroupGlobalGiftModel.number=1023;
        chatGroupGlobalGiftModel.httpGroupId="11";

        final long  start =System.currentTimeMillis();
        globalnotice.showNotice(chatGroupGlobalGiftModel, new IChatGroupGlobalGiftCallback() {
            @Override
            public void onGiftEndPlay() {
            Log.i("onGiftEndPlay","onGiftEndPlay"+(System.currentTimeMillis()-start));
            }
        });
    }

    private void getModel(){



    }

    public void stop(View view) {
        autoTextView.stopScroll();
        startActivity(ScrollStartActivity.class);
    }

}
