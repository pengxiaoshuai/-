package com.xms.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.constants.InterfaceDefinition;
import com.xms.ui.fragment.AqszFragment;
import com.xms.ui.fragment.XtszFragment;
import com.xms.utils.DefaultDisplayImageOptions;
import com.xms.utils.PreferencesUtil;
import com.xms.widget.PagerSlidingTabStrip;
import com.xms.widget.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class XtszActivity extends BaseActivity {
    @BindView(R.id.mimage)
    RoundImageView mimage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.workname)
    TextView workname;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private DisplayImageOptions mOptions = DefaultDisplayImageOptions
            .getDefaultDisplayImageOptions(this);
    private List<Fragment> mFragmentData;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tab)
    PagerSlidingTabStrip mTab;

    @Override
    public int getContentViewId() {
        return R.layout.activity_xtsz;
    }

    @Override
    public void initData() {
        setTitle();
        mTvForTitle.setText("系统设置");
        initview();//初始化滑动导航栏
        setTabsValue();//初始化导航栏滑动轴的颜色和字体颜色
        ImageLoader.getInstance().displayImage((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.PERSONIMG,""),mimage,mOptions);
        name.setText((String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.USENAME,""));
        workname.setText((String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.SSGS,""));
    }

    @OnClick({R.id.common_title_left,R.id.message_lin_top})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            case R.id.message_lin_top:
                gotoActivity(ChangeMessageActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化滑动导航栏
     */
    private void initview() {
        mFragmentData = new ArrayList<>();
        mFragmentData.add(new AqszFragment());
        mFragmentData.add(new XtszFragment());
        fragmentPagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            private final String[] titles = {"安全设置", "系统设置"};

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

}
