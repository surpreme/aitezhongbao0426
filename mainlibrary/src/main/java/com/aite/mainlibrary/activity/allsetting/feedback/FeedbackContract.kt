package com.aite.mainlibrary.activity.allsetting.feedback

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView
import com.lzy.okgo.model.HttpParams
import org.json.JSONArray


/**
 *
 */

open class FeedbackContract {
    interface View : BaseView {
        fun commitInformationSuccess(msg: String)
        fun onGetComplaintSuccess(msg: JSONArray)


    }

    interface Presenter : BasePresenter<View> {
        fun commitInformation(key: String, feedback: String, type_id: String, client_type: String)
        fun getComplaintList(key: String)

    }
}
