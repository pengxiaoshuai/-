package com.xms.ui.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.xms.R;
import com.xms.base.BaseFragment;
import com.xms.ui.activity.SsjcActivity;
import com.xms.widget.Chart.MyAxisValueFormatter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by dell on 2017/7/18.
 */

public class GlysFragment extends BaseFragment{
    private View mRootView;
    @BindView(R.id.linechart)
    LineChart mChart;
    @Override
    protected View initView(LayoutInflater inflater) {
        mRootView = inflater.inflate(R.layout.fragment_dl,null);
        return mRootView;
    }
    private Typeface mTfLight;
    @Override
    public void initData(){
        if (!isFirstLoad()){
            return;
        }
        setFirstLoad(false);

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
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        // 如果禁用,扩展可以在x轴和y轴分别完成
        mChart.setPinchZoom(true);
        //添加数据
        //setData(100, 15);
        setData();
        mChart.getData().setHighlightEnabled(false);//设置确定线的是否显示
        //设置动画
        mChart.animateX(1500);
      //  mChart.invalidate();
    }

    private void setData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult)/2+5;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult)/2 +8;
            yVals2.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult)/2 + 11;
            yVals3.add(new Entry(i, val));
        }
        ArrayList<Entry> yVals4 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult)/2 + 20;
            yVals4.add(new Entry(i, val));
        }

        LineDataSet set1 ,set2,set3,set4;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set4 = (LineDataSet) mChart.getData().getDataSetByIndex(3);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            set4.setValues(yVals4);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "A相功率因素");
            set2 = new LineDataSet(yVals2, "B相功率因素");
            set3 = new LineDataSet(yVals3, "C相功率因素");
            set4 = new LineDataSet(yVals4, "总功率因素");
            setLineDataSet(set1,getResources().getColor(R.color.chart_1));
            setLineDataSet(set2,getResources().getColor(R.color.chart_2));
            setLineDataSet(set3,getResources().getColor(R.color.chart_3));
            setLineDataSet(set4,getResources().getColor(R.color.blue));

            // create a data object with the datasets
            LineData data = new LineData(set1,set2,set3,set4);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            // set data
            mChart.setData(data);
        }
    }
    private void setData() {
        ArrayList<Entry> yVals0 = new ArrayList<Entry>();

        for (int i = 0; i < SsjcActivity.mlist.get(3).getSData().get(0).getValue().size(); i++){
            yVals0.add(new Entry(i, SsjcActivity.mlist.get(3).getSData().get(0).getValue().get(i)));
        }
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < SsjcActivity.mlist.get(3).getSData().get(1).getValue().size(); i++){
            yVals1.add(new Entry(i, SsjcActivity.mlist.get(3).getSData().get(1).getValue().get(i)));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < SsjcActivity.mlist.get(3).getSData().get(2).getValue().size(); i++){
            yVals2.add(new Entry(i, SsjcActivity.mlist.get(3).getSData().get(2).getValue().get(i)));
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < SsjcActivity.mlist.get(3).getSData().get(3).getValue().size(); i++) {
            yVals3.add(new Entry(i, SsjcActivity.mlist.get(3).getSData().get(3).getValue().get(i)));
        }




        LineDataSet set0,set1 ,set2,set3;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set0 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(3);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(4);
            set0.setValues(yVals0);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);

            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set0 = new LineDataSet(yVals0, "总功率因素");
            set1 = new LineDataSet(yVals1, "A功率因素");
            set2 = new LineDataSet(yVals2, "B功率因素");
            set3 = new LineDataSet(yVals3, "C功率因素");

            setLineDataSet(set0,getResources().getColor(R.color.colorPrimaryDark));
            setLineDataSet(set1,getResources().getColor(R.color.chart_1));
            setLineDataSet(set2,getResources().getColor(R.color.chart_2));
            setLineDataSet(set3,getResources().getColor(R.color.chart_3));

            LineData data = new LineData(set0,set1,set2,set3);
//            LineData data = new LineData(quarterStrs,allLinesList);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            // set data
            mChart.setData(data);
        }
    }
    private void setLineDataSet(LineDataSet set,int color){
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(color);
  //      set.setCircleColor(Color.BLUE);//设置圆点颜色
        set.setLineWidth(1f);
  //      set.setCircleRadius(1f);//设置小圆点的大小
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
  //      set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setDrawCircleHole(false);
        set.setDrawCircles(false);//去除连接的小圆点
    //    set.setMode(LineDataSet.Mode.CUBIC_BEZIER);//把线条画成弧形
        set.setHighLightColor(Color.BLACK);
    }

}
