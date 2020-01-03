package com.aite.mainlibrary.activity.allmoney.factmoney;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.MainActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.PayAwayUiBean;
import com.aite.mainlibrary.Mainbean.PayPostAppAwayUiBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allmoney.addbankcaractvity.AddBankcarActvity;
import com.aite.mainlibrary.adapter.PayAwayRadioGroupRecyAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FactMoneyActivity extends BaseActivity<FactMoneyContract.View, FactMoneyPresenter> implements FactMoneyContract.View {
    @BindView(R2.id.fact_price_get_edit)
    TextInputEditText fact_price_get_edit;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.bank_check)
    CheckBox bankCheck;
    @BindView(R2.id.all_money_tv)
    TextView allMoneyTv;
    @BindView(R2.id.wechat_check)
    CheckBox wechatCheck;
    @BindView(R2.id.apay_check)
    CheckBox apayCheck;
    @BindView(R2.id.post_money_ll)
    LinearLayout postMoneyLl;
    @BindView(R2.id.wechat_ll)
    LinearLayout wechatLl;
    @BindView(R2.id.apay_ll)
    LinearLayout apayLl;
    @BindView(R2.id.bank_ll)
    LinearLayout bankLl;
    @BindView(R2.id.getfact_away_tv)
    TextView getfactAwayTv;
    @BindView(R2.id.add_bankcard_tv)
    TextView addBankcardTv;
    private PayAwayRadioGroupRecyAdapter payAwayRadioGroupRecyAdapter;
    private List<PayAwayUiBean.BankListBean> bankListBeans = new ArrayList<>();
    private String BANK_ID = "";
    private String AWAY = "fact";
    private String PRICE = "0";

    @Override
    protected int getLayoutResId() {
        return R.layout.getfact_moneylayout;
    }

    @Override
    protected void initView() {
        initToolbar(getIntent().getStringExtra("titlename"));
        if (getIntent().getStringExtra("away") != null) {
            AWAY = getIntent().getStringExtra("away");
        }
        if (AWAY.equals("fact")) {
            initBottomBtn("确认提现", 0.7f, v -> {
                if (isStringEmpty(BANK_ID) || isStringEmpty(getEditString(fact_price_get_edit))) {
                    showToast("请检查信息");
                    return;
                }
                mPresenter.sureGetFactMoney(initListHttpParams(true,
                        new ContentValue("pdc_amount", getEditString(fact_price_get_edit)),
                        new ContentValue("bank_id", BANK_ID)));

            });
        } else if (AWAY.equals("post")) {
            getfactAwayTv.setText("充值方式");
            initBottomBtn("确认充值", 0.7f, v -> {
                if (isStringEmpty(getEditString(fact_price_get_edit))) {
                    showToast("请检查信息");
                    return;
                }
                if (wechatCheck.isChecked())
                    mPresenter.surePostFactMoney(initHttpParam(), "app_wxpay");
                if (apayCheck.isChecked())
                    mPresenter.surePostFactMoney(initHttpParam(), "alipay");

            });
        }
        recyclerView.setVisibility(View.GONE);
        if (AWAY.equals("fact")) {
            postMoneyLl.setVisibility(View.GONE);

        } else {
            apayLl.setVisibility(View.GONE);
            wechatLl.setVisibility(View.GONE);
            bankLl.setVisibility(View.GONE);
            allMoneyTv.setVisibility(View.GONE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(payAwayRadioGroupRecyAdapter = new PayAwayRadioGroupRecyAdapter(context, bankListBeans));
        payAwayRadioGroupRecyAdapter.setClickInterface(position -> {
            BANK_ID = String.valueOf(position);


        });
        wechatCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                clearChoiceWay();
                wechatCheck.setChecked(true);
            }

        });
        apayCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                clearChoiceWay();
                apayCheck.setChecked(true);
            }

        });
        bankCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            recyclerView.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            if (isChecked) {
                clearChoiceWay();
                bankCheck.setChecked(true);
            }

        });

    }

    private void clearChoiceWay() {
        wechatCheck.setChecked(false);
        apayCheck.setChecked(false);
        bankCheck.setChecked(false);
    }

    @OnClick({R2.id.add_bankcard_tv, R2.id.all_money_tv})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_bankcard_tv) {
            startActivity(AddBankcarActvity.class);
        } else if (v.getId() == R.id.all_money_tv) {
            fact_price_get_edit.setText(PRICE);
        }
    }

    @Override
    protected void initDatas() {
        if (AWAY.equals("fact"))
            mPresenter.getFactInFormation(initKeyParams());
        else mPresenter.getPostForInmationMoney(initKeyParams());
    }

    @Override
    protected void initResume() {

    }

    private HttpParams initHttpParam() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("pdr_amount", getEditString(fact_price_get_edit));
        if (apayCheck.isChecked()) {
            httpParams.put("payment_code", "alipay");
        } else if (wechatCheck.isChecked()) {
            httpParams.put("payment_code", "app_wxpay");
        }
        return httpParams;
    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onGetFactInFormationSuccess(Object msg) {
        PayAwayUiBean payAwayUiBean = (PayAwayUiBean) msg;
        if (payAwayUiBean == null || payAwayUiBean.getBank_list() == null) return;
        if (!payAwayUiBean.getBank_list().isEmpty()) {
            bankListBeans.addAll(payAwayUiBean.getBank_list());
            if (bankListBeans.get(0) != null)
                bankListBeans.get(0).setChecked(true);
            BANK_ID = bankListBeans.get(0).getId();
            payAwayRadioGroupRecyAdapter.notifyDataSetChanged();
        }
//        allMoneyTv.setText(context.getResources().getString(R.string.littlemoney, "￥" + payAwayUiBean.getPredepoit()));
        allMoneyTv.setText(String.format(getResources().getString(R.string.littlemoney), "￥" + payAwayUiBean.getPredepoit()));
        PRICE = payAwayUiBean.getPredepoit();

    }

    @Override
    public void onGetPostInFormationSuccess(Object msg) {
        PayPostAppAwayUiBean payPostAppAwayUiBean = (PayPostAppAwayUiBean) msg;
        for (int i = 0; i < payPostAppAwayUiBean.getDatas().size(); i++) {
            if (payPostAppAwayUiBean.getDatas().get(i).getPayment_code().equals("alipay")) {
                apayLl.setVisibility(View.VISIBLE);
            } else if (payPostAppAwayUiBean.getDatas().get(i).getPayment_code().equals("app_wxpay")) {
                wechatLl.setVisibility(View.VISIBLE);

            }
        }

    }

    @Override
    public void onSureGetFactMoneySuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean != null && twoSuccessCodeBean.getResult().equals("1")) {
            showToast(twoSuccessCodeBean.getMsg());
            onBackPressed();
        }

    }

    @Override
    public void onSurePostFactMoneySuccess(Object msg) {
        if (msg != null) {
            if (apayCheck.isChecked()) {
                AlipayOrderIdBean alipayOrderIdBean = (AlipayOrderIdBean) msg;
                LogUtils.d(alipayOrderIdBean.getPayinfo());
                PayAway.Alipay(alipayOrderIdBean.getPayinfo(), this, MainActivity.class);
            } else if (wechatCheck.isChecked()) {
                WeChatPayBackBean weChatPayBackBean = (WeChatPayBackBean) msg;
                PayAway.WchatPay(weChatPayBackBean, this);
            }


        }

    }


}
