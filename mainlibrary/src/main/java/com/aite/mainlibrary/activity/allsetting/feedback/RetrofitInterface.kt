package com.aite.mainlibrary.activity.allsetting.feedback

import com.aite.mainlibrary.activity.allsetting.editSosUser.SosUserInformationBean
import com.lzy.basemodule.bean.BaseData
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.*

/**

 * @Auther: liziyang

 * @datetime: 2020-01-19

 * @desc:

 */
interface RetrofitInterface {
    @GET("index.php?act=member_feedback&op=feedback_typelist")
    fun onGetFeedTypeList(
            @Query("key") key: String
    ): Flowable<ResponseBody>

    /**
     * 会员添加意见反馈
     *
    参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
    key	post	字符串	必须			会员登录key
    049507bc7fdedc052495a82d9c63d7d7
    feedback	post	字符串	必须			反馈内容
    dsaf
    type_id	post	整型	必须			反馈类型编号
    1
    client_type	post	整型	必须	1		反馈客户端 1来自手机端2来自PC端
     */
    @POST("index.php?act=member_feedback&op=feedback_add")
    @FormUrlEncoded
    fun onCommitFeed(
            @Field("key") key: String,
            @Field("feedback") feedback: String,
            @Field("type_id") type_id: String,
            @Field("client_type") client_type: String

    ): Flowable<ResponseBody>
}