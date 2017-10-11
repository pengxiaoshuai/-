package com.xms.ui.fragment;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.xms.R;
import com.xms.base.BaseFragment;
import com.xms.bean.NhtjBean;
import com.xms.widget.Chart.MyAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class AytjFragment extends BaseFragment {
    private View mRootView;
    @BindView(R.id.barchart)
    BarChart mbarchart;
    private List<BarEntry> entries = new ArrayList<>();
    @Override
    protected View initView(LayoutInflater inflater){
        mRootView = inflater.inflate(R.layout.fragment_astj,null);
        return mRootView;
    }
    private NhtjBean mbean;
    public void setdata(NhtjBean mbean){
        this.mbean = mbean;
        if (islook){ //判断当前fragment是否被主页面加载过
            setData();
            for (IDataSet set : mbarchart.getData().getDataSets())
                set.setDrawValues(false); //设置不显示柱子上的数据
            mbarchart.animateY(1500);
        }
    }
    @Override
    public void initData() {
        if (!isFirstLoad()){
            return;
        }
        setFirstLoad(false);
        islook=true;
        initchar();
        setData();

        for (IDataSet set : mbarchart.getData().getDataSets())
            set.setDrawValues(false); //设置不显示柱子上的数据
        mbarchart.animateY(1500);
//        setData(mmonth,7000);

    }
    private Boolean islook = false;
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            //相当于Fragment的onResume
//            islook = true;
//        } else {
//            //相当于Fragment的onPause
//            islook = false;
//        }
//    }
    private void initchar(){
        BarData data = new BarData();
        data.setBarWidth(0.9f); //设置自定义条形宽度
        // data.setBarWidth(0.9f); //设置自定义条形宽度
        mbarchart.setData(data);
        mbarchart.setFitBars(true); //使x轴完全适合所有条形
        mbarchart.setDescription(null); //统计图描述描述
        //   barChart.setScaleXEnabled(false); //X轴的拉伸
        mbarchart.setDrawBorders(false);

        IAxisValueFormatter custom = new MyAxisValueFormatter(3);//定义的自定义X轴数据
        XAxis xAxis = mbarchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //X轴在底部
        xAxis.setValueFormatter(custom);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);

    }
    private Typeface mTfLight;
    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count; i++){
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            if (Math.random() * 100 < 25) {
                yVals1.add(new BarEntry(i, val, getResources().getDrawable(R.mipmap.ic_launcher)));
            } else {
                yVals1.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (mbarchart.getData() != null &&
                mbarchart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mbarchart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mbarchart.getData().notifyDataChanged();
            mbarchart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "每月用电量(千瓦时)");

            set1.setDrawIcons(false);

            set1.setColors(getResources().getColor(R.color.themcolor_chart));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            mbarchart.setData(data);
        }
    }
    private void setData() {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < mbean.getResult().getChartData().getMonth().getXData().size(); i++){
                yVals1.add(new BarEntry(Integer.parseInt(mbean.getResult().getChartData().getMonth().getXData().get(i)),
                        mbean.getResult().getChartData().getMonth().getSData().get(i)));
        }


        BarDataSet set1;

        if (mbarchart.getData() != null &&
                mbarchart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mbarchart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mbarchart.getData().notifyDataChanged();
            mbarchart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "每月用电量(kwh)");

            set1.setDrawIcons(false);


            set1.setColors(getResources().getColor(R.color.them_color_lin_z));
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);
            mbarchart.setData(data);
        }
    }
}
