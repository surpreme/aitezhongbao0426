package com.aite.mainlibrary.activity.allmain.sendtimebank;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendTimeBankContract {
    interface View extends BaseView {
        void onPostnumberSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void postnumber(HttpParams httpParams);

    }
}
