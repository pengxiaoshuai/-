package com.xms.ui.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.xms.R;
import com.xms.adapter.BaseRecyclerAdapter;
import com.xms.base.BaseActivity;
import com.xms.bean.SsdlBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.holder.BaseRecyclerHolder;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.StringUtil;
import com.xms.utils.ToastUtil;
import com.xms.widget.Chart.MyAxisValueFormatter;
import com.xms.widget.MyLinearLayoutManager;
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

import butterknife.BindView;
import butterknife.OnClick;

public class SsdlActivity extends BaseActivity {


    @BindView(R.id.linechart)
    LineChart mChart;
    @BindView(R.id.activity_ssdl_name)
    TextView mtextview;
    @BindView(R.id.ljdl1)
    TextView mljdl1;
    @BindView(R.id.ljdl2)
    TextView mljdl2;
    @BindView(R.id.ssdl_year)
    TextView myear;
    @BindView(R.id.ssdl_month)
    TextView month;
    @BindView(R.id.ssdl_day)
    TextView mday;
    //  private DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
    private DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    private String date = dateFormat2.format(new java.util.Date());
    @BindView(R.id.mtime)
    TextView mtext;
    @BindView(R.id.select)
    TextView mselect;
    @BindView(R.id.ssdl_recyclerview)
    RecyclerView mrecyclerview;
    private BaseRecyclerAdapter<SsdlBean.ResultBean.ImJaCdBean> madapter;
    private ArrayList<SsdlBean.ResultBean.ImJaCdBean> mlist;

    @Override
    public int getContentViewId() {
        return R.layout.activity_ssdl;
    }

