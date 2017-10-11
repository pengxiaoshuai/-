package com.xms.widget.Chart;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.xms.R;

/**
 * Created by dell on 2017/7/27.
 */

public class MyMarkView extends MarkerView{
    private TextView tvMarkText;

    public MyMarkView(Context context) {
        super(context, R.layout.mark_view);
        tvMarkText = (TextView) findViewById(R.id.tvMarkText);
    }

    @Override
    public void refreshContent(Entry entry, Highlight highlight) {
        if (entry instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) entry;
            tvMarkText.setText("66666 " + Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
            tvMarkText.setText("" + entry.getY());
        }
        setOffset(-(getWidth() / 2),-getHeight() - UnitUtils.dp2px(getContext(), 2)); //设置显示的view的位置
    }

//    @Override
//    public int getXOffset() {
//        return -(getWidth() / 2);
//    }
//
//    @Override
//    public int getYOffset() {
//        return -getHeight() - UnitUtils.dp2px(getContext(), 2);
//    }
}
