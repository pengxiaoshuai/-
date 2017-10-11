package com.xms.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xms.R;
import com.xms.adapter.BaseRecyclerAdapter;
import com.xms.base.BaseActivity;
import com.xms.bean.BxjlBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.holder.BaseRecyclerHolder;
import com.xms.utils.DateUtil;
import com.xms.utils.DividerGridItemDecoration;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class BxjllbActivity extends BaseActivity {
    @BindView(R.id.bxjl_recyclerview)
    XRecyclerView mrecyclerview;
    public static String BXGL_STR="bxgl_str";
    public static String BXGL_LXR="bxgl_lxr";
    public static String BXGL_LXFS="bxgl_lxfs";
    private ArrayList<BxjlBean.ResultBean.RepairListBean> mlist;
    private BaseRecyclerAdapter<BxjlBean.ResultBean.RepairListBean> madapter;
    private int times = 0;
    public static boolean mresh = false;

    @Override
    protected void onResume(){
        super.onResume();
        if (mresh){
            mrecyclerview.refresh();
            mresh = false;
        }
    }

    @Override
    public int getContentViewId(){
        return R.layout.activity_bxjllb;
    }

    @Override
    public void initData(){
        setTitle();
        mTvForTitle.setText("报修记录");
        initxrecyclerview();
    }
    @OnClick(R.id.common_title_left)
    void OnClick(View view){
        switch (view.getId()){
            case R.id.common_title_left:
                finish();
                break;
            default:
                break;
        }
    }
    private void initxrecyclerview(){
        mlist = new ArrayList<>();
        madapter = new BaseRecyclerAdapter<BxjlBean.ResultBean.RepairListBean>(this,mlist,R.layout.adapter_item_bxjl) {
            @Override
            public void convert(BaseRecyclerHolder holder, BxjlBean.ResultBean.RepairListBean item, int position, boolean isScrolling) {
                holder.setText(R.id.adapter_item_bxjl_text1,item.getTitle());
                holder.setText(R.id.adapter_item_bxjl_text2,item.getDetail());
                holder.setText(R.id.adapter_item_bxjl_text3,"时间:" + DateUtil.timeStampDate(item.getCreateDate()+""));
            }
        };
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerview.addItemDecoration(new DividerGridItemDecoration(this));
        mrecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mrecyclerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);//吃豆豆的动画
        mrecyclerview.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
//        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
//        mrecyclerview.addHeaderView(header);//可以添加头部布局
        mrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh(){
                Request();
//                times = 0;
//                new Handler().postDelayed(new Runnable(){
//                    public void run(){
//                        mlist.clear();
//                        for(int i = 0; i < 10 ;i++){
//                            mlist.add(new BxglBean("网关无故告警"+i));
//                        }
//                        madapter.notifyDataSetChanged();
//                        mrecyclerview.refreshComplete();//告诉XRecyclerview刷新完毕
//                    }
//                }, 1000);            //refresh data here
            }

            @Override
                public void onLoadMore(){
                mrecyclerview.loadMoreComplete();
//                if(times < 2){
//                    new Handler().postDelayed(new Runnable(){
//                        public void run(){
//                            for(int i = 0; i < 10 ;i++){
//                                mlist.add(new BxglBean("网关无故告警"+mlist.size()));
//                            }
//                            mrecyclerview.loadMoreComplete();
//                            madapter.notifyDataSetChanged();
//                        }
//                    }, 1000);
//                } else {
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            for(int i = 0; i < 9 ;i++){
//                                mlist.add(new BxglBean("网关无故告警"+mlist.size()));
//                            }
//                            mrecyclerview.setNoMore(true); //设置没有更多了
//                            madapter.notifyDataSetChanged();
//                        }
//                    }, 1000);
//                }
//                times ++;
            }
        });
        mrecyclerview.setAdapter(madapter);
      // mrecyclerview.setTransitionName("你好");
        mrecyclerview.refresh();//初次进来刷新
        madapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(RecyclerView parent, View view, int position){
                Bundle bundle =new Bundle();
                bundle.putSerializable(BXGL_STR,mlist.get(position-1));
       //         ToastUtil.TextToast(mlist.get(position-1).getId());
                bundle.putString(BXGL_LXR,mlist.get(0).getIegUser().getName());
                bundle.putString(BXGL_LXFS,mlist.get(0).getIegUser().getMobilephone());
                gotoActivity(bundle,BxdActivity.class);
            }
        });
    }
    private BxjlBean mbean;
    private void Request(){
        JSONObject object=new JSONObject();
        object.put("userId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.USERID,""));
        Log.e("请求报文","{"+InterfaceDefinition.ICommonKey.REQUEST_DATA+":"+object.toString()+"}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "repairList.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA,object.toString())
                .build()
                .execute(new MyStringCallback(BxjllbActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("请求数据",""+response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")){
                                mbean=null;
                                Gson gson = new Gson();
                                mbean=gson.fromJson(response,BxjlBean.class);
                                mlist.clear();
                                mlist.addAll(mbean.getResult().getRepairList());
                                madapter.notifyDataSetChanged();
                              //  mrecyclerview.setNoMore(true); //设置没有更多了
                            }else{
                                ToastUtil.TextToast(object.getString("info"));
                            }
                            mrecyclerview.refreshComplete();//告诉XRecyclerview刷新完毕
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                            mrecyclerview.refreshComplete();//告诉XRecyclerview刷新完毕
                        }
                    }
                });
    }

//    class BxglBean{
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title){
//            this.title = title;
//        }
//
//        public BxglBean(String title) {
//            this.title = title;
//        }
//
//        private String title;
//    }
}
