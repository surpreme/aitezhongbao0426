package com.aite.mainlibrary.activity.allmoney.moneybook2;

import android.content.Context;
import android.os.Bundle;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.databinding.ActivityMoneyBookListBinding;
import com.lzy.basemodule.view.StatusBarUtils;

import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-17
 * @desc:
 */
public class MoneyBookActivitys extends BaseActivity<ActivityMoneyBookListBinding, ActivityMoneyBookViewModel> {
    private Context context;

    @Override
    public void initParam() {
        context = this;
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_money_book_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        StatusBarUtils.setColor(context, getResources().getColor(com.lzy.basemodule.R.color.white));


    }
}
