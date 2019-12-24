package com.aite.mainlibrary.activity.allsetting.addbookdispute;

import com.aite.mainlibrary.activity.allsetting.bookdispute.BookDisputeContract;
import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddBookDisputeContract {
    interface View extends BaseView {
        void onGetDisputeTypeSuccess(Object msg);
        void onPostAllDisputeSuccess(Object msg);


    }

    interface Presenter extends BasePresenter<AddBookDisputeContract.View> {
        void getDisputeType(HttpParams httpParams);
        void postAllDispute(HttpParams httpParams);

    }
}
