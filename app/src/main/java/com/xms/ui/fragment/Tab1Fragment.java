package com.xms.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xms.R;
import com.xms.adapter.BaseRecyclerAdapter;
import com.xms.adapter.MyViewPagerAdapter;
import com.xms.base.BaseFragment;
import com.xms.bean.LoginBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.holder.BaseRecyclerHolder;
import com.xms.ui.activity.DfcsActivity;
import com.xms.ui.activity.LrjsqActivity;
import com.xms.ui.activity.MainActivity;
import com.xms.ui.activity.NhtjActivity;
import com.xms.ui.activity.PcbjActivity;
import com.xms.ui.activity.PcgjActivity;
import com.xms.ui.activity.SsdlActivity;
import com.xms.utils.DefaultDisplayImageOptions;
import com.xms.utils.DividerGridItemDecoration;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页第一个fragment
 * Created by dell on 2017/2/24.
 */

public class Tab1Fragment extends BaseFragment {
    private View mRootView;
    @BindView(R.id.activity_zy_message_viewpager)
    ViewPager mviewpager;
    @BindView(R.id.activity_zy_message_linlayout)
    LinearLayout mlinlayout;
    @BindView(R.id.mrecyclerview)
    RecyclerView mrecyclerview;
    public static ArrayList<LoginBean.ResultBean.IegCustomerListBean> mstrlist = new ArrayList<>();
    public static ArrayList<String> micon = new ArrayList<>();
//    private View view0, view, view2, view3, view4;
    private ArrayList<View> mlist;
    private MyViewPagerAdapter madapter;
    private ArrayList<ImageView> mlistimage;
   // private ImageView mimage;
    private MainActivity mainActivity;
    private ArrayList<MydataBean> mRlist;
    private BaseRecyclerAdapter<MydataBean> mRadapter;
    //    @BindView(R.id.drawer_layout)
//    DrawerLayout mdr;
//    @BindView(R.id.left_drawer)
//    LinearLayout mlin_left;
    private Dialog dialog;

    @Override
    protected View initView(LayoutInflater inflater) {
        mRootView = inflater.inflate(R.layout.fragment_tab1, null);
        return mRootView;
    }

    private int currentPosition = 1;

