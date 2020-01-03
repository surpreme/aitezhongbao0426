package com.lzy.basemodule.mvp3;

import com.lzy.basemodule.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface BasePresenter3<V extends IView>{
    void attachView(V view);

    void detachView();
}
