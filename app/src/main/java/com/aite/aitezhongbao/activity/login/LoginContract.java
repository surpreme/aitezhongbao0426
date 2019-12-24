package com.aite.aitezhongbao.activity.login;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void logInSuccess(Object msg);

        void logInFail(String error);

        void logInNeedMoreMsg(String number, String key);

        void weChatLoginSuccess(Object msg);
        void weChatnowloginSuccess(Object msg);


    }

    interface Presenter extends BasePresenter<View> {
        void login(HttpParams httpParams);

        void weChatLogin(HttpParams httpParams);
        void weChatnowloginLogin(HttpParams httpParams);

    }
}