    //初始化数据用的
    @Override
    public void initData() {
        if (!isFirstLoad()) {
            return;
        }
        setFirstLoad(false);
        setTitle();
        mImgvForLeft.setVisibility(View.INVISIBLE);
        mImgvForRight.setImageResource(R.mipmap.select_right);
        mImgvForRight.setVisibility(View.VISIBLE);
        mainActivity = (MainActivity) getActivity();
        mlist = new ArrayList<>();
        initViewpager();
        if ((Boolean) PreferencesUtil.get(mContext,
                InterfaceDefinition.PreferencesUser.ZT, false)){
            mTvForTitle.setText((String) PreferencesUtil.get(mContext,
                    InterfaceDefinition.PreferencesUser.SSGS, ""));
            mstrlist.add(0, new LoginBean.ResultBean.IegCustomerListBean("", (String) PreferencesUtil.get(mContext,
                    InterfaceDefinition.PreferencesUser.SSGS, "")));
        } else {
            mTvForTitle.setText("无权限");
            mstrlist.add(0, new LoginBean.ResultBean.IegCustomerListBean("", "无权限"));
        }

        mRlist = new ArrayList<>();
        mRlist.add(new MydataBean(R.mipmap.ssyd0, "实时电量", "显示电/水/燃气/的抄表参数"));
        mRlist.add(new MydataBean(R.mipmap.grzx0, "偏差报表", "根据合同规定及实际使用计算偏差"));
        mRlist.add(new MydataBean(R.mipmap.nhtj0, "能耗统计", "统计各时间段的用电/水/燃气量"));
        mRlist.add(new MydataBean(R.mipmap.dldf0, "电费测算", "监测每日每月实时电量/费情况"));
        mRlist.add(new MydataBean(R.mipmap.lrjsq, "利润计算器", "根据交易最新规则精准测算可算出利润"));
        mRlist.add(new MydataBean(R.mipmap.pcgj, "偏差告警", "设置偏差率告警范围和方式"));
        mRadapter = new BaseRecyclerAdapter<MydataBean>(mContext, mRlist, R.layout.adapter_item_frg1_recyclerview) {
            @Override
            public void convert(BaseRecyclerHolder holder, MydataBean item, int position, boolean isScrolling) {
                holder.setImageResource(R.id.adapter_image, item.getImage());
                holder.setText(R.id.adapter_text1, item.getMtitle());
                holder.setText(R.id.adapter_text2, item.getMessage());
            }
        };
        mrecyclerview.setLayoutManager(new GridLayoutManager(mContext, 2));
        mrecyclerview.addItemDecoration(new DividerGridItemDecoration(mContext));
        mrecyclerview.setAdapter(mRadapter);
        mRadapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                //    ToastUtil.TextToast(mRlist.get(position).getMtitle());
                switch (position) {
                    case 0:
                        //实时电量
                        if (!(Boolean) PreferencesUtil.get(mContext,
                                InterfaceDefinition.PreferencesUser.ZT, false)) {
                            ToastUtil.TextToast(getResources().getString(R.string.err_no));
                            return;
                        }
                        //   gotoAct(SsjcActivity.class);
                        gotoAct(SsdlActivity.class);
                        break;
                    case 1://偏差报表
                        if (!(Boolean) PreferencesUtil.get(mContext,
                                InterfaceDefinition.PreferencesUser.ZT, false)) {
                            ToastUtil.TextToast(getResources().getString(R.string.err_no));
                            return;
                        }
                        gotoAct(PcbjActivity.class);
                        //    mainActivity.mFragmentTabHost.setCurrentTab(1);
                        //   mainActivity.mradiobutton.setChecked(true);
                        break;
                    case 2:
                        //能耗统计
                        if (!(Boolean) PreferencesUtil.get(mContext,
                                InterfaceDefinition.PreferencesUser.ZT, false)) {
                            ToastUtil.TextToast(getResources().getString(R.string.err_no));
                            return;
                        }
                        gotoAct(NhtjActivity.class);
                        break;
                    case 3://电费测算
                        if (!(Boolean) PreferencesUtil.get(mContext, InterfaceDefinition.PreferencesUser.ZT, false)) {
                            ToastUtil.TextToast(getResources().getString(R.string.err_no));
                            return;
                        }
                        gotoAct(DfcsActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putInt(DF, 0);
//                        gotoAct(bundle, DldfActivity.class);
                        break;
                    case 4:
                        //利润计算器
                       // gotoAct(SdlrjsqActivity.class);
                        gotoAct(LrjsqActivity.class);
                        break;
                    case 5:
                        //偏差告警
                        if (!(Boolean) PreferencesUtil.get(mContext,
                                InterfaceDefinition.PreferencesUser.ZT, false)) {
                            ToastUtil.TextToast(getResources().getString(R.string.err_no));
                            return;
                        }
                        gotoAct(PcgjActivity.class);
                        break;
                    default:
                        break;

                }
            }
        });
       //   Request();//初始化首页数据
        initDialog();//初始化弹出框
    }
    private void initViewpager(){
        LayoutInflater lf = getActivity().getLayoutInflater().from(mContext);
        for (int i = 0; i < micon.size(); i++) {
            View view = lf.inflate(R.layout.adapter_item_viewpager, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.adapter_item_viewpager_image);
            ImageLoader.getInstance().displayImage(micon.get(i), imageView,
                    DefaultDisplayImageOptions.getDefaultDisplayImageOptionsRounded(mContext));
            mlist.add(view);
        }
//        view0 = lf.inflate(R.layout.adapter_item_viewpager, null);
//        view = lf.inflate(R.layout.adapter_item_viewpager, null);
//        view2 = lf.inflate(R.layout.adapter_item_viewpager, null);
//        view3 = lf.inflate(R.layout.adapter_item_viewpager, null);
//        view4 = lf.inflate(R.layout.adapter_item_viewpager, null);
//        mimage = (ImageView) view0.findViewById(R.id.adapter_item_viewpager_image);
//        mimage.setImageResource(R.mipmap.banner3);
//        mlist.add(view0);
//        mimage = (ImageView) view.findViewById(R.id.adapter_item_viewpager_image);
//        mimage.setImageResource(R.mipmap.banner1);
//        mlist.add(view);
//        mimage = (ImageView) view2.findViewById(R.id.adapter_item_viewpager_image);
//        mimage.setImageResource(R.mipmap.banner2);
//        mlist.add(view2);
//        mimage = (ImageView) view3.findViewById(R.id.adapter_item_viewpager_image);
//        mimage.setImageResource(R.mipmap.banner3);
//        mlist.add(view3);
//        mimage = (ImageView) view4.findViewById(R.id.adapter_item_viewpager_image);
//        mimage.setImageResource(R.mipmap.banner1);
//        mlist.add(view4);

        madapter = new MyViewPagerAdapter(mlist);
        mviewpager.setAdapter(madapter);
        mviewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setnum(position);
//                if (position == 0) {
//                    currentPosition = mlist.size() - 2;
//                } else if (position == mlist.size() - 1) {
//                    currentPosition = 1;
//                } else {
//                    currentPosition = position;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //当state为SCROLL_STATE_IDLE即没有滑动的状态时切换页面
                //当滑动停下来的时候把所在页面切换到指定页面 0就到3,4就到1，完美循环
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    mviewpager.setCurrentItem(currentPosition, false);//是否需要滑动动画 false
//                    setnum(currentPosition);
//                }

            }
        });

        setDot();//设置小圆点
    }

    //事件监听
    @OnClick({R.id.common_title_left, R.id.common_title_right, R.id.common_title_tv})
    void Onclick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                //     ToastUtil.TextToast("事件监听ok");
