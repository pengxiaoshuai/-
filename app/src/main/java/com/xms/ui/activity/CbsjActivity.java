package com.xms.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.xms.R;
import com.xms.adapter.BaseRecyclerAdapter;
import com.xms.base.BaseActivity;
import com.xms.holder.BaseRecyclerHolder;
import com.xms.widget.timecheck.JudgeDate;
import com.xms.widget.timecheck.MyAlertDialog;
import com.xms.widget.timecheck.ScreenInfo;
import com.xms.widget.timecheck.WheelMain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class CbsjActivity extends BaseActivity {
    @BindView(R.id.mtime)
    TextView mtext;
    private String[] mItems = new String[]{"1号测量点","2号测量点","3号测量点","4号测量点"};
    private ArrayAdapter<String> adapter;
    @BindView(R.id.act_maidian_spinner)
    Spinner mspinner;
    private ArrayList<CbsjBean> mlist;
    private BaseRecyclerAdapter<CbsjBean> madapter;
    @BindView(R.id.cbsj_recyclerview)
    RecyclerView mrecyclerview;
    @Override
    public int getContentViewId() {
        return R.layout.activity_cbsj;
    }

    @Override
    public void initData(){
        setTitle();
        mTvForTitle.setText("电量电费");
        // 建立Adapter并且绑定数据源
        adapter = new ArrayAdapter<String>(CbsjActivity.this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        mspinner.setAdapter(adapter);
        initRecyclerview();//初始化recyclerview的基础数据
    }
    @OnClick({R.id.common_title_left,R.id.mtime})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.common_title_left:
                finish();
                break;
            case R.id.mtime:
                time();
                break;
            default:
                break;
        }
    }
    private void initRecyclerview(){
        mlist = new ArrayList<>();
        mlist.add(new CbsjBean("抄表时间","电度数(kwh)","频率(hz)"));
        mlist.add(new CbsjBean("2017-03-12","986458.00","98"));
        mlist.add(new CbsjBean("2017-03-12","986458.00","98"));
        mlist.add(new CbsjBean("2017-03-12","986458.00","98"));
        mlist.add(new CbsjBean("2017-03-12","986458.00","98"));
        mlist.add(new CbsjBean("2017-03-12","986458.00","98"));
        madapter=new BaseRecyclerAdapter<CbsjBean>(this,mlist,R.layout.adapter_item_cbsj_recyclerview) {
            @Override
            public void convert(BaseRecyclerHolder holder, CbsjBean item, int position, boolean isScrolling) {
                    if (position == 0 ){
                        holder.setText(R.id.adapter_cbsj_text1,item.getMtime());
                        holder.setText(R.id.adapter_cbsj_text2,item.getDds());
                        holder.setText(R.id.adapter_cbsj_text3,item.getPl());
                        holder.setTextBackground(R.id.adapter_cbsj_text1,getResources().getColor(R.color.color_cbsj_rec));
                        holder.setTextBackground(R.id.adapter_cbsj_text2,getResources().getColor(R.color.color_cbsj_rec));
                        holder.setTextBackground(R.id.adapter_cbsj_text3,getResources().getColor(R.color.color_cbsj_rec));
                        holder.setTextColor(R.id.adapter_cbsj_text1,getResources().getColor(R.color.white));
                        holder.setTextColor(R.id.adapter_cbsj_text2,getResources().getColor(R.color.white));
                        holder.setTextColor(R.id.adapter_cbsj_text3,getResources().getColor(R.color.white));
                    }else{
                        holder.setText(R.id.adapter_cbsj_text1,item.getMtime());
                        holder.setText(R.id.adapter_cbsj_text2,item.getDds());
                        holder.setText(R.id.adapter_cbsj_text3,item.getPl());
                    }
            }
        };
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerview.setAdapter(madapter);
    }
    //时间选择器
    private WheelMain wheelMain;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private void time(){
        LayoutInflater inflater1 = LayoutInflater
                .from(CbsjActivity.this);
        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                null);
        ScreenInfo screenInfo1 = new ScreenInfo(CbsjActivity.this);
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
        MyAlertDialog dialog = new MyAlertDialog(CbsjActivity.this)
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
                String format = "yyyy-MM-dd  HH:mm";
                // if (DateUtil.timeCompare(DateUtil.getStringDate(format),
                // wheelMain.getTime("%02d"))) {
                currentDate = wheelMain.getTime("%02d");
                //	String time[] = StringUtil.GetMyMessage(currentDate, " ");
                mtext.setText(currentDate);
            }
        });
        dialog.show();
    }
    class CbsjBean{
        public CbsjBean(String mtime, String dds, String pl) {
            this.mtime = mtime;
            this.dds = dds;
            this.pl = pl;
        }

        public String getMtime() {

            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public String getDds() {
            return dds;
        }

        public void setDds(String dds) {
            this.dds = dds;
        }

        public String getPl() {
            return pl;
        }

        public void setPl(String pl) {
            this.pl = pl;
        }

        private String mtime;
        private String dds;
        private String pl;
    }
}
