package com.aite.mainlibrary.activity.allmain.buydaytogether;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BuyDayTogetherContract {
    interface View extends BaseView {
        void onBuyThingSuccesss(Object msg);

        void onGetInformationSuceess(Object msg);

        void onPayListSuccess(Object msg);

        void onPayCollectSuccess(Object msg);

        void onPayThreeElseSuccess(Object msg,String payAway);
    }

    interface Presenter extends BasePresenter<View> {
        void buyService(HttpParams httpParams);

        void getInformation(HttpParams httpParams);

        void getPayList(HttpParams httpParams);

        void PayCollect(HttpParams httpParams);

        void PayThreeElse(HttpParams httpParams, String payAway);

    }
}
