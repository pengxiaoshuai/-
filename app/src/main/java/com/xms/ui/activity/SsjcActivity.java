package com.xms.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.bean.SsfhBean;
import com.xms.ui.fragment.DglFragment;
import com.xms.ui.fragment.DlFragment;
import com.xms.ui.fragment.DyFragment;
import com.xms.ui.fragment.GlysFragment;
import com.xms.widget.NoScrollViewPager;
import com.xms.widget.PagerSlidingTabStrip;
import com.xms.widget.timecheck.JudgeDate;
import com.xms.widget.timecheck.MyAlertDialog;
import com.xms.widget.timecheck.ScreenInfo;
import com.xms.widget.timecheck.WheelMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SsjcActivity extends BaseActivity {

    private String[] mItems = new String[]{"奈莫2号变"};
    private ArrayAdapter<String> adapter;
    @BindView(R.id.act_maidian_spinner)
    Spinner mspinner;
    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> mFragmentData;

    @BindView(R.id.viewpager)
    NoScrollViewPager mViewPager;

    @BindView(R.id.tab)
    PagerSlidingTabStrip mTab;
    @BindView(R.id.mtime)
    TextView mtext;
    public static ArrayList<SsfhBean> mlist;

    @Override
    public int getContentViewId() {
        return R.layout.activity_nhtj;
    }

    @Override
    public void initData() {
        if (mlist == null) {
            getjson(); //获取文件中的json数据
        }
        setTitle();
        mTvForTitle.setText("负荷");
        // 建立Adapter并且绑定数据源
        adapter = new ArrayAdapter<String>(SsjcActivity.this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        mspinner.setAdapter(adapter);
        initview();//初始化滑动导航栏
        setTabsValue();//初始化导航栏滑动轴的颜色和字体颜色
        mViewPager.setNoScroll(true);
    }

    @OnClick({R.id.common_title_left, R.id.mtime})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            case R.id.mtime:
                time();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化滑动导航栏
     */
    private void initview() {
        mFragmentData = new ArrayList<>();
        mFragmentData.add(new DglFragment());
        mFragmentData.add(new DyFragment());
        mFragmentData.add(new DlFragment());
        mFragmentData.add(new GlysFragment());
//        mFragmentData.add(new PieFragment());

        fragmentPagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            private final String[] titles = {"电功率", "电压", "电流", "功率因素"};

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

    private void time() {
        LayoutInflater inflater1 = LayoutInflater
                .from(SsjcActivity.this);
        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                null);
        ScreenInfo screenInfo1 = new ScreenInfo(SsjcActivity.this);
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
        MyAlertDialog dialog = new MyAlertDialog(SsjcActivity.this)
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

    private void getjson() {
        mlist = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("realMonitor.json"), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String str;
            StringBuilder builder = new StringBuilder();
            while ((str = br.readLine()) != null) {
                builder.append(str);
            }
            br.close();//关闭数据流
            isr.close();
            //数据已经全部获取了
            JSONArray json = JSON.parseArray(builder.toString());
            for (int i = 0; i < json.size(); i++) {
                JSONObject object = json.getJSONObject(i);
                Gson gson = new Gson();
                SsfhBean mbean = new SsfhBean();
                mbean = gson.fromJson(object.toString(), SsfhBean.class);
                mlist.add(mbean);
            }
            Log.e("+++", mlist.size() + "");
        } catch (Exception e) {
            Log.e("+++", "异常");
            e.printStackTrace();
        }
    }
}
