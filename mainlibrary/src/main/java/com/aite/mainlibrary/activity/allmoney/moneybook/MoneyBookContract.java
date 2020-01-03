package com.aite.mainlibrary.activity.allmoney.moneybook;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoneyBookContract {
    interface View extends BaseView {
        void onGetHostorySuccess(Object msg,boolean isHaveMore,String allpages);

    }

    interface Presenter extends BasePresenter<View> {
        void getHostory(HttpParams httpParams);

    }
}
