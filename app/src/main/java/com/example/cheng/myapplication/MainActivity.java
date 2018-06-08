package com.example.cheng.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.cheng.myapplication.httptest.TestSocket;
import com.example.cheng.myapplication.kotlin.Main;
import com.example.cheng.myapplication.kotlin.TestDialogFragment;
import com.example.cheng.myapplication.marqueevertical.MarqueeView;
import com.example.cheng.myapplication.model.ChatGroupGlobalGiftModel;
import com.example.cheng.myapplication.model.ShareBean;
import com.example.cheng.myapplication.model.TestData;
import com.example.cheng.myapplication.service.TestService;
import com.example.cheng.myapplication.ui.AutoScrollTextView;
import com.example.cheng.myapplication.ui.BannerPointView;
import com.example.cheng.myapplication.ui.WorldNoticeView;
import com.example.cheng.myapplication.util.FileUtil;
import com.example.cheng.myapplication.util.TestKotlinUtils;
import com.example.cheng.myapplication.util.TextLengthFilter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    LeansLayout leans_layout;
    TimePickerView timePickerView;
    private EditText et_cardNumber;
    private LivingHintView vvv;

    private WorldNoticeView worldnoticeview;

    private JSONObject jsonObject1;
//    private AutoScrollTextView autoTextView;
    private GlobalNoticeView globalnotice;
    private BannerPointView bannerPoint;
    private LinearLayout ll;
    private Subscription subscribe;
    String as = "abcdefjsonaaa";

    private MarqueeView mMarqueeView;
    private TextView tv_123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", "a=" + as.indexOf("json"));
        leans_layout = (LeansLayout) findViewById(R.id.leans_layout);
        bannerPoint = (BannerPointView) findViewById(R.id.bannerPoint);
        worldnoticeview = (WorldNoticeView) findViewById(R.id.worldnoticeview);
        globalnotice = (GlobalNoticeView) findViewById(R.id.globalnotice);
//        autoTextView = (AutoScrollTextView) findViewById(R.id.tv_text);
        mMarqueeView = (MarqueeView) findViewById(R.id.mMarqueeView);
        tv_123 = findViewById(R.id.tv_123);


        ll = (LinearLayout) findViewById(R.id.ll);
//        timePickerView= (TimePickerView) findViewById(R.id.timerpick);
//        autoTextView.init(getWindowManager());
        Log.i(TAG, "onCreate");
        String SS = "  {\"is_ios\": false, \"timestamp\": \"1508303716\", \"err_msg\": \"\", \"result\": {}, \"req_id\": 0, \"result_code\": 2000}";

        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(SS);
        Log.i("LivingHintView", jsonObject.getIntValue("result_code") + "===");

        startService(new Intent(this, TestService.class));

        for (int i = 0; i < 10; i++) {

            TextView tv = new TextView(this);

            tv.setTextSize(30);
            tv.setText("position=" + i);

        }
        initPickView();
        et_cardNumber = (EditText) findViewById(R.id.et_cardNumber);
