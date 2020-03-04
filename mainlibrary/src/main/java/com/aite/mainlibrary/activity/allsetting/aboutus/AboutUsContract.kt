package com.aite.mainlibrary.activity.allsetting.aboutus

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView
import com.lzy.okgo.model.HttpParams


/**
 *
 */

open class AboutUsContract {
     interface View : BaseView {
         fun onGetUpDateVersionSuccess(msg:UpDateVersionBean)



    }

     interface Presenter : BasePresenter<View> {
         fun getUpdateVersion(httpParams: HttpParams)

    }
}
