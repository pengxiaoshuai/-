package com.xms.ui.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xms.R;
import com.xms.base.BaseFragment;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.StringUtil;
import com.xms.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AqszFragment extends BaseFragment {
    private View mRootView;
    @BindView(R.id.password)
    EditText med1;
    @BindView(R.id.password2)
    EditText med2;
    @BindView(R.id.password3)
    EditText med3;

    @Override
    protected View initView(LayoutInflater inflater) {
        mRootView = inflater.inflate(R.layout.fragment_aqsz, null);
        return mRootView;
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.act_login_login)
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.act_login_login:
                Request(med1.getText().toString().trim(),med2.getText().toString().trim(),med3.getText().toString().trim());
                break;
            default:
                break;
        }
    }
    private void Request(final String password,final String password2,final String password3){
        if (StringUtil.isNotPassWord(password)){
            ToastUtil.TextToast("原密码输入有误！");
            return;
        }
        if (StringUtil.isNotPassWord(password2)){
            ToastUtil.TextToast("新密码输入有误！");
            return;
        }
        if (!password2.equals(password3)){
            ToastUtil.TextToast("确认密码和新密码输入不一致！");
            return;
        }

        JSONObject object=new JSONObject();
        object.put("password",password);
        object.put("newPassWord",password2);
        object.put("userId", PreferencesUtil.get(mContext, InterfaceDefinition.PreferencesUser.USERID,""));
//        object.put("companyId", PreferencesUtil.get(mContext,InterfaceDefinition.PreferencesUser.COMPANYID,""));
        Log.e("请求报文","{"+InterfaceDefinition.ICommonKey.REQUEST_DATA+":"+object.toString()+"}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "modificationPwd.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA,object.toString())
                .build()
                .execute(new MyStringCallback(mContext){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("请求数据",""+response);
                            JSONObject object= JSON.parseObject(response);
                            if (object.getBoolean("success")){
                                ToastUtil.TextToast("修改成功");
                                med1.setText("");
                                med2.setText("");
                                med3.setText("");
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
