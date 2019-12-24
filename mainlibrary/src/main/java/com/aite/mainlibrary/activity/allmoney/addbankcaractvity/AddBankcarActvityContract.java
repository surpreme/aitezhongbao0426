package com.aite.mainlibrary.activity.allmoney.addbankcaractvity;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddBankcarActvityContract {
    interface View extends BaseView {
        void onPostAllBankSuccess(Object msg);
        
    }

    interface  Presenter extends BasePresenter<View> {
        void PostAllBank(HttpParams httpParams);

    }
}
