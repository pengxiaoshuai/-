package com.xms.ui.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.xms.R;
import com.xms.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by dell on 2017/7/18.
 */

public class PieFragment extends BaseFragment{
    private View mRootView;
    @BindView(R.id.piechart)
    PieChart mChart;
    @Override
    protected View initView(LayoutInflater inflater){
        mRootView = inflater.inflate(R.layout.fragment_pie,null);
        return mRootView;
    }
    private Typeface mTfRegular;
    @Override
    public void initData(){
        if (!isFirstLoad()){
            return;
        }
        setFirstLoad(false);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 20, 5, 5);
        mChart.setDescription(null);
        mChart.setDragDecelerationFrictionCoef(0.95f);
//        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());
        mChart.setDrawEntryLabels(true); //是否允许在环形里显示名称
      //  mChart.setDragDecelerationEnabled(false); // 如果设置为true，手指滑动抛掷图表后继续减速滚动。 默认值：true。
        mChart.setTouchEnabled(false);// 启用/禁用与图表的所有可能的触摸交互。
//        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

//        mChart.setHoleRadius(58f);//内部占白圆
//        mChart.setTransparentCircleRadius(61f);//内部圆

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
   //     mChart.setOnChartValueSelectedListener(this);

        setData(3, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);


//        Legend l = mChart.getLegend();//调整类别的位置信息
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
        mChart.invalidate();
    }

    private void setData(int count, float range) {
        String[] strings =new String[]{"尖","峰","谷"};
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
//        for (int i = 0; i < count ; i++) {
//            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
//                    strings[i]
//                    ,
//                    getResources().getDrawable(R.mipmap.ic_launcher)));
//        }
        entries.add(new PieEntry(2321,
                strings[0]+"2321 kwh",
                getResources().getDrawable(R.mipmap.ic_launcher)));
        entries.add(new PieEntry(3661,
                strings[1]+"3661 kwh",
                getResources().getDrawable(R.mipmap.ic_launcher)));
        entries.add(new PieEntry(1000,
                strings[2]+"1000 kwh",
                getResources().getDrawable(R.mipmap.ic_launcher)));

        PieDataSet dataSet = new PieDataSet(entries, "");//可以修改描述信息

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ColorTemplate.MATERIAL_COLORS[0]);
        colors.add(ColorTemplate.MATERIAL_COLORS[1]);
        colors.add(ColorTemplate.MATERIAL_COLORS[2]);
//        colors.add(ColorTemplate.MATERIAL_COLORS[3]);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
    //    data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }
    //自己定义的String
    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("6383\nkwh");
        s.setSpan(new RelativeSizeSpan(2.4f), 0, s.length()-3, 0); //字体大小，到第几位
     //   SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
    //    s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0); //字体大小，到第几位
     //   s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0); //设置没有style，第14位到倒数第15位
    //    s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);//设置颜色 第14位到倒数第15位
   //     s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0); //设置大小 第14位到倒数第15位
    //    s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);//设置一种样式 从倒数第14位到末尾
   //     s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0); //设置颜色 从倒数第14位到末尾
        return s;
    }
}
