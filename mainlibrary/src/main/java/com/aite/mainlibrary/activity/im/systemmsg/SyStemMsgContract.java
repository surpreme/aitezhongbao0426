package com.aite.mainlibrary.activity.im.systemmsg;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SyStemMsgContract {
    interface View extends BaseView {
        void onGetSystemListSuccess(Object msg,boolean isHaveMore,String allPages);

    }

    interface Presenter extends BasePresenter<View> {
        void getSystemList(HttpParams httpParams);

    }
}
