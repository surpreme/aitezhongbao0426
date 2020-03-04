package com.example.net;

import com.example.bean.AdvBean;
import com.example.bean.AlipayBean;
import com.example.bean.AllAreaBean;
import com.example.bean.BaseBean;
import com.example.bean.AddDataBean;
import com.example.bean.ConsultListBean;
import com.example.bean.DoctorInfoBean;
import com.example.bean.DoctorInfoPersonBean;
import com.example.bean.DoctorListBean;
import com.example.bean.IMInfoBean;
import com.example.bean.OrderInfoBean;
import com.example.bean.OrderListBean;
import com.example.bean.OrganizeDetailBean;
import com.example.bean.OrganizeListBean;
import com.example.bean.PayResult;
import com.example.bean.PayWayBean;
import com.example.bean.VolunteerInfoBean;
import com.example.bean.VolunteerListBean;
import com.example.bean.WeChatpayBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 创建时间 2019/11/22 14:07
 * 描述:
 */
public interface APIService {

    //添加医生信息
    @POST("mobile/index.php?act=doctor_extend&op=add")
    @FormUrlEncoded
    Observable<BaseBean> addDoctorInfo(@Field("key") String key, @Field("doctor_id") String doctor_id, @Field("work_address") String work_address, @Field("adept") String adept,
                                       @Field("introduce") String introduce, @Field("professional_types") int professional_types,
                                       @Field("departments") String departments, @Field("position") String position,
                                       @Field("longitude") String longitude, @Field("latitude") String latitude);

    //医生扩展信息修改,,
    @POST("mobile/index.php?act=doctor_extend&op=edit")
    @FormUrlEncoded
    Observable<BaseBean> editDoctorInfo(@Field("key") String key, @Field("doctor_id") String doctor_id, @Field("work_address") String work_address, @Field("adept") String adept,
                                        @Field("introduce") String introduce, @Field("professional_types") int professional_types,
                                        @Field("departments") String departments, @Field("position") String position,
                                        @Field("longitude") String longitude, @Field("latitude") String latitude);

    //查看医生信息 医生扩展信息详情--根据会员id查询(医生查看自己),,
    @GET("mobile/index.php?act=doctor_extend&op=details")
    Observable<BaseBean<DoctorInfoPersonBean>> getDoctorInfoPersoner(@Query("key") String key, @Query("member_id") String member_id);

    //获取所有省市区
    @GET("mobile/index.php?act=index&op=getAllAreaList")
    Observable<AllAreaBean> getAllAreaList();


    //医生列表
    @GET("mobile/index.php?act=doctor_extend&op=list")
    Observable<DoctorListBean> getDoctorList(@Query("key") String key, @Query("keyword") String keyword, @Query("position") String position,
                                             @Query("departments") String departments, @Query("consult_count") int consult_count, @Query("evaluate_score") int evaluate_score,
                                             @Query("provinceid") String provinceid, @Query("cityid") String cityid, @Query("curpage") int curpage, @Query("pagesize") int pagesize);


    //医生科室列表
    @POST("mobile/index.php?act=doctor_extend&op=doctor_departments")
    @FormUrlEncoded
    Observable<BaseBean<List<String>>> getDepartmentList(@Field("key") String key);

    //收藏医生
    @POST("mobile/index.php?act=doctor_extend&op=doctor_collection")
    @FormUrlEncoded
    Flowable<ResponseBody> collectDoctor(@Field("key") String key, @Field("fav_id") String fav_id);

    //取消收藏医生
    @GET("mobile/index.php?act=doctor_extend&op=doctor_collection_del")
    Flowable<ResponseBody> cancelCollectDoctor(@Query("key") String key, @Query("fav_id") String fav_id);


    //医生职位列表
    @POST("mobile/index.php?act=doctor_extend&op=doctor_position")
    @FormUrlEncoded
    Observable<BaseBean<List<String>>> getDoctorPosition(@Field("key") String key);


    //医生扩展信息详情
    @GET("mobile/index.php?act=doctor_extend&op=detail")
    Observable<BaseBean<DoctorInfoBean>> getDoctorInfo(@Query("key") String key, @Query("doctor_id") String doctor_id);


    //医生扩展信息详情--根据会员id查询
    @GET("mobile/index.php?act=doctor_extend&op=details")
    Observable<BaseBean<DoctorInfoBean>> getDoctorInfo1(@Query("key") String key, @Query("member_id") String member_id);


    //医生咨询价格添加
    @POST("mobile/index.php?act=doctor_extend&op=consult_price_add")
    @FormUrlEncoded
    Observable<BaseBean> setConsultPrice(@Field("key") String key, @Field("doctor_id") String doctor_id, @Field("type") int type,
                                         @Field("time") String time, @Field("price") String price);

    //医生咨询价格列表
    @GET("mobile/index.php?act=doctor_extend&op=consult_price_list")
    Observable<ConsultListBean> getConsultList(@Query("key") String key, @Query("type") int type, @Query("curpage") int curpage,
                                               @Query("pagesize") int pagesize);


    //医生咨询价格修改
    @POST("mobile/index.php?act=doctor_extend&op=consult_price_edit")
    @FormUrlEncoded
    Observable<BaseBean> modifyConsultPrice(@Field("key") String key, @Field("c_price_id") String c_price_id, @Field("type") int type,
                                            @Field("time") String time, @Field("price") String price);


    //获取腾讯im配置信息
    @GET("plug/index.php?op=getIMUserSig")
    Observable<IMInfoBean> getIMinfo(@Query("key") String key, @Query("lang") String lang, @Query("member_id") String member_id);

