package com.aite.mainlibrary.fragment.choiceEatbook.chirendchoiceeat;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

public class ChirendChoiceEatContract {
    interface View extends BaseView {
        void onGetBookListSuccess(Object msg);

        void onCancleOrderSuccess(Object msg);

        void onPayOrderSuccess(Object msg);

        void onPayListSuccess(Object msg);

        //使用钱包支付实物订单 这里是助餐自选
        void onFactPayCollectSuccess(Object msg);

        //第三方支付
        void onFactPayThreeElseSuccess(Object msg, String payAway);
    }

    interface Presenter extends BasePresenter<View> {
        void getBookList(HttpParams httpParams);

        void cancleOrder(HttpParams httpParams);

        void payOrder(HttpParams httpParams);

        void getPayList(HttpParams httpParams);

        void PayFactCollect(HttpParams httpParams);

        void PayFactThreeElse(HttpParams httpParams, String payAway);

    }
}

