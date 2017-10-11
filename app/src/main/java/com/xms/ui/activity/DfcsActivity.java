package com.xms.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.bean.DfcsBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.ui.fragment.DfcsArtjFragment;
import com.xms.ui.fragment.DfcsAstjFragment;
import com.xms.ui.fragment.DfcsAytjFragment;
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

public class DfcsActivity extends BaseActivity {

    @BindView(R.id.activity_dfcs_name)
    TextView mname;
    @BindView(R.id.dfcs_text01)
    TextView mtext1;
    @BindView(R.id.dfcs_text02)
    TextView mtext2;
    @BindView(R.id.dfcs_text03)
    TextView mtext3;
    //时间选择器
    private WheelMain wheelMain;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private MyAlertDialog dialog;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> mFragmentData;
    private DfcsBean mbean;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewPager;

    @BindView(R.id.tab)
    PagerSlidingTabStrip mTab;
    private DfcsAstjFragment mfragment0;
    private DfcsArtjFragment mfragment;
    private DfcsAytjFragment mfragment1;
    @BindView(R.id.dfcs_year)
    TextView mtext_year;
    @BindView(R.id.dfcs_month)
    TextView mtext_month;
    @BindView(R.id.dfcs_day)
    TextView mtext_day;
    @BindView(R.id.select)
    TextView mselect;
    @BindView(R.id.dfcs_lin)
    LinearLayout minlayout;
    @Override
    public int getContentViewId() {
        return R.layout.activity_dfcs;
    }

    @Override
    public void initData() {
        setTitle();
        mTvForTitle.setText("电费测算");
        mname.setText((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.TITLE, ""));
        Request(dateFormat.format(new Date()));
        setvalue(dateFormat.format(new Date()));
        mtext.setText(dateFormat.format(new Date()));
        initview();//初始化滑动导航栏
        setTabsValue();//初始化导航栏滑动轴的颜色和字体颜色
        mViewPager.setNoScroll(true);
        mselect.setText( (String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.SELECT, ""));
    }
    private void setvalue(String str){
        mtext_year.setText(StringUtil.GetMyMessage(str,"-")[0]);
        mtext_month.setText(StringUtil.GetMyMessage(str,"-")[1]);
        mtext_day.setText(StringUtil.GetMyMessage(str,"-")[2]);
    }

    @BindView(R.id.mtime)
    TextView mtext;

    @OnClick({R.id.common_title_left, R.id.mtime,R.id.activity_ssdl_cx})
    void Onclick(View view){
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
                setvalue(mtext.getText().toString());
                Request(mtext.getText().toString());
                break;
            default:
                break;

        }
    }

    private void Request(final String data) {
        JSONObject object = new JSONObject();
        object.put("ddate", data);
        object.put("Id", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.COMPANYID, ""));
        object.put("userId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.USERID, ""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "estimateCost.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(DfcsActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")) {

                                Gson gson = new Gson();
                                mbean = null;
                                mbean =  gson.fromJson(response,DfcsBean.class);
                              //  ToastUtil.TextToast(mbean.getInfo());
                                mtext1.setText(mbean.getResult().getType1());
                                mtext2.setText(mbean.getResult().getType2());
                                mtext3.setText(mbean.getResult().getType3());
                                if (mbean.getResult().isIsCustomer()){
                                    minlayout.setVisibility(View.GONE);
                                }
                                Log.e("000","000");
                                String str = (String) PreferencesUtil.get(DfcsActivity.this,
                                        InterfaceDefinition.PreferencesUser.SELECT, "");
                                if (str.equals("售")){
                                    Log.e("001","001");
                                    mfragment0.setdata(mbean,0);
                                    Log.e("002","002");
                                    mfragment.setdata(mbean,0);
                                    Log.e("003","003");
                                    mfragment1.setdata(mbean,0);
                                    Log.e("004","004");
                                }else{
                                    Log.e("000","001");
                                    mfragment0.setdata(mbean,1);
                                    Log.e("000","002");
                                    mfragment.setdata(mbean,1);
                                    Log.e("000","003");
                                    mfragment1.setdata(mbean,1);
                                    Log.e("000","004");
                                }


                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("数据异常");
                        }


                    }
                });
    }


    private void time() {
        LayoutInflater inflater1 = LayoutInflater
                .from(DfcsActivity.this);
        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                null);
        ScreenInfo screenInfo1 = new ScreenInfo(DfcsActivity.this);
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
        dialog = new MyAlertDialog(DfcsActivity.this)
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
                // if (DateUtil.timeCompare(DateUtil.getStringDate(format),
                // wheelMain.getTime("%02d"))) {
                currentDate = wheelMain.getTime("%02d");
                //	String time[] = StringUtil.GetMyMessage(currentDate, " ");
                mtext.setText(currentDate);

            }
        });
        dialog.show();
    }


    /**
     * 初始化滑动导航栏
     */
    private void initview() {
        mFragmentData = new ArrayList<>();
        mfragment0=new DfcsAstjFragment();
        mfragment = new DfcsArtjFragment();
        mfragment1 = new DfcsAytjFragment();
        mFragmentData.add(mfragment0);
        mFragmentData.add(mfragment);
        mFragmentData.add(mfragment1);
        fragmentPagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            private final String[] titles = {"按时统计","按日统计", "按月统计"};

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


}
