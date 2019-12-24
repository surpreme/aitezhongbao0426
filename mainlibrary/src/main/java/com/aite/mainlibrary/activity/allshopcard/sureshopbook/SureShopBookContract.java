package com.aite.mainlibrary.activity.allshopcard.sureshopbook;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SureShopBookContract {
    interface View extends BaseView {
        void onGetInformationSuccess(Object msg);

        void onGetAddressSuccess(Object msg);

        void onPayListSuccess(Object msg);

        void onPaySuccess(Object msg);

        void onAdressMonenySuccess(Object msg);

        //使用钱包支付实物订单 这里是助餐自选
        void onPayCollectSuccess(Object msg);


    }

    interface Presenter extends BasePresenter<View> {
        void getInformation(HttpParams httpParams);

        void getAddress(HttpParams httpParams);

        void getPayList(HttpParams httpParams);

        void MakePay(HttpParams httpParams);

        void getAdressMoneny(HttpParams httpParams);

        void PayCollect(HttpParams httpParams);

    }
}
