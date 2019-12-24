package com.aite.mainlibrary.fragment.lovefamilychridren.goodchriendaroundbackground;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodChriendAroundBackgroundContract {
    interface View extends BaseView {
        void onGetlistSuccess(Object msg);

    }

    interface  Presenter extends BasePresenter<View> {
        void getlist(HttpParams httpParams);

    }
}