//        et_cardNumber.addTextChangedListener(watcher);
//        InputFilter[] filters = {new TextLengthFilter(30, new TextLengthFilter.ToastCall() {
//            @Override
//            public void toast() {
//                Toast.makeText(MainActivity.this, "超过了", Toast.LENGTH_SHORT).show();
//            }
//        })};
//        et_cardNumber.setFilters(filters);


        vvv = (LivingHintView) findViewById(R.id.vvv);
        vvv.startAnimation();

        String city_province_dict = getFromAssets("city_province_dict");
        try {
            jsonObject1 = new JSONObject(city_province_dict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        main();
//        initDrawaleTouch();
        initListTest();
        try {
            new TestSocket().testHttps(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showLinkedHashMap();
    }
    int size =12;
    private void showLinkedHashMap() {
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>(12,0.75f,false);
        for (int i = 0; i <size ; i++) {
            linkedHashMap.put("i+"+i,"value+"+i);
        }
        Collection<String> values = linkedHashMap.values();
        Iterator<String> iterator = values.iterator();

        while (iterator.hasNext()){
            String next = iterator.next();
            Log.i("showLinkedHashMap",next);
        }




    }

    List<String> list = new ArrayList<>();

    private void initListTest() {
        list.add("asd0");
        list.add("asd1");
        list.add("asd2");

        list.add(1, "change1 ");

        for (int i = 0; i < list.size(); i++) {
            Log.i("list_index", list.get(i));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initDrawaleTouch() {
        tv_123.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (tv_123.getCompoundDrawables()[2] != null) {

                        boolean touchable = event.getX() > (v.getWidth() - tv_123.getTotalPaddingRight())
                                && (event.getX() < ((tv_123.getWidth() - tv_123.getPaddingRight())));
                        if (touchable) {
                            Toast.makeText(v.getContext(), "dianjidap", Toast.LENGTH_LONG).show();
                            Log.d("aasdasdasda", "asdadadad");

                        }
                    }
                }

                return false;
            }
        });
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

    public void onClickMain(View xc) throws Exception {

//        new TestDialogFragment().show(getFragmentManager(), "testDialog");


        startActivity(new Intent(this, Main2Activity.class));
//        startActivity(new Intent(this, RecycleViewActivity.class));
//        bannerPoint.setSelctPosiiton(bannerPoint.getPosition() + 1);
//        BannerPointView bannerPointView = getBannerPointView(this, 3);
//        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//        l.gravity = Gravity.CENTER_HORIZONTAL;
//        ll.addView(bannerPointView, l);

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

        subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

        List<String> list = new ArrayList<>();
        list.add("你是我的小熊啊哦小心哦");
        list.add("你是我的小熊啊哦小心哦");
        list.add("你是我的小熊啊哦小心哦");

        mMarqueeView.startWithList(list);
        mMarqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                SharedPreferences sp = getSharedPreferences("mywhatbool", Context.MODE_PRIVATE);

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                    return;
                } else {
                    Toast.makeText(MainActivity.this, "poisiiton=" + sp.getString("what", "null"), Toast.LENGTH_LONG).show();
                }
            }
        });
        SharedPreferences sp = getSharedPreferences("mywhatbool", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        // 和Map<key, value>一样保存数据，取数据也是一样简单
        edit.putString("what", "fashion" + new Random().nextInt(10));
        edit.commit();

        int b = 2;
        String newStr = "newStr";
        strOrg = newStr;
        newStr = "newStr.add";
        a = b;
        b = 3;

        ChatGroupGlobalGiftModel modelNew = new ChatGroupGlobalGiftModel();
        modelOrg = modelNew;
        modelNew.giftName = "收到了礼物";

        Log.i("show_a", "a=" + a + ",modelOrg=" + modelOrg.giftName);
    }

    private int a = 1;
    private String strOrg = "origin";
    private ChatGroupGlobalGiftModel modelOrg = null;

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
//        try {
//            loadUrl();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        worldnoticeview.show();
////        autoTextView.init(getWindowManager());
////        autoTextView.startScroll();
//
//        Toast.makeText(MainActivity.this, "我是修补匠", Toast.LENGTH_SHORT).show();
//        bannerPoint.setCount(3);
//
//        ChatGroupGlobalGiftModel chatGroupGlobalGiftModel = new ChatGroupGlobalGiftModel();
//        chatGroupGlobalGiftModel.giftName = new Random().nextInt() % 2 == 0 ? "锥子大锥子" : "锥子";
//        chatGroupGlobalGiftModel.receiverName = new Random().nextInt() % 2 == 0 ? "我是个" : "我是个asdasdasdasdasd";
//        chatGroupGlobalGiftModel.senderName = new Random().nextInt() % 2 == 0 ? "我是第十个" : "我是个asdasdasdasdasd";
//        chatGroupGlobalGiftModel.number = 1023;
//        chatGroupGlobalGiftModel.httpGroupId = "11";
//
//        final long start = System.currentTimeMillis();
//        globalnotice.showNotice(chatGroupGlobalGiftModel, new IChatGroupGlobalGiftCallback() {
//            @Override
//            public void onGiftEndPlay() {
//                Log.i("onGiftEndPlay", "onGiftEndPlay" + (System.currentTimeMillis() - start));
//            }
//        });

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            getModel();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
        }
    }


    private void getModel() {
        File sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        File sdDir1 = Environment.getDataDirectory();
        File sdDir2 = Environment.getRootDirectory();


        Log.i("filepath","sdDir"+sdDir);
        Log.i("filepath","sdDir1:"+sdDir1);
        Log.i("filepath","sdDir2:"+sdDir2);

        try {
            logFiles(sdDir.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new FileUtil().clearFile(getFilesDir()+"/yonglibao");
    }

    private void  logFiles(String path) throws IOException {

        File f= new File(path);
        if(f.isDirectory()){
            if(!f.exists()){
                f.mkdir();
            }

            String[] list = f.list();
            for (String s:list){
                Log.i("filePath",s+"");
            }

        }


    }


    public void stop(View view) {
//        autoTextView.stopScroll();
//        startActivity(ScrollStartActivity.class);

        startActivity(new Intent(MainActivity.this, ScrollStartActivity.class)
//        .putExtra("testData",new TestData(new ChatGroupGlobalGiftModel(),new ShareBean()))
        );


    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//
//    }
//
//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscribe.unsubscribe();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            boolean isShow = true;
            for (int i = 0; grantResults.length > i; i++) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    isShow = false;
                    break;
                }
            }
            Toast.makeText(MainActivity.this, isShow ? "授权成功" : "授权失败", Toast.LENGTH_LONG).show();

        }else{
            boolean isShow = true;
            for (int i = 0; grantResults.length > i; i++) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    isShow = false;
                    break;
                }
            }
            if(isShow){
                getModel();
            }
            Toast.makeText(MainActivity.this, isShow ? "授权成功" : "授权失败", Toast.LENGTH_LONG).show();
        }
    }

    private String loadUrl = "http://v.juhe.cn/historyWeather/province?key=6dcaaeea46f34369b8cdd0bda5462d6c";

    private void loadUrl() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(loadUrl)
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("response", "e：" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("response", "r：" + response.body().string());
            }
        });
    }

}
