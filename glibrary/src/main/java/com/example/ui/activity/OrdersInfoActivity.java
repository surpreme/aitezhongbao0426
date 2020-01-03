package com.example.ui.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.lzy.basemodule.base.BaseActivity;

import butterknife.BindView;

/**
 * 创建时间 2019/12/20 17:19
 * 描述:
 */
public class OrdersInfoActivity extends BaseActivity {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.tv_time)
    TextView mTvTime;
    @BindView(R2.id.tv_type)
    TextView mTvType;
    @BindView(R2.id.tv_time1)
    TextView mTvTime1;
    @BindView(R2.id.tv_price)
    TextView mTvPrice;
    @BindView(R2.id.tv_id)
    TextView mTvId;
    @BindView(R2.id.tv_pay_id)
    TextView mTvPayId;
    @BindView(R2.id.tv_start_time)
    TextView mTvStartTime;
    @BindView(R2.id.tv_end_time)
    TextView mTvEndTime;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_orders_info;
    }

    @Override
    protected void initView() {
        initToolbar("订单详情");
        mTvTitleRight.setVisibility(View.GONE);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

}
