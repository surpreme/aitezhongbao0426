package com.aite.mainlibrary.fragment.daybookchridren.unpaybooklist;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class UnPaybooklistContract {
    interface View extends BaseView {
        void onGetinformationSuccess(Object msg);

        void onCancelinformationSuccess(Object msg);

        void onPayListSuccess(Object msg);

        //使用钱包支付虚拟订单 这里是助餐自选
        void onPayCollectSuccess(Object msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getinformation(HttpParams httpParams);

        void Cancelinformation(HttpParams httpParams);

        void getPayList(HttpParams httpParams);

        void PayCollect(HttpParams httpParams);

    }
}
