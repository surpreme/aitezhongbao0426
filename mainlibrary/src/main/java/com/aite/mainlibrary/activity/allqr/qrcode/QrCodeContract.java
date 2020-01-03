package com.aite.mainlibrary.activity.allqr.qrcode;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class QrCodeContract {
    interface View extends BaseView {
        void onSureSuccess(Object msg);
        void onSureUnfactBookSuccess(Object msg);
        void onBindingDeviceSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void sureUnfactBook(HttpParams httpParams);
        void sureBook(HttpParams httpParams);
        void BindingDevice(HttpParams httpParams);

    }
}
