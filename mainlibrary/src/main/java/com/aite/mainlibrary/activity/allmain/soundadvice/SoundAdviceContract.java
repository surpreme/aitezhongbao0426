package com.aite.mainlibrary.activity.allmain.soundadvice;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SoundAdviceContract {
    interface View extends BaseView {
        void onGetUiTitleSuccess(Object msg);

        void onGetUriContentSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getUiTitleSuccess(HttpParams httpParams);

        void getUriContentSuccess(HttpParams httpParams);

    }
}
