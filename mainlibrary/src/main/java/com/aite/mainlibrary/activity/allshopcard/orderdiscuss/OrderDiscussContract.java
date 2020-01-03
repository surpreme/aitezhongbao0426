package com.aite.mainlibrary.activity.allshopcard.orderdiscuss;

import com.lzy.basemodule.bean.BaseDataWhitoutErrorCode;
import com.lzy.basemodule.bean.OrderDiscussBean;
import com.lzy.basemodule.mvp.BasePresenter;
import com.lzy.basemodule.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderDiscussContract {
    interface View extends BaseView {
        void onGetDiscussSuccess(OrderDiscussBean msg, boolean isHaveMore);

    }

    interface Presenter extends BasePresenter<View> {
        void getDiscuss(String goods_id, String type, int curpage);


    }
}
