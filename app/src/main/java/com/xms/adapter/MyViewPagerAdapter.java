package com.xms.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by dell on 2017/6/16.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> mlist;
    public  MyViewPagerAdapter(ArrayList<View> mlist){
        this.mlist = mlist;
    }
    @Override
    public int getCount(){
        return mlist.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView(mlist.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        container.addView(mlist.get(position),0);
        return  mlist.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == object;
    }
}
