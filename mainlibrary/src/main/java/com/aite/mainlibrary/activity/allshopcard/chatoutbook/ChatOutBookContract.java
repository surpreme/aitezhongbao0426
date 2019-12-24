package com.aite.mainlibrary.activity.allshopcard.chatoutbook;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChatOutBookContract {
    interface View extends BaseView {
        void onPostEditBookInformationSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void postEditBookInformation(HttpParams httpParams);

    }
}
