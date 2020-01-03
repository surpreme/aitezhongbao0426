package com.aite.mainlibrary.activity.allmain.messager;


import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessagerContract {
    interface View extends BaseView {
        void onGetSystemMsgSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void onGetSystemMsg(HttpParams httpParams);

    }
}
