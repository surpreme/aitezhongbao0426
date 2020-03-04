package com.aite.a.activity.li.activity.topuppays;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TopupPaysContract {
    interface View extends BaseView {
        void onGetPayThingInformationSuccess(ThingsInformationBean bean);

        void onGetPayConfigurationSuccess(Object msg,String payAway);

    }

    interface Presenter extends BasePresenter<View> {
        void getPayThingInformation(HttpParams httpParams);

        void getPayConfiguration(HttpParams httpParams,String payAway);

    }
}
