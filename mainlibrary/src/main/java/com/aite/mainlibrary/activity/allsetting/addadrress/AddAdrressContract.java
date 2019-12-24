package com.aite.mainlibrary.activity.allsetting.addadrress;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddAdrressContract {
    interface View extends BaseView {
        void onPostMsgSuccess(Object msg);

        void onGetAreaChoiceSuccess(Object msg);

        void onChangeAdrressSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void ChangeAdrress(HttpParams httpParams);

        void postMsg(HttpParams httpParams);

        void getAreachoice();

    }
}
