package com.aite.mainlibrary.activity.allmain.sendtimebank;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.base.BaseApp;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendTimeBankActivity extends BaseActivity<SendTimeBankContract.View, SendTimeBankPresenter> implements SendTimeBankContract.View {

    @BindView(R2.id.user_number_edit)
    EditText userNumberEdit;
    @BindView(R2.id.time_long_edit)
    EditText timeLongEdit;
    @BindView(R2.id.sure_btn)
    Button sureBtn;

    @Override
    protected int getLayoutResId() {
        return R.layout.send_user_time;
    }

    @Override
    protected void initView() {
        initToolbar("积分转增");

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

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("member_name", getEditString(userNumberEdit));
        httpParams.put("credit", getEditString(timeLongEdit));
        return httpParams;
    }

    @Override
    public void onPostnumberSuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean == null) return;
        if (twoSuccessCodeBean.getResult().equals("1")) {
            if (twoSuccessCodeBean.getMsg() != null)
                showToast(twoSuccessCodeBean.getMsg());
        }

    }

    @OnClick(R2.id.sure_btn)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sure_btn) {
            mPresenter.postnumber(initParams());
        }

    }

}
