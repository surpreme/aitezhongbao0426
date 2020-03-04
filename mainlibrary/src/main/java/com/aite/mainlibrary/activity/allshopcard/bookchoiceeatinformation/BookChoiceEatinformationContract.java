package com.aite.mainlibrary.activity.allshopcard.bookchoiceeatinformation;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BookChoiceEatinformationContract {
    interface View extends BaseView {
        void onGetInformationSuccess(Object msg);
        void onCancleOrderSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getInformations(HttpParams httpParams);
        void cancleOrder(HttpParams httpParams);

    }
}
