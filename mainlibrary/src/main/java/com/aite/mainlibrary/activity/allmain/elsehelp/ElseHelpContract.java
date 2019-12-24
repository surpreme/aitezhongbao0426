package com.aite.mainlibrary.activity.allmain.elsehelp;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ElseHelpContract {
    interface View extends BaseView {
        void onGetIconSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void GetIcon(HttpParams httpParams);

    }
}
