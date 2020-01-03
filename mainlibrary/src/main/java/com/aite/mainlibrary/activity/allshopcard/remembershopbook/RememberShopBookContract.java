package com.aite.mainlibrary.activity.allshopcard.remembershopbook;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RememberShopBookContract {
    interface View extends BaseView {
        void onGetFoodInformationSuccess(Object msg);

        void postAllInformationSuccess(Object msg);

        void onGetAddressSuccess(Object msg);

        void onPayListSuccess(Object msg);

        //使用钱包支付虚拟订单 这里是助餐自选
        void onPayCollectSuccess(Object msg);

        void onPayThreeElseSuccess(Object msg, String payAway);

    }

    interface Presenter extends BasePresenter<View> {
        void getFoodInformation(HttpParams httpParams);

        void postAllInformation(HttpParams httpParams);

        void getAddress(HttpParams httpParams);

        void getPayList(HttpParams httpParams);

        void PayCollect(HttpParams httpParams);

        void PayThreeElse(HttpParams httpParams, String payAway);


    }
}
