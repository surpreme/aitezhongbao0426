package com.aite.a.activity.li.activity.topuppays;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aite.a.activity.MainActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.alipaylibrary.bean.AlipayOrderIdBean;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aiteshangcheng.a.R;
import com.aiteshangcheng.a.R2;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.logcat.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 */

public class TopupPaysActivity extends BaseActivity<TopupPaysContract.View, TopupPaysPresenter> implements TopupPaysContract.View {

    @BindView(R2.id._tv_name)
    TextView TvName;
    @BindView(R2.id._iv_right)
    ImageView IvRight;
    @BindView(R2.id._tx_right)
    TextView TxRight;
    @BindView(R2.id._tv_back)
    TextView TvBack;
    @BindView(R2.id._iv_back)
    ImageView IvBack;
    @BindView(R2.id.tv_ordersn)
    TextView tvOrdersn;
    @BindView(R2.id.tv_money)
    TextView tvMoney;
    @BindView(R2.id.iv_zfb)
    ImageView ivZfb;
    @BindView(R2.id.iv_wx)
    ImageView ivWx;
    @BindView(R2.id.tv_confirm)
    TextView tvConfirm;
    private String PAY_TAG = "";
    private String PAY_SN = "";
    private ThingsInformationBean thingsInformationBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_topuppay;
    }

    @Override
    protected void initView() {
        initToolbar("支付");
        if (getIntent().getStringExtra("Parcel_Number") != null) {
            tvOrdersn.setText(String.format("充值单号:%s", getIntent().getStringExtra("Parcel_Number")));
            if (getIntent().getStringExtra("Money_Price") != null) {
                tvMoney.setText(String.format("￥ %s", getIntent().getStringExtra("Money_Price")));
                if (getIntent().getStringExtra("pay_sn") != null) {
                    this.PAY_SN = getIntent().getStringExtra("pay_sn");
                } else {
                    showToast("系统繁忙.....");
                    onBackPressed();
                }
            }

        }

    }

    @Override
    protected void initDatas() {
        if (PAY_SN != null && !PAY_SN.equals("") && !PAY_SN.equals("null")) {
            mPresenter.getPayThingInformation(initListHttpParams(true, new ContentValue("pdr_sn", PAY_SN)));
        }

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @OnClick({R2.id.iv_zfb, R2.id.iv_wx, R2.id.tv_confirm})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_zfb) {
            PAY_TAG = "alipay";
            ivWx.setImageResource(R.drawable.topup_paywx1);
            ivZfb.setImageResource(R.drawable.topup_payzfb2);
        } else if (id == R.id.iv_wx) {
            PAY_TAG = "app_wxpay";
            ivWx.setImageResource(R.drawable.topup_paywx2);
            ivZfb.setImageResource(R.drawable.topup_payzfb1);
        } else if (id == R.id.tv_confirm) {
            mPresenter.getPayConfiguration(initListHttpParams(
                    true,
                    new ContentValue("lang_type", "zh_cn"),
                    new ContentValue("pay_sn", thingsInformationBean.getList().get(0).getPdr_sn()),
                    new ContentValue("payment_code", PAY_TAG),
                    new ContentValue("pdr_amount", thingsInformationBean.getList().get(0).getPdr_amount())),
//                    new ContentValue("pdr_amount", thingsInformationBean.getList().get(0).getPdr_amount())),
                    PAY_TAG);


        }
    }

    @Override
    public void onGetPayThingInformationSuccess(ThingsInformationBean bean) {
        this.thingsInformationBean = bean;

    }

    @Override
    public void onGetPayConfigurationSuccess(Object msg, String payAway) {
        if (msg != null) {
            if (payAway.equals("alipay")) {
//                AlipayOrderIdBean alipayOrderIdBean = (AlipayOrderIdBean) msg;
//                LogUtils.d(alipayOrderIdBean.getPayinfo());
                PayAway.Alipay(msg.toString(), this, MainActivity.class);
            } else if (payAway.equals("app_wxpay")) {
                WeChatPayBackBean weChatPayBackBean = (WeChatPayBackBean) msg;
                PayAway.WchatPay(weChatPayBackBean, getContext());
            }
        }
    }
}
