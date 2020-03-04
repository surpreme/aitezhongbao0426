package com.aite.mainlibrary.activity.allsetting.collect

import android.annotation.SuppressLint
import com.aite.a.activity.li.util.LogUtils
import com.aite.mainlibrary.Mainbean.MoneyBookBean
import com.aite.mainlibrary.activity.allmoney.moneybook2.ItemMoneyBookViewModel
import com.aite.mainlibrary.activity.allsetting.feedback.FeedbackContract
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.lzy.basemodule.baseRetrofit.http.RetrofitClient
import com.lzy.basemodule.bean.BaseDataX
import com.lzy.basemodule.bean.ErrorBean
import com.lzy.basemodule.net.RxScheduler
import io.reactivex.functions.Consumer
import me.goldze.mvvmhabit.utils.ToastUtils
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject

/**

 * @Auther: liziyang

 * @datetime: 2020-01-20

 * @desc:

 */
open class MineCollectPresenter : BasePresenterImpl<MineCollectContract.View>(), MineCollectContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getCollectServiceList(key: String, fav_type: String, curpage: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java).onGetFavoritesServiceList(key, fav_type, curpage)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : Consumer<ResponseBody> {
                    override fun accept(responseBody: ResponseBody?) {
                        val jsonObject = JSONObject(responseBody!!.string())
                        val code = jsonObject.optString("code")
                        if (code == "200") {
                            val dataObject: JSONObject? = jsonObject.optJSONObject("data")
                            val collectListBean: CollectListBean = Gson().fromJson<CollectListBean>(dataObject?.toString(), CollectListBean::class.java)
                            mView?.onGetCollectServiceSuccess(collectListBean)
                        } else {
                            val message = jsonObject.optString("message")
                            mView?.showError(message)
                        }

                    }
                })

    }

}
