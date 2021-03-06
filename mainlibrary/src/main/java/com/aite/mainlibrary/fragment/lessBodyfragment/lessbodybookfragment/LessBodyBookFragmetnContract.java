package com.aite.mainlibrary.fragment.lessBodyfragment.lessbodybookfragment;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LessBodyBookFragmetnContract {
    interface View extends BaseView {
        void onGetinformationSuccess(Object msg);

        void onCancelinformationSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getinformation(HttpParams httpParams);

        void Cancelinformation(HttpParams httpParams);

    }
}
