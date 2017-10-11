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
import com.xms.bean.PcbbBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.ui.fragment.PcbbArtjFragment;
import com.xms.ui.fragment.PcbbAstjFragment;
import com.xms.ui.fragment.PcbbAytjFragment;
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

import static com.xms.R.id.mtime;

public class PcbjActivity extends BaseActivity {

    //    @BindView(R.id.linechart)
//    LineChart mChart;
    @BindView(R.id.activity_pcbj01)
    TextView mtext0;
    @BindView(R.id.pcbb_year)
    TextView myear;
    @BindView(R.id.pcbb_month)
    TextView mmonth;
    @BindView(R.id.pcbb_day)
    TextView mday;
   @BindView(R.id.pcbb_text1)
   TextView mtext11;
    @BindView(R.id.pcbb_text2)
    TextView mtext2;
    @BindView(R.id.pcbb_text3)
    TextView mtext3;
    @BindView(R.id.select)
    TextView mselect;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> mFragmentData;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.tab)
    PagerSlidingTabStrip mTab;
    private  PcbbBean mbean;
    @Override
    public int getContentViewId() {
        return R.layout.activity_pcbj;
    }

    @Override
    public void initData() {
        setTitle();
        mTvForTitle.setText("偏差报表");
        initview();//初始化滑动导航栏
        setTabsValue();//初始化导航栏滑动轴的颜色和字体颜色
        mtext0.setText((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.TITLE, ""));
        //  initchar();
        Request(dateFormat.format(new Date()));
        myear.setText(StringUtil.GetMyMessage(dateFormat.format(new Date()),"-")[0]);
        mmonth.setText(StringUtil.GetMyMessage(dateFormat.format(new Date()),"-")[1]);
        mday.setText(StringUtil.GetMyMessage(dateFormat.format(new Date()),"-")[2]);
        mtext.setText(dateFormat.format(new Date()));
        mselect.setText( (String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.SELECT, ""));
    }

    @BindView(mtime)
    TextView mtext;

    @OnClick({R.id.common_title_left,R.id.mtime,R.id.activity_ssdl_cx})
    void Onclick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            case mtime:
                if (dialog!=null){
                    dialog.show();
                }else{
                    time();
                }
                break;
            case R.id.activity_ssdl_cx:
                Request(mtext.getText().toString());
                myear.setText(StringUtil.GetMyMessage(mtext.getText().toString(),"-")[0]);
                mmonth.setText(StringUtil.GetMyMessage(mtext.getText().toString(),"-")[1]);
                mday.setText(StringUtil.GetMyMessage(mtext.getText().toString(),"-")[2]);
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
                .url(InterfaceDefinition.Login.URL + "deviateReport.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(PcbjActivity.this) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")) {
                                Gson gson = new Gson();
                                mbean = null;
                                 mbean = gson.fromJson(response, PcbbBean.class);
                                mtext11.setText(mbean.getResult().getDataValue() + "");
                                mtext2.setText(mbean.getResult().getContractDateValue() + "");
                                mtext3.setText(mbean.getResult().getDifferRatio() + "");

                                mfragment0.setdata(mbean);
                                mfragment1.setdata(mbean);
                                mfragment2.setdata(mbean);
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }
    private PcbbAstjFragment mfragment0;
    private PcbbArtjFragment mfragment1;
    private PcbbAytjFragment mfragment2;
    /**
     * 初始化滑动导航栏
     */
    private void initview() {
        mFragmentData = new ArrayList<>();
        mfragment0=new PcbbAstjFragment();
        mfragment1 = new PcbbArtjFragment();
        mfragment2 = new PcbbAytjFragment();
        mFragmentData.add(mfragment0);
        mFragmentData.add(mfragment1);
        mFragmentData.add(mfragment2);
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
    //时间选择器
    private WheelMain wheelMain;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private MyAlertDialog dialog;
    private void time() {
        LayoutInflater inflater1 = LayoutInflater
                .from(PcbjActivity.this);
        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                null);
        ScreenInfo screenInfo1 = new ScreenInfo(PcbjActivity.this);
        wheelMain = new WheelMain(timepickerview1, true, 3);
        wheelMain.screenheight = screenInfo1.getHeight();
        Calendar calendar1 = Calendar.getInstance();
        String time1 = calendar1.get(Calendar.YEAR) + "-"
                + (calendar1.get(Calendar.MONTH) + 1) + "-"
                + calendar1.get(Calendar.DAY_OF_MONTH)
                + calendar1.get(Calendar.HOUR_OF_DAY)
                + calendar1.get(Calendar.MINUTE);
        if (JudgeDate.isDate(time1, "yyyy-mm-dd")) {
            //     if (JudgeDate.isDate(time1, "yyyy-mm")) {
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
        dialog = new MyAlertDialog(PcbjActivity.this)
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
//    class  PcbjBean{
//
//        public PcbjBean(String name, String htdl, String ydl, String ljpcl) {
//            this.name = name;
//            this.htdl = htdl;
//            this.ydl = ydl;
//            this.ljpcl = ljpcl;
//        }
//
//        public String getName() {
//
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getHtdl() {
//            return htdl;
//        }
//
//        public void setHtdl(String htdl) {
//            this.htdl = htdl;
//        }
//
//        public String getYdl() {
//            return ydl;
//        }
//
//        public void setYdl(String ydl) {
//            this.ydl = ydl;
//        }
//
//        public String getLjpcl() {
//            return ljpcl;
//        }
//
//        public void setLjpcl(String ljpcl) {
//            this.ljpcl = ljpcl;
//        }
//
//        private String name;
//        private String htdl;
//        private String ydl;
//        private String ljpcl;
//    }


//    private Typeface mTfLight;
//    private void initchar(){
//        IAxisValueFormatter custom = new MyAxisValueFormatter(4);//定义的自定义X轴数据
//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //X轴在底部
//        xAxis.setTypeface(mTfLight);
//        xAxis.setValueFormatter(custom); //添加入X轴数据
//        xAxis.setDrawGridLines(false);
//        xAxis.setGranularity(1f); // only intervals of 1 day
//        xAxis.setLabelCount(7);
//
//        // no description text 没有描述的文本
//        mChart.getDescription().setEnabled(false);
//        // enable touch gestures 支持手势触控
//        mChart.setTouchEnabled(true);
//
//        mChart.setDragDecelerationFrictionCoef(0.9f);
//
//        // enable scaling and dragging 支持缩放和拖动
//        mChart.setDragEnabled(true);
//        mChart.setScaleEnabled(true);
//        mChart.setDrawGridBackground(false);// 是否在折线图上添加边框
//        mChart.setHighlightPerDragEnabled(true);
//        // 如果禁用,扩展可以在x轴和y轴分别完成
//        mChart.setPinchZoom(true);
//        setData(20, 15);
//        mChart.getData().setHighlightEnabled(false);//设置确定线的是否显示
//        mChart.getData().setDrawValues(false);
//        //设置动画
//        mChart.animateX(1500);
//    }
//
//    private void setData(int count, float range) {
//
//        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
//
//        for (int i = 0; i < count; i++) {
//            float mult = range;
//            float val = (float) (Math.random() * mult)/2+10;
//            yVals1.add(new Entry(i, val));
//        }
//
//        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
//
//        for (int i = 0; i < count; i++) {
//            float mult = range;
//            float val = (float) (Math.random() * mult)/2 +12;
//            yVals2.add(new Entry(i, val));
//        }
//
//        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
//
//        for (int i = 0; i < count; i++) {
//            float mult = range;
//            float val = (float) (Math.random() * mult)/2 + 14;
//            yVals3.add(new Entry(i, val));
//        }
//
//        LineDataSet set1 ,set2,set3;
//
//        if (mChart.getData() != null &&
//                mChart.getData().getDataSetCount() > 0) {
//            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
//            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
//            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
//            set1.setValues(yVals1);
//            set2.setValues(yVals2);
//            set3.setValues(yVals3);
//            mChart.getData().notifyDataChanged();
//            mChart.notifyDataSetChanged();
//        } else {
//            // create a dataset and give it a type
//            set1 = new LineDataSet(yVals1, "合同");
//            set2 = new LineDataSet(yVals2, "实际");
//            set3 = new LineDataSet(yVals3, "偏差率");
//            setLineDataSet(set1,getResources().getColor(R.color.chart_1));
//            setLineDataSet(set2,getResources().getColor(R.color.chart_2));
//            setLineDataSet(set3,getResources().getColor(R.color.chart_3));
//
//
//            LineData data = new LineData(set1,set2,set3);
//            data.setValueTextColor(Color.BLACK);
//            data.setValueTextSize(9f);
//            mChart.setData(data);
//        }
//    }
//    private void setLineDataSet(LineDataSet set,int color){
////        set.setAxisDependency(YAxis.AxisDependency.LEFT);
////        set.setColor(color);
////        //  set.setCircleColor(Color.BLACK);//设置圆点颜色
////        set.setLineWidth(1f);
////        //     set.setCircleRadius(1f);//设置小圆点的大小
////        //   set.setFillAlpha(65);
////        set.setFillColor(ColorTemplate.getHoloBlue());
////        //      set.setHighLightColor(Color.rgb(244, 117, 117));
////        set.setDrawCircleHole(false);
////        set.setDrawCircles(false);//去除连接的小圆点
////        //    set.setMode(LineDataSet.Mode.CUBIC_BEZIER);//把线条画成弧形
////        //   set.setHighLightColor(Color.BLACK);//确定线的颜色
//
//        set.setCircleColor(color);//设置圆点颜色
//        set.setCubicIntensity(0.4f);
//        set.setDrawFilled(false);  //设置包括的范围区域填充颜色
//        set.setDrawCircles(true);  //设置有圆点
//        set.setLineWidth(1.0f);    //设置线的宽度
//        set.setCircleSize(2f);   //设置小圆的大小
//        set.setColor(color);
//    }
}
