package com.xms.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.xms.R;
import com.xms.adapter.BaseRecyclerAdapter;
import com.xms.base.BaseFragment;
import com.xms.holder.BaseRecyclerHolder;
import com.xms.widget.MyLinearLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;

public class CssjFragment extends BaseFragment {

    private View mRootView;
    @BindView(R.id.cssj_recyclerview)
    RecyclerView mrecyclerview;
    private ArrayList<CssjBean> mlist;
    private BaseRecyclerAdapter<CssjBean> madapter;
    private ArrayList<EditText> medits;
    @Override
    protected View initView(LayoutInflater inflater){
        mRootView = inflater.inflate(R.layout.fragment_cssj, null);
        return mRootView;
    }

    @Override
    public void initData(){
        if (!isFirstLoad()){
            return;
        }
        setFirstLoad(false);
        mlist = new ArrayList<>();
        mlist.add(new CssjBean("预测用电量:","","万千瓦时"));
        mlist.add(new CssjBean("用户实际用电量:","","万千瓦时"));
        mlist.add(new CssjBean("双边协商月分解电量:","","万千瓦时"));
        mlist.add(new CssjBean("双边协商价差:","","厘/千瓦时"));
        mlist.add(new CssjBean("月度竞价成交电量:","","万千瓦时"));
        mlist.add(new CssjBean("月度出清结算价:","","厘/千瓦时"));
        mlist.add(new CssjBean("允许偏差范围±%:","偏差范围以内电量参考","%"));
        medits = new ArrayList<>();
        madapter = new BaseRecyclerAdapter<CssjBean>(mContext,mlist,R.layout.adapter_item_lrjsq) {
            @Override
            public void convert(BaseRecyclerHolder holder, CssjBean item, int position, boolean isScrolling){
                holder.setText(R.id.adapter_item_lrjsq_left,item.getLeft());
                holder.setEdittextHint(R.id.adapter_item_lrjsq_edit,item.getHint());
                holder.setText(R.id.adapter_item_lrjsq_right,item.getRight());
                medits.add((EditText) holder.getView(R.id.adapter_item_lrjsq_edit));//添加Edittextview到集合里
            }
        };
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(mContext);
        linearLayoutManager.setScrollEnabled(false);
        mrecyclerview.setLayoutManager(linearLayoutManager);
        mrecyclerview.setAdapter(madapter);
        madapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
              //  ToastUtil.TextToast(medits.get(position).getText().toString());
            }
        });
        hiddenKeyboard(getActivity());
    }

    class CssjBean {
        public CssjBean(String left, String hint, String right) {
            this.left = left;
            this.hint = hint;
            this.right = right;
        }

        public String getLeft() {

            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getRight() {
            return right;
        }

        public void setRight(String right) {
            this.right = right;
        }

        private String left;
        private String hint;
        private String right;

    }
}