//                mdr.openDrawer(mlin_left);
                break;
            case R.id.common_title_right:
                dialog.show();
                break;
            case R.id.common_title_tv:
                dialog.show();
                break;
            default:
                break;
        }
    }

    private void setDot() {
        mlistimage = new ArrayList<>();
        //设置LinearLayout的子控件的宽高，这里单位是像素
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 0;//左边距
        for (int i = 0; i < mlist.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.mipmap.hd01);
            mlinlayout.addView(imageView);
            mlistimage.add(imageView);
        }
        mlistimage.get(0).setImageResource(R.mipmap.ld0);
    }

    private void setnum(int num) {
        for (int i = 0; i < mlistimage.size(); i++) {
            mlistimage.get(i).setImageResource(R.mipmap.hd01);
        }
//        mlistimage.get(num % 3).setImageResource(R.mipmap.ld0);
        mlistimage.get(num).setImageResource(R.mipmap.ld0);
    }

    private RecyclerView mdialogrecyclerview;
    //    private ArrayList<LoginBean.ResultBean.IegCustomerListBean> mliststr;
    private BaseRecyclerAdapter<LoginBean.ResultBean.IegCustomerListBean> mdialogadapter;
    private int index = 0;

    private void initDialog() {
//        mliststr = new ArrayList<>();


//        mliststr.addAll(mstrlist);
//        mliststr.add("江西百电信息产业有限公司");
//        mliststr.add("莫奈瓷砖");
        dialog = new Dialog(mContext, R.style.dialog);
        dialog.setContentView(R.layout.dialog_main_top1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        WindowManager windowManager = dialog.getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() / 6 * 5); // // 设置宽度
        dialog.getWindow().setAttributes(lp);
        dialog.findViewById(R.id.common_title_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mdialogrecyclerview = (RecyclerView) dialog.findViewById(R.id.dialog_main_recyclerview);
        mdialogadapter = new BaseRecyclerAdapter<LoginBean.ResultBean.IegCustomerListBean>(mContext, mstrlist, R.layout.adapter_item_main_recy) {
            @Override
            public void convert(BaseRecyclerHolder holder, LoginBean.ResultBean.IegCustomerListBean item, int position, boolean isScrolling) {
                holder.setText(R.id.adapter_item_main_text, item.getName());
                if (index == position) {
                    holder.setImageResource(R.id.adapter_item_main_img, R.mipmap.select_true_0);
                } else {
                    holder.setImageResource(R.id.adapter_item_main_img, R.mipmap.select_false_0);
                }
            }
        };
        mdialogrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mdialogrecyclerview.setAdapter(mdialogadapter);
        mdialogadapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                index = position;
                mdialogadapter.notifyDataSetChanged();
            }
        });
        dialog.findViewById(R.id.dialog_item_gjsz_yy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvForTitle.setText(mstrlist.get(index).getName());
                PreferencesUtil.put(mContext,
                        InterfaceDefinition.PreferencesUser.COMPANYID, mstrlist.get(index).getId());
                if (index==0){
                    PreferencesUtil.put(mContext,
                            InterfaceDefinition.PreferencesUser.SELECT, "售");
                }else{
                    PreferencesUtil.put(mContext,
                            InterfaceDefinition.PreferencesUser.SELECT, "客");
                }
                PreferencesUtil.put(mContext,
                        InterfaceDefinition.PreferencesUser.TITLE, mstrlist.get(index).getName());
                dialog.dismiss();
            }
        });
    }

    private void Request() {
        JSONObject object = new JSONObject();
        object.put("userId", PreferencesUtil.get(mContext,
                InterfaceDefinition.PreferencesUser.USERID, ""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "index.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(mContext) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")) {

                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }
                    }
                });
    }

    class MydataBean {
        public MydataBean(int image, String mtitle, String message) {
            this.image = image;
            this.mtitle = mtitle;
            this.message = message;
        }

        private int image;
        private String mtitle;
        private String message;

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getMtitle() {
            return mtitle;
        }

        public void setMtitle(String mtitle) {
            this.mtitle = mtitle;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
