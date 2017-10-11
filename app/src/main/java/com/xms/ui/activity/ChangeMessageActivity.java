package com.xms.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.bean.ChangeMessageBean;
import com.xms.callback.MyStringCallback;
import com.xms.constants.InterfaceDefinition;
import com.xms.utils.ChoiceSDCardImageLoader;
import com.xms.utils.DefaultDisplayImageOptions;
import com.xms.utils.LoadLocalImageUtil;
import com.xms.utils.PreferencesUtil;
import com.xms.utils.StringUtil;
import com.xms.utils.ToastUtil;
import com.xms.widget.RoundImageView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class ChangeMessageActivity extends BaseActivity {
    @BindView(R.id.name)
    EditText name0;
    @BindView(R.id.workname)
    TextView workname;
    private ChoiceSDCardImageLoader loader;
    //    @BindView(R.id.time)
//    TextView mtime;
    @BindView(R.id.changemessage_image)
    RoundImageView mimage;
    private DisplayImageOptions mOptions = DefaultDisplayImageOptions
            .getDefaultDisplayImageOptions(this);
    @Override
    public int getContentViewId() {
        return R.layout.activity_change_message;
    }

    private ArrayList<String> list;
    public static final int REQUEST_CAMERA_CODE = 0;
    public static final int REQUEST_PREVIEW_CODE = 2;

    @Override
    public void initData(){
        setTitle();
        mTvForTitle.setText("修改个人信息");
        list = new ArrayList<>();
        name0.setText((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.USENAME, ""));
        workname.setText((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.SSGS, "无"));
        ImageLoader.getInstance().displayImage((String) PreferencesUtil.get(this,
                InterfaceDefinition.PreferencesUser.PERSONIMG, ""), mimage,mOptions);
        Request();
    }

    @OnClick({R.id.common_title_left, R.id.act_login_login, R.id.changemessage_top})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
//            case R.id.time:
//                time();
//                break;
            case R.id.act_login_login:
                Request(name0.getText().toString().trim());
                break;
            case R.id.changemessage_top:
                PhotoPickerIntent intent = new PhotoPickerIntent(ChangeMessageActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                // intent.setShowCarema(true); // 是否显示拍照， 默认false
                intent.setMaxTotal(1); // 最多选择照片数量，默认为9
                intent.setSelectedPaths(list); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
                break;
            default:
                break;
        }
    }

    //图片选择
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
            }
        }
    }

    private void refreshAdpater(ArrayList<String> paths) {
        // 处理返回照片地址
        if (paths != null && paths.size() > 0) {
            list.clear();
            list.addAll(paths);
            //   ToastUtil.TextToast(list.get(0));
            LoadLocalImageUtil.getInstance().displayFromSDCard(list.get(0), mimage);
            //     loader.loadImage(3, list.get(0),mimage);
        }
    }

    private ChangeMessageBean mbean;

    private void Request() {

        JSONObject object = new JSONObject();
        object.put("userId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.USERID, ""));
//        object.put("companyId", PreferencesUtil.get(mContext,InterfaceDefinition.PreferencesUser.COMPANYID,""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "myInfo.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(this){
                    @Override
                    public void onResponse(String response){
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")){
                                mbean = null;
                                Gson gson = new Gson();
                                mbean = gson.fromJson(response, ChangeMessageBean.class);
                                name0.setText(mbean.getResult().getUserName());
                                workname.setText(mbean.getResult().getCompanyName());
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }

    private void Request(final String name) {
        if (StringUtil.isEmpty(name)) {
            ToastUtil.TextToast("姓名不能为空！");
            return;
        }
      JSONObject object = new JSONObject();
        object.put("userName", name);
        object.put("userId", PreferencesUtil.get(this, InterfaceDefinition.PreferencesUser.USERID, ""));
//        object.put("companyId", PreferencesUtil.get(mContext,InterfaceDefinition.PreferencesUser.COMPANYID,""));
        Log.e("请求报文", "{" + InterfaceDefinition.ICommonKey.REQUEST_DATA + ":" + object.toString() + "}");
        OkHttpUtils
                .post()//
                .url(InterfaceDefinition.Login.URL + "myInfo1.do")//
                .addParams(InterfaceDefinition.ICommonKey.REQUEST_DATA, object.toString())
                .build()
                .execute(new MyStringCallback(this) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("请求数据", "" + response);
                            JSONObject object = JSON.parseObject(response);
                            if (object.getBoolean("success")) {
                                ToastUtil.TextToast("修改成功");
                                PreferencesUtil.put(ChangeMessageActivity.this,
                                        InterfaceDefinition.PreferencesUser.USENAME, name0.getText().toString().trim());
                                Request();
                                MyMessageFragment.select = true;
                            } else {
                                ToastUtil.TextToast(object.getString("info"));
                            }
                        } catch (Exception ioex) {
                            ToastUtil.TextToast("请检查网络连接是否正常");
                        }


                    }
                });
    }

//    //时间选择器
//    private WheelMain wheelMain;
//    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//    private void time(){
//        LayoutInflater inflater1 = LayoutInflater
//                .from(ChangeMessageActivity.this);
//        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
//                null);
//        ScreenInfo screenInfo1 = new ScreenInfo(ChangeMessageActivity.this);
//        wheelMain = new WheelMain(timepickerview1, true, 3);
//        wheelMain.screenheight = screenInfo1.getHeight();
//        Calendar calendar1 = Calendar.getInstance();
//        String time1 = calendar1.get(Calendar.YEAR) + "-"
//                + (calendar1.get(Calendar.MONTH) + 1) + "-"
//                + calendar1.get(Calendar.DAY_OF_MONTH)
//                + calendar1.get(Calendar.HOUR_OF_DAY)
//                + calendar1.get(Calendar.MINUTE);
//        if (JudgeDate.isDate(time1, "yyyy-mm-dd")) {
//            try {
//                calendar1.setTime(dateFormat.parse(time1));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        int year = calendar1.get(Calendar.YEAR);
//        int month = calendar1.get(Calendar.MONTH);
//        int day = calendar1.get(Calendar.DAY_OF_MONTH);
//        //需要精确到分就取消注释
////		int hour = calendar1.get(Calendar.HOUR_OF_DAY);
////		int minute = calendar1.get(Calendar.MINUTE);
//        wheelMain.initDateTimePicker(year, month, day
//                //, hour, minute
//        );
//        MyAlertDialog dialog = new MyAlertDialog(ChangeMessageActivity.this)
//                .builder().setTitle("选择日期").setView(timepickerview1)
//                .setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                });
//        dialog.setPositiveButton("确定", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String currentDate = "";
//                String format = "yyyy-MM-dd  HH:mm";
//                // if (DateUtil.timeCompare(DateUtil.getStringDate(format),
//                // wheelMain.getTime("%02d"))) {
//                currentDate = wheelMain.getTime("%02d");
//                //	String time[] = StringUtil.GetMyMessage(currentDate, " ");
//                mtime.setText(currentDate);
//            }
//        });
//        dialog.show();
//    }

}
