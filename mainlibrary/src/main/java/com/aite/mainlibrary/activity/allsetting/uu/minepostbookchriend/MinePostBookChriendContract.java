package com.aite.mainlibrary.activity.allsetting.uu.minepostbookchriend;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MinePostBookChriendContract {
    interface View extends BaseView {
        void onGetListInformationSuccess(Object msg);

        void onCancelBookSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getListInformation(String url, HttpParams httpParams);

        void cancelBook(String url, HttpParams httpParams);

    }
}
