package com.xms.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by dell on 2017/7/22.
 */

//复写倒计时
public class MyCountDownTimer extends CountDownTimer {
    private TextView mtext;
    public MyCountDownTimer(TextView mtext,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mtext =mtext;
    }

    //计时过程
    @Override
    public void onTick(long l) {
        //防止计时过程中重复点击
        mtext.setEnabled(false);
        mtext.setText("重新发送("+l/1000+")");

    }

    //计时完毕的方法
    @Override
    public void onFinish(){
        //重新给Button设置文字
        mtext.setText("获取验证码");
        //设置可点击
        mtext.setEnabled(true);
    }
}
