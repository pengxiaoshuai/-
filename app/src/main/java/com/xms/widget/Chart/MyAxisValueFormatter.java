package com.xms.widget.Chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;
    private int num;
    public MyAxisValueFormatter(int num) {
      //  mFormat = new DecimalFormat("###,###,###,##0.0");//后面有小数点
        mFormat = new DecimalFormat("###,###,###,##");//多位数用逗号隔开

        this.num = num;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis){
        switch (num){
            case 1:
                return mFormat.format(value) + "时";
            case 2:
                return mFormat.format(value) + "日";
            case 3:
                return mFormat.format(value) + "月";
            case 4:
                return mFormat.format(value);
            default:
                return mFormat.format(value);
        }

    }
}
