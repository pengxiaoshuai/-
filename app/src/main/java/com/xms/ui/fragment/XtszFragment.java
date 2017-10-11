package com.xms.ui.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xms.R;
import com.xms.base.BaseFragment;
import com.xms.bean.XtszBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.ToastUtil;
import com.xms.widget.SwitchView;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;


public class XtszFragment extends BaseFragment {
    private View mRootView;
    @BindView(R.id.switchview01)
    SwitchView mswitchview;
    @BindView(R.id.switchview02)
    SwitchView mswitchview2;

    @Override
    protected View initView(LayoutInflater inflater) {
        mRootView = inflater.inflate(R.layout.fragment_xtsz, null);
        return mRootView;
    }

    private int gj = 0;
    private int ts = 0;

    @Override
    public void initData() {
        if (!isFirstLoad()) {
            return;
        }
        Request();
        mswitchview.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                if (mbean == null) {
                    ToastUtil.TextToast("正在重新获取状态信息");
                    Request();
                    return;
                }
                Request(Short.parseShort(1 + ""), Short.parseShort(mbean.getResult().getPushSet() + ""));
            }

            @Override
            public void toggleToOff() {
                if (mbean == null) {
                    ToastUtil.TextToast("正在重新获取状态信息");
                    Request();
                    return;
                }
                Request(Short.parseShort(0 + ""), Short.parseShort(mbean.getResult().getPushSet() + ""));
            }
        });
        mswitchview2.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                if (mbean == null) {
                    ToastUtil.TextToast("正在重新获取状态信息");
                    Request();
                    return;
                }
                Request(Short.parseShort(mbean.getResult().getWarnSet() + ""), Short.parseShort(1 + ""));
            }

            @Override
            public void toggleToOff() {
                if (mbean == null) {
                    ToastUtil.TextToast("正在重新获取状态信息");
                    Request();
                    return;
                }
                Request(Short.parseShort(mbean.getResult().getWarnSet() + ""), Short.parseShort(0 + ""));
            }
        });
    }

    private XtszBean mbean;

    //获取状态值
    private void Request() {
        JSONObject object = new JSONObject();
        object.put("userId", PreferencesUtil.get(mContext, InterfaceDefinition.PreferencesUser.USERID, ""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "set.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(mContext) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")){
                                setFirstLoad(false);
                                mbean = null;
                                Gson gson = new Gson();
                                mbean = gson.fromJson(response, XtszBean.class);
                                if (ts != mbean.getResult().getPushSet()) {
                                    if (mbean.getResult().getPushSet() == 0) {
                                        mswitchview2.setState(false);
                                        ts = 0;
                                    } else {
                                        mswitchview2.setState(true);
                                        ts = 1;
                                    }
                                }
                                if (gj != mbean.getResult().getWarnSet()) {
                                    if (mbean.getResult().getWarnSet() == 0) {
                                        mswitchview.setState(false);
                                        gj = 0;
                                    } else {
                                        mswitchview.setState(true);
                                        gj = 1;
                                    }
                                }

                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }

    //修改值
    private void Request(Short gj, Short ts) {
        JSONObject object = new JSONObject();
        object.put("warnSet", gj);
        object.put("pushSet", ts);
        object.put("userId", PreferencesUtil.get(mContext, InterfaceDefinition.PreferencesUser.USERID, ""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "set1.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(mContext) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")) {
                                Request();
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }
}
