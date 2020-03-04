package com.example.ui.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Utils.TextUtil;
import com.example.base.GBaseActivity;
import com.example.bean.BaseBean;
import com.example.bean.OrderInfoBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.mvp.orderInfo.OrderInfoContract;
import com.example.mvp.orderInfo.OrderInfoPresenter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;

import butterknife.BindView;

/**
 * 创建时间 2019/12/20 17:19
 * 描述:
 */
public class OrdersInfoActivity extends GBaseActivity<OrderInfoPresenter> implements OrderInfoContract.View {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.tv_name)
    TextView mTvName;


    @BindView(R2.id.tv_order_type)
    TextView mTvOrderType;

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
    private String mOrderId;
    private String mKey;


    @Override
    public int setLayoutId() {
        return R.layout.activity_orders_info;
    }

    @Override
    public void initOthers() {
        mPresenter = new OrderInfoPresenter();
        mPresenter.attachView(this);
        mKey = AppConstant.KEY;
        mOrderId = getIntent().getStringExtra("OrderId");
        log("mOrderId==" + mOrderId);
        initToolbar("订单详情");
        mTvTitleRight.setVisibility(View.GONE);
        mPresenter.getOrderInfoData(mKey, mOrderId);

    }


    /**
     * 详情
     *
     * @param bean
     */
    @Override
    public void getOrderInfoData(BaseBean<OrderInfoBean> bean) {
        if (bean.getCode() == 200) {
            OrderInfoBean datas = bean.getDatas();
            initView(datas);
        }
    }

    /**
     * 初始化数据
     *
     * @param datas
     */
    private void initView(OrderInfoBean datas) {
        //订单类型
        String type = "";
        switch (datas.getOrder_state()) {
            case "0":
                type = "订单已取消";
                break;
            case "1":
                type = "订单未支付";
                break;
            case "2":
                type = "订单已支付";
                break;
            case "3":
                type = "订单已完成";
                break;
        }
        mTvOrderType.setText(type);
        //名字
        mTvName.setText("病人姓名：" + datas.getBuyer_name());
        //咨询时间
        mTvTime.setText("咨询时间：" + datas.getAdd_time());
        //咨询类型
        mTvType.setText("咨询类型：" + (datas.getOrder_type() == 1 ? "图文咨询" : "电话咨询"));
        //时长
        mTvTime1.setText("时长：" + TextUtil.getTime(datas.getConsult_time(), mContext));
        //总计
        mTvPrice.setText("总计：" + datas.getOrder_price());
        //订单编号 c_order_sn
        mTvId.setText("订单编号：" + datas.getC_order_sn());
        //支付单号： pay_sn
        mTvPayId.setText("支付单号：" + datas.getPay_sn());
        //开始时间：
        mTvStartTime.setText("开始时间：" + datas.getConsult_s_time());
        //结束时间：
        mTvEndTime.setText("结束时间：" + datas.getConsult_e_time());
    }

    @Override
    public void onError(Throwable throwable) {
        log("报错:" + throwable.toString());
    }
}
