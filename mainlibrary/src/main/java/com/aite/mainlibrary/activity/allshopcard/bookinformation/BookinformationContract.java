package com.aite.mainlibrary.activity.allshopcard.bookinformation;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BookinformationContract {
    interface View extends BaseView {
        void onGetBookInformationSuccess(Object msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void GetBookInformation(HttpParams httpParams);

    }
}
