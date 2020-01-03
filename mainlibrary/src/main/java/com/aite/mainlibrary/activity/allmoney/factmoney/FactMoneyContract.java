package com.aite.mainlibrary.activity.allmoney.factmoney;

import com.aite.mainlibrary.Mainbean.PayPostAppAwayUiBean;
import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FactMoneyContract {
    interface View extends BaseView {
        void onGetFactInFormationSuccess(Object msg);

        void onGetPostInFormationSuccess(Object msg);

        void onSureGetFactMoneySuccess(Object msg);

        void onSurePostFactMoneySuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getFactInFormation(HttpParams httpParams);

        void sureGetFactMoney(HttpParams httpParams);

        void surePostFactMoney(HttpParams httpParams, String payAway);

        void getPostForInmationMoney(HttpParams httpParams);

    }
}
