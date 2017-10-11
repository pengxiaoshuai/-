package com.xms.ui.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.utils.DefaultDisplayImageOptions;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.StringUtil;
import com.xms.utils.ToastUtil;
import com.xms.widget.RoundImageView;
import com.xms.widget.timecheck.JudgeDate;
import com.xms.widget.timecheck.MyAlertDialog;
import com.xms.widget.timecheck.ScreenInfo;
import com.xms.widget.timecheck.WheelMain;
import com.zhy.http.okhttp.OkHttpUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


public class BxglActivity extends BaseActivity {

    @BindView(R.id.time)
    TextView mtext;
    @BindView(R.id.common_title_tv)
    TextView mtitle;
    @BindView(R.id.title)
    EditText title0;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.message)
    EditText message;
    @BindView(R.id.bxgl_name)
    TextView mname;
    @BindView(R.id.bxgl_workname)
    TextView mworkname;
    @BindView(R.id.bxgl_imgae)
    RoundImageView mimage;
    private DisplayImageOptions mOptions = DefaultDisplayImageOptions
            .getDefaultDisplayImageOptions(this);
    @Override
    public int getContentViewId() {
        return R.layout.activity_bxgl;
    }

    @Override
    public void initData() {
        mtitle.setText("报修管理");
        mname.setText((String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.USENAME,""));
        mworkname.setText((String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.SSGS,""));
        ImageLoader.getInstance().displayImage((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.PERSONIMG,""),mimage,
                mOptions);
        phone.setText((String)PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.PHONE,""));
    //    Request();
    }

    @OnClick({R.id.common_title_left, R.id.bxgl_top, R.id.time, R.id.act_login_login, R.id.common_title_right})
    void OnClick(View view) {
        switch (view.getId()){
            case R.id.common_title_left:
                finish();
                break;
            case R.id.bxgl_top:
                gotoActivity(ChangeMessageActivity.class);
                break;
            case R.id.time:
                if (dialog == null){
                    time();
                } else {
                    dialog.show();
                }
                break;
            case R.id.act_login_login:
                Request(title0.getText().toString().trim(), mtext.getText().toString().trim(), phone.getText().toString().trim()
                        , message.getText().toString().trim());
                break;
            case R.id.common_title_right:
                gotoActivity(BxjllbActivity.class);
                break;
            default:
                break;
        }
    }

    //时间选择器
    private WheelMain wheelMain;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private MyAlertDialog dialog;

    private void time() {
        LayoutInflater inflater1 = LayoutInflater
                .from(BxglActivity.this);
        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                null);
        ScreenInfo screenInfo1 = new ScreenInfo(BxglActivity.this);
        wheelMain = new WheelMain(timepickerview1, true, 3);
        wheelMain.screenheight = screenInfo1.getHeight();
        Calendar calendar1 = Calendar.getInstance();
        String time1 = calendar1.get(Calendar.YEAR) + "-"
                + (calendar1.get(Calendar.MONTH) + 1) + "-"
                + calendar1.get(Calendar.DAY_OF_MONTH)
                + calendar1.get(Calendar.HOUR_OF_DAY)
                + calendar1.get(Calendar.MINUTE);
        if (JudgeDate.isDate(time1, "yyyy-mm-dd")) {
            try {
                calendar1.setTime(dateFormat.parse(time1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        int day = calendar1.get(Calendar.DAY_OF_MONTH);
        //需要精确到分就取消注释
//		int hour = calendar1.get(Calendar.HOUR_OF_DAY);
//		int minute = calendar1.get(Calendar.MINUTE);
        wheelMain.initDateTimePicker(year, month, day
                //, hour, minute
        );
        dialog = new MyAlertDialog(BxglActivity.this)
                .builder().setTitle("选择日期").setView(timepickerview1)
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDate = "";
                String format = "yyyy-MM-dd";
                // if (DateUtil.timeCompare(DateUtil.getStringDate(format),
                // wheelMain.getTime("%02d"))) {
                currentDate = wheelMain.getTime("%02d");
                //	String time[] = StringUtil.GetMyMessage(currentDate, " ");
                mtext.setText(currentDate);
            }
        });
        dialog.show();
    }


    private void Request(final String title, final String data, final String mobilephone, final String detail) {
      //  Log.e("000",getTime(data));
        JSONObject object = new JSONObject();
        if (StringUtil.isEmpty(title)) {
            ToastUtil.TextToast("故障标题不能为空！");
            return;
        }
        if (StringUtil.isEmpty(data)){
            ToastUtil.TextToast("发生时间不能为空！");
            return;
        }

        if (StringUtil.isEmpty(mobilephone)) {
            object.put("mobilephone", PreferencesUtil.get(BxglActivity.this,
                    InterfaceDefinition.PreferencesUser.PHONE, ""));
        } else {
            if (StringUtil.isNotMobileNO(mobilephone)) {
                ToastUtil.TextToast("请输入正确的手机号！");
                return;
            }
            object.put("mobilephone", mobilephone);
        }
        if (StringUtil.isEmpty(detail)) {
            ToastUtil.TextToast("请简单描述故障情况！");
            return;
        }


        object.put("title", title);
        object.put("date", Long.parseLong(getTime(data))*1000);
        object.put("detail", detail);
        object.put("userId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.USERID, ""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "repair.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(BxglActivity.this) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")) {
                                ToastUtil.TextToast("提交成功");
                                gotoActivity(BxjllbActivity.class);
                                finish();
//                                phone.setText("");
//                                title0.setText("");
//                                message.setText("");
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }

    // 将字符串转为时间戳
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (Exception e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return re_time;
    }
    private void Request(){
        JSONObject object=new JSONObject();
        object.put("userId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.USERID,""));
        Log.e("请求报文","{"+InterfaceDefinition.ICommonKey.REQUEST_DATA+":"+object.toString()+"}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "repairList.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA,object.toString())
                .build()
                .execute(new MyStringCallback(BxglActivity.this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("请求数据",""+response);
                            JSONObject object= JSON.parseObject(response);
                            if (object.getBoolean("success")){

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