    //获取医生聊天配置信息
    @GET("mobile/index.php?act=doctor_extend&op=detail")
    Observable<IMInfoBean> getDoctorIMinfo(@Query("key") String key, @Query("doctor_id") String doctor_id);


    //用户查看医生咨询列表
    @GET("mobile/index.php?act=doctor_extend&op=consult_price_list")
    Observable<ConsultListBean> getConsultLists(@Query("key") String key, @Query("type") int type, @Query("doctor_id") String doctor_id,
                                                @Query("curpage") int curpage, @Query("pagesize") int pagesize);


    //添加咨询订单
    @POST("mobile/index.php?act=doctor_extend&op=consult_order_add")
    @FormUrlEncoded
    Observable<BaseBean<String>> addOrder(@Field("key") String key, @Field("doctor_id") String doctor_id, @Field("order_type") int order_type,
                                          @Field("order_price") String order_price, @Field("consult_time") String consult_time);


    //获取支付方式 注：如果使用钱包支付 则单独调用对应钱包支付提交接口
    @GET("mobile/index.php?act=member_payment&op=APP_getPaymentList")
    Observable<BaseBean<List<PayWayBean>>> getPaymentList(@Query("key") String key);


    //钱使用钱包支付虚拟订单
    @GET("mobile/index.php?act=member_payment&op=predeposit_consult_pay")
    Observable<BaseBean<PayResult>> getWalletPay(@Query("key") String key, @Query("order_id") String order_id);


    //支付宝参数
    @GET("mobile/index.php?act=member_payment&op=app_consult_pay")
    Observable<AlipayBean> getAlipayData(@Query("key") String key, @Query("order_id") String order_id, @Query("payment_code") String payment_code);


    //微信参数
    @GET("mobile/index.php?act=member_payment&op=app_consult_pay")
    Observable<BaseBean<WeChatpayBean>> getWeChatPayData(@Query("key") String key, @Query("order_id") String order_id, @Query("payment_code") String payment_code);


    //咨询订单列表
    @GET("mobile/index.php?act=doctor_extend&op=consult_order_list")
    Observable<OrderListBean> getOrderListData(@Query("key") String key, @Query("curpage") int curpage, @Query("pagesize") int pagesize, @Query("order_state") int order_state);


    //咨询订单详情
    @GET("mobile/index.php?act=doctor_extend&op=consult_order_detail")
    Observable<BaseBean<OrderInfoBean>> getOrderInfoData(@Query("key") String key, @Query("c_order_id") String c_order_id);


    //义工首页
    @GET("mobile/index.php?act=index&op=APPindex")
    Observable<BaseBean<AdvBean>> getAppHomePageData();


    //义工项目列表信息
    @GET("mobile/index.php?act=volunteer&op=item_list")
    Observable<VolunteerListBean> getVolunteerListData(@Query("key") String key, @Query("keyword") String keyword, @Query("curpage") int curpage,
                                                       @Query("pagesize") int pagesize);


    //义工项目详情信息
    @GET("mobile/index.php?act=volunteer&op=item_detail")
    Observable<BaseBean<VolunteerInfoBean>> getVolunteerInfoData(@Query("key") String key, @Query("item_id") int item_id);


    //证件类型
    @GET("mobile/index.php?act=volunteer&op=id_type")
    Observable<BaseBean<AddDataBean>> getCertificateData(@Query("key") String key);

    //政治面貌列表
    @GET("mobile/index.php?act=volunteer&op=politics_status")
    Observable<BaseBean<AddDataBean>> getPoliticsStatusListData(@Query("key") String key);


    //学历列表
    @GET("mobile/index.php?act=volunteer&op=education")
    Observable<BaseBean<AddDataBean>> getEducationListsData(@Query("key") String key);


    //年龄列表
    @GET("mobile/index.php?act=volunteer&op=ageList")
    Observable<BaseBean<AddDataBean>> getAgeListData(@Query("key") String key);


    //义工组织列表信息
    @GET("mobile/index.php?act=volunteer&op=organize_list")
    Observable<OrganizeListBean> getOrganizeListData(@Query("key") String key, @Query("keyword") String keyword, @Query("organize_id") String organize_id,
                                                     @Query("curpage") int curpage, @Query("pagesize") int pagesize);


    //添加义工申请
    @POST("mobile/index.php?act=volunteer&op=volunteer_add")
    @Multipart
    Observable<BaseBean> addVolunteer(@Part List<MultipartBody.Part> content);


    //义工项目列表信息
    @GET("mobile/index.php?act=volunteer&op=item_list")
    Observable<VolunteerListBean> getVolunteerItemList(@Query("key") String key, @Query("keyword") String keyword, @Query("apply_time") String apply_time,
                                                       @Query("age_limit") int age_limit, @Query("provinceid") String provinceid, @Query("cityid") String cityid,
                                                       @Query("areaid") String areaid, @Query("organize_id") String organize_id, @Query("curpage") int curpage,
                                                       @Query("pagesize") int pagesize);

    //义工组织详情信息
    @GET("mobile/index.php?act=volunteer&op=organize_detail")
    Observable<BaseBean<OrganizeDetailBean>> getOrganizeDetail(@Query("key") String key, @Query("organize_id") String organize_id);


    //我要报名
    @POST("mobile/index.php?act=volunteer&op=apply_add")
    @FormUrlEncoded
    Observable<BaseBean> applyAdd(@Field("key") String key, @Field("organize_id") String organize_id, @Field("item_id") String item_id);

}
