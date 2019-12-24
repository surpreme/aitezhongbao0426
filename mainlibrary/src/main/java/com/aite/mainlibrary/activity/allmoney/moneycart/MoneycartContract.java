package com.aite.mainlibrary.activity.allmoney.moneycart;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MoneycartContract {
    interface View extends BaseView {
        void onGetInformationSuccess(Object msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void GetInformation(HttpParams httpParams);

    }
}
