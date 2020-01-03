package com.aite.mainlibrary.activity.allsetting.serviceorderbook;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ServiceOrderBookContract {
    interface View extends BaseView {
        void onGetOrderBookListSuccess(Object msg);

        void onCancelinformationSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void onGetOrderBookList(HttpParams httpParams);

        void Cancelinformation(HttpParams httpParams);


    }
}
