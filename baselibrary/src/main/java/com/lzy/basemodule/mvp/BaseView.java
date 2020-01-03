package com.lzy.basemodule.mvp;

import android.content.Context;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public interface BaseView {
    Context getContext();

    void showLoading();

    void dimissLoading();

    void showError(String msg);

    void showEmpty();

    void showNormal();

}
