package com.xms.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.bean.LoginBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.ui.fragment.Tab1Fragment;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.StringUtil;
import com.xms.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    public static String PHONE = "";
    public static String PASSWORD = "";
    public static final String KEY_BUND = "key_bund";
    @BindView(R.id.phone)
    EditText mphone;
    @BindView(R.id.password)
    EditText mpassword;

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.act_login_login, R.id.forget, R.id.request})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.act_login_login:
//                gotoActivity(MainActivity.class);
//                finish();
                Request(mphone.getText().toString(), mpassword.getText().toString());
                break;
            case R.id.forget:
                gotoActivity(ForgetPasswordActivity.class);
                break;
            case R.id.request:
                gotoActivity(RequsetActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringUtil.isEmpty(PHONE)) {
            String phone = (String) PreferencesUtil.get(LoginActivity.this,
                    InterfaceDefinition.PreferencesUser.PHONE, "");
            String password = (String) PreferencesUtil.get(LoginActivity.this,
                    InterfaceDefinition.PreferencesUser.PASSWORD, "");
            if (StringUtil.isNotEmpty(phone)) {
                mphone.setText(phone);
            }
            if (StringUtil.isNotEmpty(password)) {
                mpassword.setText(password);
            }
        } else {
            mphone.setText(PHONE);
            mpassword.setText(PASSWORD);
            Request(mphone.getText().toString().trim(), mpassword.getText().toString().trim());
            PHONE = "";
            PASSWORD = "";
        }
    }

    private void Request(final String mobile, final String password) {
        if (StringUtil.isEmpty(mobile)) {
            ToastUtil.TextToast("登录账号为空，请重新输入");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            ToastUtil.TextToast("登录密码为空，请重新输入");
            return;
        }
        if (!StringUtil.isMobileNO(mobile)) {
            ToastUtil.TextToast("手机号码输入有误，请重新输入");
            return;
        }
        if (StringUtil.isNotPassWord(password)) {
            ToastUtil.TextToast("请输入6-18位的有效密码");
            return;
        }

        JSONObject object = new JSONObject();
        object.put("MOBILE", mobile);
        object.put("PASSWORD", password);
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "login.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(LoginActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            //15079031901
                            Log.e("请求数据", "" + response);
                            //{"curDateTime":1502157579819,"info":"操作完成","recode":10001000,"result":"江西百电信息产业有限公司","sessionId":"8D5DFCA3D781A0CBE07F6B17BB7B92A8","status":"y","success":true}
                            JSONObject object = JSON.parseObject(response);
                            LoginBean mbean = new LoginBean();
                            Gson gson=new Gson();
                            if (object.getBoolean("success")){
                                mbean=gson.fromJson(response,LoginBean.class);
                                PreferencesUtil.put(LoginActivity.this,
                                        InterfaceDefinition.PreferencesUser.PHONE, mobile);//账号
                                PreferencesUtil.put(LoginActivity.this,
                                        InterfaceDefinition.PreferencesUser.PASSWORD, password);//密码
                                Tab1Fragment.mstrlist.clear();
                                if (mbean.getResult().get(0).getStatus()!=10001000){
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.ZT, false);
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.SELECT, "无");
                                } else {
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.SSGS, mbean.getResult().get(0).getCompanyName());
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.USERID, mbean.getResult().get(0).getUserId());
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.COMPANYID, "");
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.ZT, true);
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.PERSONIMG,
                                            InterfaceDefinition.IPath.BASE_URL + mbean.getResult().get(0).getHeadIcon().getFileName());
                                    if (mbean.getResult().get(0).getIegCustomerList().size()!=0){
                                        Tab1Fragment.mstrlist.addAll(mbean.getResult().get(0).getIegCustomerList());
                                    }
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.SELECT, "售");
                                    PreferencesUtil.put(LoginActivity.this,
                                            InterfaceDefinition.PreferencesUser.TITLE, mbean.getResult().get(0).getCompanyName());
                                }
                                Tab1Fragment.micon.clear();
                                for (int i = 0; i < mbean.getResult().get(0).getBannerList().size(); i++) {
                                    Tab1Fragment.micon.add(InterfaceDefinition.IPath.BASE_URL+mbean.getResult().get(0).getBannerList().get(i).getIcon().getFileName());
                                }
                                gotoActivity(MainActivity.class);
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
