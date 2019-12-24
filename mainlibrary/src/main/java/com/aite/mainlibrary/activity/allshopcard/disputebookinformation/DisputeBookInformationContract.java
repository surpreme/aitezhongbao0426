package com.aite.mainlibrary.activity.allshopcard.disputebookinformation;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DisputeBookInformationContract {
    interface View extends BaseView {
        void onGetDisputeSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getDispute(HttpParams httpParams);

    }
}
