package com.aite.mainlibrary.activity.allsetting.collect

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: liziyang

 * @datetime: 2020-01-20

 * @desc:

 */
class MineCollectContract {
    interface View : BaseView {
        fun onGetCollectServiceSuccess(mCollectListBean:CollectListBean)

    }

    /**
     *   params.addBodyParameter("key", key);
    params.addBodyParameter("curpage", "1");
    params.addBodyParameter("fav_type", "goods");
     */
    interface Presenter : BasePresenter<View> {
        fun getCollectServiceList(key: String, fav_type: String, curpage: String)

    }
}