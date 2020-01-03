package com.aite.mainlibrary.activity.allshopcard.orderdiscuss;

import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseDataWhitoutErrorCode;
import com.lzy.basemodule.bean.OrderDiscussBean;
import com.lzy.basemodule.rxjava.BaseObserver3;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Query;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-27
 * @desc:
 */
public interface RetrofitResultInterface {
    /**
     * 当POST请求时，@FormUrlEncoded和@Field简单的表单键值对。两个需要结合使用，否则会报错
     *
     * @Query 请求参数。无论是GET或POST的参数都可以用它来实现
     * @FormUrlEncoded post
     * @GET("index.php?act=goods&op=goods_evaluate") Call<BaseData < OrderDiscussBean>> getOrderDiscuss(@Field("goods_id") String goods_id,@Field("type") String type,@Field("curpage") int curpage);
     */

    // @Multipart get
//    @Multipart
    @GET("index.php?act=goods&op=goods_evaluate")
//    Flowable<BaseData<OrderDiscussBean>> getOrderDiscuss(
//            @Query("goods_id") String goods_id,
//            @Query("type") String type,
//            @Query("curpage") int curpage);

    Flowable<BaseData<OrderDiscussBean>> getOrderDiscuss(
            @Query("goods_id") String goods_id,
            @Query("type") String type,
            @Query("curpage") int curpage);
}
