package com.aite.mainlibrary.activity.allmoney.banker;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BankerContract {
    interface View extends BaseView {
        void  onGetbankListSuccess(Object msg);
        void  onDeletebankSuccess(Object msg);



    }

    interface  Presenter extends BasePresenter<View> {
        void  GetbankList(HttpParams httpParams);
        void  Deletebank(HttpParams httpParams);

    }
}
