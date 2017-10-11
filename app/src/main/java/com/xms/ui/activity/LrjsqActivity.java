package com.xms.ui.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xms.R;
import com.xms.base.BaseActivity;
import com.xms.constants.InterfaceDefinition;

import butterknife.BindView;
import butterknife.OnClick;

public class LrjsqActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mwebview;

    @Override
    public int getContentViewId() {
        return R.layout.activity_lrjsq;
    }

    @OnClick(R.id.common_title_left)
    void Onclick(View view) {
        switch (view.getId()) {
            case R.id.common_title_left:
                finish();
                break;
            default:
                break;

        }
    }

    @Override
    public void initData(){
        setTitle();
        mTvForTitle.setText("售电利润计算器");
       // Request();
        mwebview.loadUrl(InterfaceDefinition.Login.URL_WEBVIEW);
        WebSettings setting = mwebview.getSettings();
        setting.setJavaScriptEnabled(true);//支持js
        setting.setDefaultTextEncodingName("utf-8");

    }

}
