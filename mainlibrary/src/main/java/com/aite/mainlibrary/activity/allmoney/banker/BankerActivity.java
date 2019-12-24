package com.aite.mainlibrary.activity.allmoney.banker;


import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.MineBankListBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allmoney.addbankcaractvity.AddBankcarActvity;
import com.aite.mainlibrary.adapter.BankCardRecyAdapter;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BankerActivity extends BaseActivity<BankerContract.View, BankerPresenter> implements BankerContract.View {
    @BindView(R2.id.bank_recy)
    RecyclerView bankRecy;
    @BindView(R2.id.add_bankcard_ll)
    LinearLayout addBankcardLl;
    private BankCardRecyAdapter bankCardRecyAdapter;
    private List<MineBankListBean.BankListBean> bankListBeans = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.bank_carlayout;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back)
            onBackPressed();
        if (v.getId() == R.id.add_bankcard_ll)
            startActivity(AddBankcarActvity.class);
    }

    @Override
    protected void initView() {
        initToolbar("我的银行卡");
        addBankcardLl.setOnClickListener(this);
        bankRecy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        bankRecy.setItemAnimator(new DefaultItemAnimator());
        bankRecy.setAdapter(bankCardRecyAdapter = new BankCardRecyAdapter(this, bankListBeans));
        bankCardRecyAdapter.setOnItemRecyClickInterface(new OnClickLstenerInterface.OnItemRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                PopwindowUtils.
                        getmInstance().
                        showdiadlogPopupWindow
                                (context,
                                        "您确定要删除卡号为" + bankListBeans.get(postion).getBank_no() + "的银行卡吗？",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                mPresenter.Deletebank(initListHttpParams(
                                                        true,
                                                        new ContentValue("bank_id",
                                                                bankListBeans.get(postion).getId())));

                                            }
                                        });
            }
        });
    }

    @Override
    protected void initDatas() {
        mPresenter.GetbankList(initListHttpParams(true));

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onGetbankListSuccess(Object msg) {
        MineBankListBean mineBankListBean = (MineBankListBean) msg;
        bankListBeans.addAll(mineBankListBean.getBank_list());
        bankCardRecyAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDeletebankSuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean.getResult() == null) return;
        if (twoSuccessCodeBean.getResult().equals("1")) {
            if (twoSuccessCodeBean.getMsg() == null) return;
            showToast(twoSuccessCodeBean.getMsg());
            onBackPressed();
        }

    }
}
