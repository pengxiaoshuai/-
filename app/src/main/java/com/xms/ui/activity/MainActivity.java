package com.xms.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xms.R;
import com.xms.application.AppContext;
import com.xms.base.BaseActivity;
import com.xms.grobal.AppManager;
import com.xms.ui.fragment.Tab1Fragment;
import com.xms.utils.CommonDialog;
import com.xms.utils.ToastUtil;

import butterknife.BindView;


/**
 * 主Activity
 *
 * @author 彭其煊
 * @version 1.0
 * @date 2017.2.24
 */
public class MainActivity extends BaseActivity {

//    @BindView(R.id.rb1)
//    RadioButton mRbt1;
//
//    @BindView(R.id.rb2)
//    RadioButton mRbt2;
//
//    @BindView(R.id.rb3)
//    RadioButton mRbt3;


    @BindView(R.id.act_main_ftb)
    public FragmentTabHost mFragmentTabHost;

    @BindView(R.id.group)
    RadioGroup mGrop;
    @BindView(R.id.rb1)
    public RadioButton mradiobutton1;
    @BindView(R.id.rb2)
    public RadioButton mradiobutton;

    boolean isExit;// 标记是否退出

    private AppContext mAppContext;

    private int index = 1;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(){
        mAppContext = (AppContext) getApplicationContext();
        //   ToastUtil.TextToast("登陆成功");
        initFragmentTabHost();
        verifyStoragePermissions(this);
    }

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.CALL_PHONE"};
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 2);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //初始化首页数据
    private void initFragmentTabHost(){
        mFragmentTabHost.setup(this, getSupportFragmentManager(),
                R.id.act_main_fl);
        String Tab1 = getResources().getString(R.string.tab_1);
        String Tab2 = getResources().getString(R.string.tab_2);
        String Tab3 = getResources().getString(R.string.tab_3);
        mFragmentTabHost.addTab(
                mFragmentTabHost.newTabSpec(Tab1).setIndicator(Tab1),
                Tab1Fragment.class, null);
        mFragmentTabHost.addTab(
                mFragmentTabHost.newTabSpec(Tab2).setIndicator(Tab2),
                MyMessageFragment.class, null);
//        mFragmentTabHost.addTab(
//                mFragmentTabHost.newTabSpec(Tab3).setIndicator(Tab3),
//                MyFragment.class, null);
        mGrop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch (checkedId){
                    case R.id.rb1:
                        index=1;
                        mFragmentTabHost.setCurrentTab(0);
                        break;
                    case R.id.rb2:
                        index=2;
                        mFragmentTabHost.setCurrentTab(1);
                        break;
                    case R.id.rb3:
                        if (index==1){
                            mradiobutton1.setChecked(true);
                        }else{
                            mradiobutton.setChecked(true);
                        }
                        Dialog("4006118900");
                        //    mFragmentTabHost.setCurrentTab(2);
                        break;

                    default:
                        break;

                }
            }
        });
        mFragmentTabHost.setCurrentTab(0);
    }

    //
//    @OnClick(R.id.common_dialog_cancle)
//    void finishA(View view) {
//        switch (view.getId()){
//            R.id.common_dialog_cancle:
//            break;
//        }
//    }
    // 重写onkeyDown方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    // 退出方法
    private void exit(){
        if (!isExit){
            isExit = true;
            ToastUtil.TextToast("再按一次退出程序");
            mhandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            AppManager.getAppManager().AppExit(MainActivity.this);
        }

    }

    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    private void Dialog(final String phone){
        CommonDialog dialog = new CommonDialog(this,
                R.style.dialog);
        dialog.setIcon(R.mipmap.logo);
        dialog.setContent("您是否要拨打客服电话？");
        dialog.setLeftBtnText("取消");
        dialog.setRightBtnText("确定");
        dialog.setListener(new CommonDialog.DialogClickListener(){

            @Override
            public void onRightBtnClick(Dialog dialog){

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                        + phone));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.TextToast("请赋予拨打电话的权限后重试");
                    return;
                }
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

