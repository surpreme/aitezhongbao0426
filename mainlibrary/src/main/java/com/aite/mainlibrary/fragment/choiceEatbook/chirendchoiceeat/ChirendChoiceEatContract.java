package com.aite.mainlibrary.fragment.choiceEatbook.chirendchoiceeat;

import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.okgo.model.HttpParams;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChirendChoiceEatContract {
    interface View extends BaseView {
        void onGetBookListSuccess(Object msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getBookList(HttpParams httpParams);

    }
}
