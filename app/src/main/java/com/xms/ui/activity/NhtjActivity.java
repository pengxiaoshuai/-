package com.xms.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.bean.NhtjBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.ui.fragment.ArtjFragment;
import com.xms.ui.fragment.AstjFragment;
import com.xms.ui.fragment.AytjFragment;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.StringUtil;
import com.xms.utils.ToastUtil;
import com.xms.widget.NoScrollViewPager;
import com.xms.widget.PagerSlidingTabStrip;
import com.xms.widget.timecheck.JudgeDate;
import com.xms.widget.timecheck.MyAlertDialog;
import com.xms.widget.timecheck.ScreenInfo;
import com.xms.widget.timecheck.WheelMain;
import com.zhy.http.okhttp.OkHttpUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NhtjActivity extends BaseActivity {
//    private String[] mItems = new String[]{"莫奈2号变"};
//    private ArrayAdapter<String> adapter;
//    @BindView(R.id.act_maidian_spinner)
//    Spinner mspinner;
    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> mFragmentData;

    @BindView(R.id.viewpager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.activity_ssdl_name)
    TextView mname;
    @BindView(R.id.ssdl_year)
    TextView myear;
    @BindView(R.id.ssdl_month)
    TextView mmonth;
    @BindView(R.id.ssdl_day)
    TextView mday;
    @BindView(R.id.tab)
    PagerSlidingTabStrip mTab;
    @BindView(R.id.mtime)
    TextView mtext;
    private NhtjBean mbean;
    @BindView(R.id.select)
    TextView mselect;
    @Override
    public int getContentViewId(){
        return R.layout.activity_nhtj;
    }

    @Override
    public void initData(){
        setTitle();
        mTvForTitle.setText("能耗统计");
//        // 建立Adapter并且绑定数据源
//        adapter = new ArrayAdapter<String>(NhtjActivity.this, android.R.layout.simple_spinner_item, mItems);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //绑定 Adapter到控件
//        mspinner.setAdapter(adapter);
        initview();//初始化滑动导航栏
        setTabsValue();//初始化导航栏滑动轴的颜色和字体颜色
        mViewPager.setNoScroll(true);
        mname.setText((String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.TITLE, ""));
        String data = dateFormat.format(new Date());
        Request(data);
        inittop(data);
        mtext.setText(data);
        mselect.setText( (String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.SELECT, ""));
    }
    private void inittop(String data){
        myear.setText(StringUtil.GetMyMessage(data,"-")[0]);
        mmonth.setText(StringUtil.GetMyMessage(data,"-")[1]);
        mday.setText(StringUtil.GetMyMessage(data,"-")[2]);
    }
    @OnClick({R.id.common_title_left,R.id.mtime,R.id.activity_ssdl_cx})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.common_title_left:
                finish();
                break;
            case R.id.mtime:
                if (dialog!=null){
                    dialog.show();
                }else{
                    time();
                }
                break;
            case R.id.activity_ssdl_cx:
                inittop(mtext.getText().toString());
                Request(mtext.getText().toString());
                break;
            default:
                break;
        }
    }
    private AstjFragment mfragmentastj;
    private ArtjFragment mfragmemtartj;
    private AytjFragment mfragmentaytj;
    /**
     * 初始化滑动导航栏
     */
    private void initview(){
        mfragmentastj = new AstjFragment();
        mfragmemtartj= new ArtjFragment();
        mfragmentaytj  =new AytjFragment();
        mFragmentData = new ArrayList<>();
        mFragmentData.add(mfragmentastj);
        mFragmentData.add(mfragmemtartj);
        mFragmentData.add(mfragmentaytj);
        fragmentPagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            private final String[] titles = { "按时统计", "按日统计", "按月统计" };

            @Override
            public int getCount() {
                return mFragmentData.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragmentData.get(arg0);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        mTab.setViewPager(mViewPager);

    }

    /**
     * 初始化导航栏滑动轴的颜色和字体颜色
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        mTab.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        mTab.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        mTab.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, getResources()
                        .getDisplayMetrics()));
        // 设置Tab Indicator的高度
        mTab.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                        .getDisplayMetrics()));
        // 设置Tab标题文字的大小
        mTab.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, getResources()
                        .getDisplayMetrics()));
        // 设置Tab Indicator的颜色
        mTab.setIndicatorColor(getResources().getColor(R.color.them_color));
        // 设置选中Tab文字的颜色
        mTab.setSelectedTextColor(getResources().getColor(R.color.them_color));
        // 取消点击Tab时的背景色
        mTab.setTabBackground(0);
    }
    //时间选择器
    private WheelMain wheelMain;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private MyAlertDialog dialog;
    private void time(){
        LayoutInflater inflater1 = LayoutInflater
                .from(NhtjActivity.this);
        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                null);
        ScreenInfo screenInfo1 = new ScreenInfo(NhtjActivity.this);
        wheelMain = new WheelMain(timepickerview1, true, 3);
        wheelMain.screenheight = screenInfo1.getHeight();
        Calendar calendar1 = Calendar.getInstance();
        String time1 = calendar1.get(Calendar.YEAR) + "-"
                + (calendar1.get(Calendar.MONTH) + 1) + "-"
                + calendar1.get(Calendar.DAY_OF_MONTH)
                + calendar1.get(Calendar.HOUR_OF_DAY)
                + calendar1.get(Calendar.MINUTE);
        if (JudgeDate.isDate(time1, "yyyy-mm-dd")) {
            try {
                calendar1.setTime(dateFormat.parse(time1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        int day = calendar1.get(Calendar.DAY_OF_MONTH);
        //需要精确到分就取消注释
//		int hour = calendar1.get(Calendar.HOUR_OF_DAY);
//		int minute = calendar1.get(Calendar.MINUTE);
        wheelMain.initDateTimePicker(year, month, day
                //, hour, minute
        );
        dialog = new MyAlertDialog(NhtjActivity.this)
                .builder().setTitle("选择日期").setView(timepickerview1)
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDate = "";
                String format = "yyyy-MM-dd  HH:mm";
                // if (DateUtil.timeCompare(DateUtil.getStringDate(format),
                // wheelMain.getTime("%02d"))) {
                currentDate = wheelMain.getTime("%02d");
                //	String time[] = StringUtil.GetMyMessage(currentDate, " ");
                mtext.setText(currentDate);
            }
        });
        dialog.show();
    }
    private void Request(final String data){
        JSONObject object=new JSONObject();
        object.put("ddate",data);
        object.put("Id", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.COMPANYID,""));
        object.put("userId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.USERID, ""));
        Log.e("请求报文","{"+InterfaceDefinition.ICommonKey.REQUEST_DATA+":"+object.toString()+"}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "electricityReport.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA,object.toString())
                .build()
                .execute(new MyStringCallback(NhtjActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("请求数据",""+response);
                            JSONObject object= JSON.parseObject(response);
                            if (object.getBoolean("success")){
                                Gson gson = new Gson();
                                mbean = null;
                                mbean=gson.fromJson(response,NhtjBean.class);
                                mfragmentastj.setdata(mbean);
                                mfragmemtartj.setdata(mbean);
                                mfragmentaytj.setdata(mbean);
                            }else{
                            ToastUtil.TextToast(object.getString("info"));
                        }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }

}
