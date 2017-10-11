package com.xms.ui.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.xms.R;
import com.xms.base.BaseFragment;
import com.xms.bean.DfcsBean;
import com.xms.widget.Chart.MyAxisValueFormatter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by dell on 2017/8/9.
 */

public class DfcsAstjFragment extends BaseFragment {
    private View mRootView;
    @BindView(R.id.linechart)
    LineChart mChart;

    @Override
    protected View initView(LayoutInflater inflater) {
        mRootView = inflater.inflate(R.layout.fragment_dfcs, null);
        return mRootView;
    }

    private DfcsBean mbean;

    public void setdata(DfcsBean mbean, int index) {
        initchar();
        this.mbean = mbean;
        mChart.setNoDataText("暂无相关数据");
//        if (mbean.getResult().getChartData().getHour().getSData().get(0).getValue().size() <= 0) {
//            mChart.clear();
//            mChart.invalidate();
//            return;
//        }
        if (mbean.getResult().getChartData().getHour().getSData().get(0).getValue().size()<=0){
            mChart.clear();
            mChart.invalidate();
            return;
        }
        if (index == 0) {
            setData();
        } else {
            setData1();
        }

        mChart.getData().setHighlightEnabled(false);//设置确定线的是否显示
        mChart.getData().setDrawValues(false);
        //设置动画
        mChart.animateX(1500);
    }

    @Override
    public void initData() {

    }

    private Typeface mTfLight;

    private void initchar() {
        mChart.setNoDataText("暂无相关数据");
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

    }

    private void setData() {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < mbean.getResult().getChartData().getHour().getXData().size(); i++) {
            yVals1.add(new Entry(i + 1,
                    Float.parseFloat(String.valueOf(mbean.getResult().getChartData().getHour().getSData().get(0).getValue().get(i)))));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < mbean.getResult().getChartData().getHour().getXData().size(); i++) {
            yVals2.add(new Entry(i + 1,
                    Float.parseFloat(String.valueOf(mbean.getResult().getChartData().getHour().getSData().get(1).getValue().get(i)))));
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < mbean.getResult().getChartData().getHour().getXData().size(); i++) {
            yVals3.add(new Entry(i + 1,
                    Float.parseFloat(String.valueOf(mbean.getResult().getChartData().getHour().getSData().get(2).getValue().get(i)))));
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
            set1 = new LineDataSet(yVals1, mbean.getResult().getChartData().getDay().getSData().get(0).getName());
            set2 = new LineDataSet(yVals2, mbean.getResult().getChartData().getDay().getSData().get(1).getName());
            set3 = new LineDataSet(yVals3, mbean.getResult().getChartData().getDay().getSData().get(2).getName());
            setLineDataSet(set1, getResources().getColor(R.color.chart_1));
            setLineDataSet(set2, getResources().getColor(R.color.blue));
            setLineDataSet(set3, getResources().getColor(R.color.them_color_lin_z));


            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);
            mChart.setData(data);
        }
    }

    private void setData1() {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < mbean.getResult().getChartData().getHour().getXData().size(); i++) {
            yVals1.add(new Entry(Float.parseFloat(mbean.getResult().getChartData().getHour().getXData().get(i)),
                    Float.parseFloat(String.valueOf(mbean.getResult().getChartData().getHour().getSData().get(0).getValue().get(i)))));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, mbean.getResult().getChartData().getHour().getSData().get(0).getName());
          setLineDataSet(set1, getResources().getColor(R.color.chart_3));


            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);
            mChart.setData(data);
        }
    }

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
}
