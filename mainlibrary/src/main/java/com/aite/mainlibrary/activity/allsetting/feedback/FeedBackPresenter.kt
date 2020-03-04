package com.aite.mainlibrary.activity.allsetting.feedback

import android.annotation.SuppressLint
import android.app.Activity
import com.aite.mainlibrary.activity.allsetting.aboutus.UpDateVersionBean
import com.aite.mainlibrary.activity.allsetting.editSosUser.SosUserInformationBean
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.lzy.basemodule.BaseConstant.AppConstant
import com.lzy.basemodule.baseRetrofit.http.RetrofitClient
import com.lzy.basemodule.bean.BaseData
import com.lzy.basemodule.bean.BaseData2
import com.lzy.basemodule.bean.ErrorBean
import com.lzy.basemodule.logcat.LogUtils
import com.lzy.basemodule.net.RxScheduler
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import io.reactivex.functions.Consumer
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject


/**
 *
 */

open class FeedBackPresenter : BasePresenterImpl<FeedbackContract.View>(), FeedbackContract.Presenter {
    @SuppressLint("CheckResult")
    override fun commitInformation(key: String, feedback: String, type_id: String, client_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onCommitFeed(key, feedback, type_id, client_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : Consumer<ResponseBody> {
                    override fun accept(responseBody: ResponseBody?) {
                        val jsonObject = JSONObject(responseBody!!.string())
                        val code = jsonObject.optString("error_code")
                        if (code == "0") {
                            mView!!.commitInformationSuccess("Success")
                        } else {
                            val message = jsonObject.optString("message")
                            mView!!.showError(message)
                        }

                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun getComplaintList(key: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java).onGetFeedTypeList(key).compose(RxScheduler.Flo_io_main()).subscribe(object : Consumer<ResponseBody> {
            override fun accept(responseBody: ResponseBody?) {
                val jsonObject = JSONObject(responseBody!!.string())
                val code = jsonObject.optString("error_code")
                if (code == "0") {
                    val datasArray: JSONArray? = jsonObject.optJSONArray("datas")
                    mView!!.onGetComplaintSuccess(datasArray!!)
                } else {
                    val errorObject: JSONObject? = jsonObject.optJSONObject("data")
                    val errorBean: ErrorBean = Gson().fromJson<ErrorBean>(errorObject!!.toString(), ErrorBean::class.java)
                    if (errorBean.error != null) {
                        mView!!.showError(errorBean.error)
                    }
                }

            }
        })

    }


}
