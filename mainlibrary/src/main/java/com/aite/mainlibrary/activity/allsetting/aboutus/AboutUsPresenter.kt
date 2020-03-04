package com.aite.mainlibrary.activity.allsetting.aboutus

import android.app.Activity
import com.aite.mainlibrary.Mainbean.ElseServiceIconBean
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.lzy.basemodule.BaseConstant.AppConstant
import com.lzy.basemodule.bean.BaseData
import com.lzy.basemodule.logcat.LogUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import org.json.JSONObject


/**
 *
 */

open class AboutUsPresenter : BasePresenterImpl<AboutUsContract.View>(), AboutUsContract.Presenter {
    override fun getUpdateVersion(httpParams: HttpParams) {
        OkGo.get<BaseData<UpDateVersionBean>>(AppConstant.GET_UPDATE_VERSION_URL)
                .tag(mView?.getContext())
                .params(httpParams)
                .execute(object : AbsCallback<BaseData<UpDateVersionBean>>() {
                    @Throws(Throwable::class)
                    override fun convertResponse(response: okhttp3.Response): BaseData<UpDateVersionBean>? {
                        LogUtils.d(response.request())
                        val jsonObject = JSONObject(response.body()!!.string())
                        val `object` = jsonObject.optJSONObject("datas")
                        val gson = Gson()
                        val elseServiceIconBean = gson.fromJson(`object`!!.toString(), UpDateVersionBean::class.java)
                        (mView?.getContext() as Activity).runOnUiThread { mView?.onGetUpDateVersionSuccess(elseServiceIconBean) }

                        return null
                    }

                    override fun onStart(request: Request<BaseData<UpDateVersionBean>, out Request<*, *>>?) {
                        LogUtils.d("onStart")

                    }

                    override fun onSuccess(response: Response<BaseData<UpDateVersionBean>>) {
                        LogUtils.d("onSuccess")

                    }
                })
    }


}
