package com.xms.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xms.R;
import com.xms.adapter.BaseRecyclerAdapter;
import com.xms.base.BaseActivity;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.holder.BaseRecyclerHolder;
import com.xms.inteface.CommonListener;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.StringUtil;
import com.xms.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class PcgjActivity extends BaseActivity {
    @BindView(R.id.pcgj_recyclerview)
    RecyclerView mRecyclerview;
    private ArrayList<com.xms.bean.PcgjBean.ResultBean.IegCustomerListBean> mlist;
    private BaseRecyclerAdapter<com.xms.bean.PcgjBean.ResultBean.IegCustomerListBean> madapter;
    private int left = 1;
    private int right = 1;
    private com.xms.bean.PcgjBean mbean;
    private String id = null;
    @Override
    public int getContentViewId(){
        return R.layout.activity_pcgj;
    }
    @OnClick(R.id.common_title_left)
    void Onclick(View view){
        switch (view.getId()){
            case R.id.common_title_left:
                finish();
                break;
            default:
                break;

        }
    }


    @Override
    public void initData(){
        setTitle();
        initDialog();
        mTvForTitle.setText("偏差告警");
        mlist=new ArrayList<>();

        //mlist.add(new PcgjBean("莫奈瓷砖","-0.6558","<-0.13|>0.03"));
        madapter=new BaseRecyclerAdapter<com.xms.bean.PcgjBean.ResultBean.IegCustomerListBean>(this,mlist,R.layout.adapter_item_pcgj) {
            @Override
            public void convert(BaseRecyclerHolder holder, final com.xms.bean.PcgjBean.ResultBean.IegCustomerListBean item, int position, boolean isScrolling) {
                holder.setText(R.id.adapter_item_pcgj_1,item.getName());
                holder.setText(R.id.adapter_item_pcgj_2,item.getDifferRatio()+"");
                holder.setText(R.id.adapter_item_pcgj_3,"<"+item.getMinusDeviation()+"|>"+item.getPositiveDeviation());
                holder.setCommonListener(R.id.adapter_item_pcgj_4, position, new CommonListener(){
                    @Override
                    public void commonListener(View view, int position){
                        dialog_bom(item.getWeChatNote(),item.getShortNote(),item.getMinusDeviation()+"",item.getPositiveDeviation()+"");
                        id=item.getId();
                        dialog.show();
                    }
                });
            }
        };
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(madapter);
        Request();
    }
    private void Request(){
        JSONObject object = new JSONObject();
//        object.put("ddate", data);
    //    object.put("companyId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.COMPANYID, ""));
        object.put("userId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.USERID, ""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "deviateWarn.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(PcgjActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("000","000");
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")){
                                Gson gson = new Gson();
                                mbean = gson.fromJson(response,com.xms.bean.PcgjBean.class);
                                mlist.clear();
                                mlist.addAll(mbean.getResult().getIegCustomerList());
                                madapter.notifyDataSetChanged();
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }
    private void dialog_bom(int left,int right,String top,String bom){
        if (left==1){
            this.left=1;
            imgleft1.setBackgroundResource(R.mipmap.wx_1);
            textleft.setTextColor(getResources().getColor(R.color.them_color));
            imgleft2.setBackgroundResource(R.mipmap.select_true_0);
        }else{
            this.left=0;
            imgleft1.setBackgroundResource(R.mipmap.wx_0);
            textleft.setTextColor(getResources().getColor(R.color.gray));
            imgleft2.setBackgroundResource(R.mipmap.select_false_0);
        }
        if (right==1){
            this.right=1;
            imgright1.setBackgroundResource(R.mipmap.dx_1);
            textright.setTextColor(getResources().getColor(R.color.them_color));
            imgright2.setBackgroundResource(R.mipmap.select_true_0);
        }else{
            this.right=0;
            imgright1.setBackgroundResource(R.mipmap.dx_0);
            textright.setTextColor(getResources().getColor(R.color.gray));
            imgright2.setBackgroundResource(R.mipmap.select_false_0);
        }
        medit_01.setText(top);
        medit_02.setText(bom);
    }
    //修改告警设置
    private void Request(int wx,int dx,Double f,Double z,String id){
        JSONObject object = new JSONObject();
        object.put("wx", wx);
        object.put("dx", dx);
        object.put("zxpcV", z);
        object.put("fxpcV", f);
        object.put("customerId", id);
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "deviateWarn1.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(PcgjActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("000","000");
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")){
                             ToastUtil.TextToast("设置成功");
                                Request();//刷新数据
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }
    private Dialog dialog;
    private ImageButton mright;
    private TextView mtext;
    private ImageView imgleft1,imgleft2,imgright1,imgright2;
    private TextView textleft,textright;
    private EditText medit_01,medit_02;
    private void initDialog(){
        dialog=new Dialog(this,R.style.dialog);
        dialog.setContentView(R.layout.dialog_item_gjsz);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
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
        mright = (ImageButton) dialog.findViewById(R.id.common_title_right);
        mtext = (TextView) dialog.findViewById(R.id.dialog_item_gjsz_yy);
        imgleft1 = (ImageView) dialog.findViewById(R.id.dialog_item_left_img1);
        imgleft2 = (ImageView) dialog.findViewById(R.id.dialog_item_left_img2);
        imgright1 = (ImageView) dialog.findViewById(R.id.dialog_item_right_img1);
        imgright2 = (ImageView) dialog.findViewById(R.id.dialog_item_right_img2);
        textleft = (TextView) dialog.findViewById(R.id.dialog_item_left_text);
        textright = (TextView) dialog.findViewById(R.id.dialog_item_right_text);
        medit_01 = (EditText) dialog.findViewById(R.id.dialog_item_ed1);
        medit_02 = (EditText) dialog.findViewById(R.id.dialog_item_ed2);
        mright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.dismiss();
                if (StringUtil.isEmpty(medit_01.getText().toString())){
                    ToastUtil.TextToast("负偏差不能为空");
                    return;
                }
                if (StringUtil.isEmpty(medit_02.getText().toString())){
                    ToastUtil.TextToast("正偏差不能为空");
                    return;
                }
                double top = 0.0;
                double bom = 0.0;
                try {
                    top=Double.parseDouble(medit_01.getText().toString().trim());
                    bom = Double.parseDouble(medit_02.getText().toString().trim());
                }catch (Exception e){
                    ToastUtil.TextToast("输入的数据有误，请重新输入！");
                    return;
                }
                Request(left,right,top,bom,id);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_item_lin_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (left==1){
                    left=0;
                    imgleft1.setBackgroundResource(R.mipmap.wx_0);
                    textleft.setTextColor(getResources().getColor(R.color.gray));
                    imgleft2.setBackgroundResource(R.mipmap.select_false_0);
                }else{
                    left=1;
                    imgleft1.setBackgroundResource(R.mipmap.wx_1);
                    textleft.setTextColor(getResources().getColor(R.color.them_color));
                    imgleft2.setBackgroundResource(R.mipmap.select_true_0);
                }
            }
        });
        dialog.findViewById(R.id.dialog_item_lin_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (right==1){
                    right=0;
                    imgright1.setBackgroundResource(R.mipmap.dx_0);
                    textright.setTextColor(getResources().getColor(R.color.gray));
                    imgright2.setBackgroundResource(R.mipmap.select_false_0);
                }else{
                    right=1;
                    imgright1.setBackgroundResource(R.mipmap.dx_1);
                    textright.setTextColor(getResources().getColor(R.color.them_color));
                    imgright2.setBackgroundResource(R.mipmap.select_true_0);
                }
            }
        });
    }
//    class PcgjBean {
//        public PcgjBean(String name, String zpcl, String gjfw) {
//            this.name = name;
//            this.zpcl = zpcl;
//            this.gjfw = gjfw;
//        }
//
//        public String getName() {
//
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getZpcl() {
//            return zpcl;
//        }
//
//        public void setZpcl(String zpcl) {
//            this.zpcl = zpcl;
//        }
//
//        public String getGjfw() {
//            return gjfw;
//        }
//
//        public void setGjfw(String gjfw) {
//            this.gjfw = gjfw;
//        }
//
//        private String name;
//        private String zpcl;
//        private String gjfw;
//
//    }

}
