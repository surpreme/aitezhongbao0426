package com.aite.mainlibrary.activity.allsetting.collect

import com.aite.mainlibrary.activity.allsetting.editSosUser.SosUserInformationBean
import com.lzy.basemodule.bean.BaseData
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.*

/**

 * @Auther: liziyang

 * @datetime: 2020-01-19

 * @desc:key: String, fav_type: String, curpage: String

 */
interface RetrofitInterface {
    @GET("index.php?act=member_favorites&op=favorites_list")
    fun onGetFavoritesServiceList(
            @Query("key") key: String,
            @Query("fav_type") fav_type: String,
            @Query("curpage") curpage: String
    ): Flowable<ResponseBody>
}