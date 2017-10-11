package com.xms.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.xms.R;
import com.xms.base.BaseFragment;

/**
 * Created by dell on 2017/7/17.
 */

public class MyFragment extends BaseFragment{
    private View mRootView;
    @Override
    protected View initView(LayoutInflater inflater) {
        mRootView=inflater.inflate(R.layout.fragment_test,null);
        return mRootView;
    }

    @Override
    public void initData() {

    }
}
