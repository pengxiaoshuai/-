package com.xms.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xms.R;
import com.xms.base.BaseFragment;
import com.xms.bean.MyMessageBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.utils.CommonDialog;
import com.xms.utils.DefaultDisplayImageOptions;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.ToastUtil;
import com.xms.widget.RoundImageView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class MyMessageFragment extends BaseFragment {

    private View mRootView;
    public static final String DF = "mydf";
    private DateFormat dateFormat1 = new SimpleDateFormat("yyyyMM");
    @BindView(R.id.activity_my_message_topimg)
    RoundImageView mimg;
    @BindView(R.id.message_myname)
    TextView mname;
    @BindView(R.id.message_myworkname)
    TextView mworkname;
    @BindView(R.id.message_left)
    TextView mleft;
    @BindView(R.id.message_right)
    TextView mright;
    @BindView(R.id.select)
    TextView mselect;
    public static boolean select = false;
    private DisplayImageOptions mOptions = DefaultDisplayImageOptions
            .getDefaultDisplayImageOptions(getActivity());
    @Override
    public void onResume(){
        super.onResume();
        if (select){
            Request(dateFormat1.format(new Date()));
            select = false;
        }
    }

    @Override
    protected View initView(LayoutInflater inflater){
        mRootView = inflater.inflate(R.layout.activity_my_message, null);
        return mRootView;
    }

    @Override
    public void initData() {
        if (!isFirstLoad()) {
            return;
        }

        setTitle();
        mTvForTitle.setText("个人中心");
        mImgvForLeft.setVisibility(View.INVISIBLE);

            ImageLoader.getInstance().displayImage((String) PreferencesUtil.get(getActivity(),
                    InterfaceDefinition.PreferencesUser.PERSONIMG, ""), mimg,
                    mOptions);

        if (!(Boolean) PreferencesUtil.get(mContext,
                InterfaceDefinition.PreferencesUser.ZT, false)) {
            mworkname.setVisibility(View.GONE);
        }
        Request(dateFormat1.format(new Date()));
        mselect.setText((String) PreferencesUtil.get(mContext,
                InterfaceDefinition.PreferencesUser.SELECT, ""));
    }

    @OnClick({R.id.message_lin_top, R.id.activity_my_message_dl, R.id.activity_my_message_df,
            R.id.activity_my_message_bxgl, R.id.activity_my_message_xtsz, R.id.activity_my_message_kfzx})
    void OnClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.message_lin_top:
                gotoAct(ChangeMessageActivity.class);
                break;
//            case R.id.activity_my_message_dl:
//                bundle.putInt(DF, 0);
//                gotoAct(bundle, DldfActivity.class);
//                break;
//            case R.id.activity_my_message_df:
//                bundle.putInt(DF, 1);
//                gotoAct(bundle, DldfActivity.class);
//                break;
            case R.id.activity_my_message_bxgl:
                gotoAct(BxglActivity.class);
                break;
            case R.id.activity_my_message_xtsz:
                gotoAct(XtszActivity.class);
                break;
            case R.id.activity_my_message_kfzx:
                Dialog("15779160456");
                break;
            default:
                break;
        }
    }

    private void Request(final String data) {
        JSONObject object = new JSONObject();
        object.put("ddate", data);
        object.put("userId", PreferencesUtil.get(mContext, InterfaceDefinition.PreferencesUser.USERID, ""));
//        object.put("companyId", PreferencesUtil.get(mContext,InterfaceDefinition.PreferencesUser.COMPANYID,""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "personalCenter.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(mContext) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")) {
                                setFirstLoad(false);
                                Gson gson = new Gson();
                                MyMessageBean mbean = gson.fromJson(response, MyMessageBean.class);
                                mname.setText(mbean.getResult().getUserName());
                                mworkname.setText(mbean.getResult().getCompanyName());
                                PreferencesUtil.put(mContext,
                                        InterfaceDefinition.PreferencesUser.USENAME, mbean.getResult().getUserName());

                                mleft.setText(mbean.getResult().getDataValue() + "");
                                mright.setText(mbean.getResult().getPrice() + "");
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }

    private void Dialog(final String phone) {
        CommonDialog dialog = new CommonDialog(getActivity(),
                R.style.dialog);
        dialog.setIcon(R.mipmap.logo);
        dialog.setContent("您是否要拨打客服电话？");
        dialog.setLeftBtnText("取消");
        dialog.setRightBtnText("确定");
        dialog.setListener(new CommonDialog.DialogClickListener() {

            @Override
            public void onRightBtnClick(Dialog dialog) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                        + phone));
                startActivity(intent);//拨打电话
            }

            @Override
            public void onLeftBtnClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
