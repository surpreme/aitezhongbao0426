package com.aite.aitezhongbao.activity.usertype;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UserTypeContract {
    interface View extends BaseView {
        void setChoiceHeghtSuccess(Object msg);

    }

    interface  Presenter extends BasePresenter<View> {
        void ChoiceHeght(HttpParams httpParams);

    }
}
