package com.aite.mainlibrary.activity.allmoney.moneycart;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Constant.MainUIConstant;
import com.aite.mainlibrary.Mainbean.MoneyCollectBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allmoney.FactMoneyActivity;
import com.aite.mainlibrary.activity.allmoney.banker.BankerActivity;
import com.aite.mainlibrary.adapter.MoneyCardRecyAdapter;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoneycartActivity extends BaseActivity<MoneycartContract.View, MoneycartPresenter> implements MoneycartContract.View {
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.get_factmoney_ll)
    LinearLayout getFactmoneyLl;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.allmoney_tv)
    TextView allmoneyTv;
    @BindView(R2.id.today_money_tv)
    TextView todayMoneyTv;
    private MoneyCardRecyAdapter moneyCardRecyAdapter;
    private MoneyCollectBean moneyCollectBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.mine_money_card;
    }

    @Override
    protected void initView() {
        ivBack.setOnClickListener(this);
        getFactmoneyLl.setOnClickListener(this);
        tvTitle.setText("我的钱包");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        moneyCardRecyAdapter = new MoneyCardRecyAdapter(context,
//                MainUIConstant.MoneycartActivityConstant.settingImg,
//                MainUIConstant.MoneycartActivityConstant.settingTv);
        moneyCardRecyAdapter = new MoneyCardRecyAdapter(context,
                MainUIConstant.MoneycartActivityConstant.settingImg,
                MainUIConstant.MoneycartActivityConstant.settingTv, moneyCollectBean);
        recyclerView.setAdapter(moneyCardRecyAdapter);
        moneyCardRecyAdapter.setOnRecyClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                switch (postion) {
                    case 0:
                        break;
                    case 1:
                    case 2:
                        startActivity(FactMoneyActivity.class);
                        break;
                    case 3:
                        startActivity(BankerActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) onBackPressed();
        if (v.getId() == R.id.get_factmoney_ll)
            startActivity(FactMoneyActivity.class);
    }

    @Override
    protected void initDatas() {
        mPresenter.GetInformation(initKeyParams());

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    /**
     * 返回字段	类型	说明
     * datas[]->predepoit	整型	返回会员总资产
     * datas[]->new_amount	整型	返回会员今日收入资产
     * datas[]->cash_info[]	数组	最新提现信息
     * datas[]->cash_info[]->bank_name	字符串	提现银行名称
     * datas[]->cash_info[]->pdc_amount	字符串	提现金额
     * datas[]->cash_info[]->pdc_payment_time	字符串	提现到账时间
     * datas[]->recharge_info[]	数组	最新充值信息
     * datas[]->recharge_info[]->pdr_payment_name	字符串	充值支付方式
     * datas[]->recharge_info[]->pdr_amount	字符串	充值金额
     * datas[]->recharge_info[]->pdr_payment_time	字符串	充值支付时间
     * datas[]->log_info[]	数组	最新账单明细信息
     * datas[]->log_info[]->lg_desc	字符串	描述
     * datas[]->log_info[]->lg_av_amount	字符串	变更金额
     * datas[]->log_info[]->lg_add_time	字符串	添加时间
     * error	字符串	错误信息 error_code=0 正确 其他编码错误
     *
     * @param msg
     */
    @Override
    public void onGetInformationSuccess(Object msg) {
        moneyCollectBean = (MoneyCollectBean) msg;
        if (moneyCollectBean != null) {
            allmoneyTv.setText(haveTwoDouble(moneyCollectBean.getPredepoit()));
            todayMoneyTv.setText(String.format("今日资产%s", haveTwoDouble(moneyCollectBean.getNew_amount())));
            if (moneyCardRecyAdapter != null)
                moneyCardRecyAdapter.setData(moneyCollectBean);

        }

    }


}