    @Override
    public void initData() {
        setTitle();
        mTvForTitle.setText("实时电量");
        mtextview.setText((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.TITLE, ""));
        mtext.setText(dateFormat2.format(new java.util.Date()));
        inittop(dateFormat2.format(new Date()));
        mselect.setText((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.SELECT, ""));
        if (PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.SELECT, "").equals("客")) {
            mrecyclerview.setVisibility(View.VISIBLE);
        } else {
            mrecyclerview.setVisibility(View.GONE);
        }
        initrecyclerview();
        Request(date);
    }

    private void inittop(String data) {
        myear.setText(StringUtil.GetMyMessage(data, "-")[0]);
        month.setText(StringUtil.GetMyMessage(data, "-")[1]);
        mday.setText(StringUtil.GetMyMessage(data, "-")[2]);
    }

    private void initrecyclerview() {
        mlist = new ArrayList<>();
        madapter = new BaseRecyclerAdapter<SsdlBean.ResultBean.ImJaCdBean>(this, mlist, R.layout.adapter_item_ssdl) {
            @Override
            public void convert(BaseRecyclerHolder holder, SsdlBean.ResultBean.ImJaCdBean item, int position, boolean isScrolling) {
                holder.setText(R.id.adapter_item_ssdl_text, item.getName());
                LineChart chart = holder.getView(R.id.linechart);
                initChart(chart, item);
            }
        };
        mrecyclerview.setLayoutManager(new MyLinearLayoutManager(this));
        mrecyclerview.setAdapter(madapter);
    }

    private void initChart(LineChart chart, SsdlBean.ResultBean.ImJaCdBean item) {
        IAxisValueFormatter custom = new MyAxisValueFormatter(4);//定义的自定义X轴数据
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //X轴在底部
        xAxis.setTypeface(mTfLight);
        xAxis.setValueFormatter(custom); //添加入X轴数据
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);

        // no description text 没有描述的文本
        chart.getDescription().setEnabled(false);
        // enable touch gestures 支持手势触控
        chart.setTouchEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging 支持缩放和拖动
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);// 是否在折线图上添加边框
        chart.setHighlightPerDragEnabled(true);
        // 如果禁用,扩展可以在x轴和y轴分别完成
        chart.setPinchZoom(true);
        //setData(20, 15);
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < item.getMeterData().getXData().size(); i++) {
            yVals1.add(new Entry(Integer.parseInt(item.getMeterData().getXData().get(i)),
                    item.getMeterData().getSData().get(0).getValue().get(i)));
        }
        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, item.getName());
            setLineDataSet(set1, getResources().getColor(R.color.them_color_lin_z));

            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);
            chart.setData(data);

            chart.getData().setHighlightEnabled(false);//设置确定线的是否显示
            chart.getData().setDrawValues(false);
            //设置动画
            chart.animateX(1500);
        }
    }

    @OnClick({R.id.common_title_left, R.id.mtime, R.id.message_lin_top, R.id.activity_ssdl_cx})
    void Onclick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            case R.id.mtime:
                if (dialog != null) {
                    dialog.show();
                } else {
                    time();
                }
                break;
            case R.id.message_lin_top:
                //         photo();
                break;
            case R.id.activity_ssdl_cx:
                inittop(mtext.getText().toString());
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
                .url(InterfaceDefinition.Login.URL + "realtimeElectricity.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(SsdlActivity.this) {
                    @Override

                    public void onResponse(String response) {
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")) {
                                Gson gson = new Gson();
                                SsdlBean mbean = gson.fromJson(response, SsdlBean.class);
                                mljdl1.setText(mbean.getResult().getDayValue() + "");
                                mljdl2.setText(mbean.getResult().getMonthValue() + "");
                                initchar(mbean);
                                if (PreferencesUtil.get(SsdlActivity.this, InterfaceDefinition.PreferencesUser.SELECT, "").equals("客")) {
                                    mlist.clear();
                                    mlist.addAll(mbean.getResult().getImJaCd());
                                    madapter.notifyDataSetChanged();
                                }
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }

    //时间选择器
    private WheelMain wheelMain;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private MyAlertDialog dialog;

    private void time() {
        LayoutInflater inflater1 = LayoutInflater
                .from(SsdlActivity.this);
        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                null);
        ScreenInfo screenInfo1 = new ScreenInfo(SsdlActivity.this);
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
        dialog = new MyAlertDialog(SsdlActivity.this)
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

    private Typeface mTfLight;

    private void initchar(SsdlBean mbean) {
        IAxisValueFormatter custom = new MyAxisValueFormatter(4);//定义的自定义X轴数据
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //X轴在底部
        xAxis.setTypeface(mTfLight);
        xAxis.setValueFormatter(custom); //添加入X轴数据
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);

        // no description text 没有描述的文本
        mChart.getDescription().setEnabled(false);
        // enable touch gestures 支持手势触控
        mChart.setTouchEnabled(true);

        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging 支持缩放和拖动
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);// 是否在折线图上添加边框
        mChart.setHighlightPerDragEnabled(true);
        // 如果禁用,扩展可以在x轴和y轴分别完成
        mChart.setPinchZoom(true);
        //setData(20, 15);
        setData(mbean);
        mChart.getData().setHighlightEnabled(false);//设置确定线的是否显示
        mChart.getData().setDrawValues(false);
        //设置动画
        mChart.animateX(1500);
    }

    private void setData(SsdlBean mbean) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < mbean.getResult().getChartData().getXData().size(); i++) {
            yVals1.add(new Entry(i + 1, mbean.getResult().getChartData().getSData().get(0).getValue().get(i)));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < mbean.getResult().getChartData().getXData().size(); i++) {
            yVals2.add(new Entry(i + 1, mbean.getResult().getChartData().getSData().get(1).getValue().get(i)));
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < mbean.getResult().getChartData().getXData().size(); i++) {
            yVals3.add(new Entry(i + 1, mbean.getResult().getChartData().getSData().get(2).getValue().get(i)));
        }

        LineDataSet set1, set2, set3;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, mbean.getResult().getChartData().getSData().get(0).getName());
            set2 = new LineDataSet(yVals2, mbean.getResult().getChartData().getSData().get(1).getName());
            set3 = new LineDataSet(yVals3, mbean.getResult().getChartData().getSData().get(2).getName());
            setLineDataSet(set1, getResources().getColor(R.color.chart_1));
            setLineDataSet(set2, getResources().getColor(R.color.chart_2));
            setLineDataSet(set3, getResources().getColor(R.color.chart_3));


            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);
            mChart.setData(data);
        }
    }

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
//            set1 = new LineDataSet(yVals1, "A电量(kwh)");
//            set2 = new LineDataSet(yVals2, "B电量(kwh)");
//            set3 = new LineDataSet(yVals3, "C电量(kwh)");
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
    private void setLineDataSet(LineDataSet set, int color) {
//        set.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set.setColor(color);
//        //  set.setCircleColor(Color.BLACK);//设置圆点颜色
//        set.setLineWidth(1f);
//        //     set.setCircleRadius(1f);//设置小圆点的大小
//        //   set.setFillAlpha(65);
//        set.setFillColor(ColorTemplate.getHoloBlue());
//        //      set.setHighLightColor(Color.rgb(244, 117, 117));
//        set.setDrawCircleHole(false);
//        set.setDrawCircles(false);//去除连接的小圆点
//        //    set.setMode(LineDataSet.Mode.CUBIC_BEZIER);//把线条画成弧形
//        //   set.setHighLightColor(Color.BLACK);//确定线的颜色

        set.setCircleColor(color);//设置圆点颜色
        set.setCubicIntensity(0.4f);
        set.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set.setDrawCircles(true);  //设置有圆点
        set.setLineWidth(1.0f);    //设置线的宽度
        set.setCircleSize(2f);   //设置小圆的大小
        set.setColor(color);
    }


//    private  void photo(){
//        Matisse.from(SsdlActivity.this)
//                .choose(MimeType.allOf())
//                .countable(true)
//                .capture(true)
//                .captureStrategy(
//                        new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
//                .maxSelectable(9)
//            //    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(
//                        getResources().getDimensionPixelSize(R.dimen.dp120))
//                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//                .thumbnailScale(0.85f)
//                .imageEngine(new GlideEngine())
//                .forResult(REQUEST_CODE_CHOOSE);
//    }
//    private static final int REQUEST_CODE_CHOOSE = 23;
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
//         //  mAdapter.setData(Matisse.obtainResult(data), Matisse.obtainPathResult(data));
//            ToastUtil.TextToast("ok");
//        }
//    }
}
