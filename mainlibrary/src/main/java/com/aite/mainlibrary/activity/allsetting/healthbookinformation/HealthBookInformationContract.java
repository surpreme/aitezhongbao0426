package com.aite.mainlibrary.activity.allsetting.healthbookinformation;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HealthBookInformationContract {
    interface View extends BaseView {
        void onGetSickInformationSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getSickInformation(HttpParams httpParams);


    }
}
