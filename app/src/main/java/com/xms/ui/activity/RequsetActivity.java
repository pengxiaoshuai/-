package com.xms.ui.activity;


import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.utils.MyCountDownTimer;
import com.xms.utils.StringUtil;
import com.xms.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.OnClick;

public class RequsetActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password2)
    EditText password2;

    @Override
    public int getContentViewId() {
        return R.layout.activity_requset;
    }

    @BindView(R.id.getcode)
    TextView mgetcode;
    private MyCountDownTimer myCountDownTimer;

    @Override
    public void initData() {
        setTitle();
        mTvForTitle.setText("注册账号");

    }

    @OnClick({R.id.common_title_left, R.id.ok, R.id.back, R.id.getcode})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            case R.id.ok:
                Request0(phone.getText().toString().trim(),code.getText().toString().trim(),
                        name.getText().toString().trim(),password.getText().toString().trim(),
                        password2.getText().toString().trim());
                break;
            case R.id.back:
                finish();
                break;
            case R.id.getcode:
                if (StringUtil.isEmpty(phone.getText().toString().trim())){
                    ToastUtil.TextToast("手机号码为空，请重新输入!");
                    return;
                }
                if (!StringUtil.isMobileNO(phone.getText().toString().trim())){
                    ToastUtil.TextToast("输入的手机号有误！");
                    return;
                }

                Request(phone.getText().toString().trim());
                if (myCountDownTimer != null) {
                    myCountDownTimer.start();
                } else {
                    myCountDownTimer = new MyCountDownTimer(mgetcode, 60000, 1000);
                    myCountDownTimer.start();
                }
                break;
            default:
                break;
        }
    }
    private void Request0(final String mobile,final String code,final String name, final String password,final String password2){
        if (StringUtil.isEmpty(mobile)){
            ToastUtil.TextToast("手机号码为空，请重新输入!");
            return;
        }
        if (StringUtil.isEmpty(name)){
            ToastUtil.TextToast("姓名不能为空!");
        }
        if (StringUtil.isEmpty(code)){
            ToastUtil.TextToast("验证码不能为空!");
        }
        if (StringUtil.isEmpty(password)){
            ToastUtil.TextToast("登录密码不能为空");
            return;
        }
        if (StringUtil.isEmpty(password2)){
            ToastUtil.TextToast("确认密码不能为空!");
            return;
        }
        if (!StringUtil.isMobileNO(mobile)){
            ToastUtil.TextToast("手机号码输入有误，请重新输入!");
            return;
        }

        if (StringUtil.isNotPassWord(password)){
            ToastUtil.TextToast("请输入6-18位的有效密码");
            return;
        }
        if (!password.equals(password2)){
            ToastUtil.TextToast("两次输入的密码不相同，请重新输入");
            return;
        }

        JSONObject object=new JSONObject();
        try {
            object.put("mobilephone",mobile);
            object.put("vcode",code);
            object.put("name",name);
            object.put("pwd",password);
        } catch (Exception e){
            e.printStackTrace();
        }
//        String requestData= null;
//        try {
//            requestData = Base64.encodeToString(object.toString().getBytes("UTF-8"),0);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
 //       Log.e("请求报文",requestData);
        //{"curDateTime":1500729242114,"info":"操作完成","recode":10001000,"result":"成功","sessionId":"DA5EFD482A196F9A28F28A1F00C3D86A","status":"y","success":true}
        Log.e("请求报文","{"+InterfaceDefinition.ICommonKey.REQUEST_DATA+":"+object.toString()+"}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "register.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA,object.toString())
                .build()
                .execute(new MyStringCallback(RequsetActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("请求数据",""+response);
                            JSONObject object=JSON.parseObject(response);
                            if (object.getBoolean("success")){
                                LoginActivity.PHONE=mobile;
                                LoginActivity.PASSWORD=password;
                                gotoActivity(LoginActivity.class);
                                ToastUtil.TextToast("注册成功!");
                            }else{
                              //  ToastUtil.TextToast("用户未注册，或密码错误！");
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }
    private void Request(final String mobile) {

        JSONObject object = new JSONObject();
        object.put(InterfaceDefinition.Login.MOBILE, mobile);//mobilephone=15979093097
//        String requestData= Base64.encodeToString(object.toString().getBytes(),0);
//        //  String requestData= Base64.encodeToString(object.toString().getBytes(),0);
//        Log.e("请求报文",requestData);
//        //{"curDateTime":1500719875628,"info":"请勿频繁发送验证码！","recode":-99999999,"result":"请勿频繁发送验证码！","sessionId":"FFE2DFE86700AC4E1BE41355EF90519C","status":"n","success":false}
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "sendVcode.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(RequsetActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject object1= JSON.parseObject(response);
                            Log.e("请求数据", "" + response);
                            if (object1.getBoolean("success")){
                                ToastUtil.TextToast("验证码发送成功!");
                            }else{
                                ToastUtil.TextToast(object1.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }
                    }
                });
    }

    public static void main(String[]a){
        JSONObject object=new JSONObject();
            object.put("mobilephone","15979093097");
            object.put("vcode","1234");
            object.put("name","name");
            object.put("pwd","12346");
        String requestData= null;
        try {
            requestData = Base64.encodeToString(object.toString().getBytes("UTF-8"),Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.print(1);
    }

}

