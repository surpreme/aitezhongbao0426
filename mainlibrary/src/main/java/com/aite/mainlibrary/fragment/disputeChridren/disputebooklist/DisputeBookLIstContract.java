package com.aite.mainlibrary.fragment.disputeChridren.disputebooklist;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DisputeBookLIstContract {
    interface View extends BaseView {
        void onGetListInformationSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getListInformation(HttpParams httpParams);

    }
}
