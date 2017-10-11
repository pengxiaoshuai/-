package com.xms.ui.activity;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.bean.BxjlBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.utils.CommonDialog;
import com.xms.utils.DateUtil;
import com.xms.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class BxdActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.message)
    TextView message;
    private BxjlBean.ResultBean.RepairListBean mbean;

    @Override
    public int getContentViewId() {
        return R.layout.activity_bxd;
    }

    @Override
    public void initData(){
        setTitle();
        mTvForTitle.setText("报修单");
        if (getIntent() != null){
            mbean = (BxjlBean.ResultBean.RepairListBean) getIntent().getSerializableExtra(BxjllbActivity.BXGL_STR);
            title.setText(mbean.getTitle());
            time.setText(DateUtil.timeStampDate(mbean.getCreateDate()+""));
            name.setText(getIntent().getExtras().getString(BxjllbActivity.BXGL_LXR));
            phone.setText(getIntent().getExtras().getString(BxjllbActivity.BXGL_LXFS));
            message.setText(mbean.getDetail());

        }
    }

    @OnClick({R.id.common_title_left, R.id.delete, R.id.goback})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            case R.id.delete:
                Dialog();
                break;
            case R.id.goback:
                finish();
                break;
            default:
                break;
        }
    }

    private void Dialog() {
        CommonDialog dialog = new CommonDialog(this,
                R.style.dialog);
        dialog.setIcon(R.mipmap.logo);
        dialog.setContent("确定删除？");
        dialog.setLeftBtnText("取消");
        dialog.setRightBtnText("确定");
        dialog.setListener(new CommonDialog.DialogClickListener() {

            @Override
            public void onRightBtnClick(Dialog dialog) {
                Request(mbean.getId());
                dialog.dismiss();
            }

            @Override
            public void onLeftBtnClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void Request(final String id){
        JSONObject object=new JSONObject();
        object.put("repairId", id);
        Log.e("请求报文","{"+InterfaceDefinition.ICommonKey.REQUEST_DATA+":"+object.toString()+"}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "deviateWarn2.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA,object.toString())
                .build()
                .execute(new MyStringCallback(BxdActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("请求数据",""+response);
                            JSONObject object= JSON.parseObject(response);
                            if (object.getBoolean("success")){
                                ToastUtil.TextToast("删除成功");
                                BxjllbActivity.mresh = true;
                                finish();
                            }else{
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }
                    }
                });
    }
}
