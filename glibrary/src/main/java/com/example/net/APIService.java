package com.example.net;

import com.example.bean.AllAreaBean;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.bean.DoctorListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 创建时间 2019/11/22 14:07
 * 描述:
 */
public interface APIService {

    //添加医生信息
    @POST("index.php?act=doctor_extend&op=add")
    @FormUrlEncoded
    Observable<BaseBean> addDoctorInfo(@Field("key") String key, @Field("work_address") String work_address, @Field("adept") String adept,
                                       @Field("introduce") String introduce, @Field("professional_types") int professional_types,
                                       @Field("departments") String departments, @Field("position") String position);

    //获取所有省市区
    @GET("index.php?act=index&op=getAllAreaList")
    Observable<AllAreaBean> getAllAreaList();


    //医生列表
    @GET("index.php?act=doctor_extend&op=list")
    Observable<DoctorListBean> getDoctorList(@Query("key") String key, @Query("keyword") String keyword, @Query("position") String position,
                                             @Query("departments") String departments, @Query("consult_count") int consult_count, @Query("evaluate_score") int evaluate_score,
                                             @Query("provinceid") String provinceid, @Query("cityid") String cityid, @Query("curpage") int curpage, @Query("pagesize") int pagesize);


    //医生科室列表
    @POST("index.php?act=doctor_extend&op=doctor_departments")
    @FormUrlEncoded
    Observable<BaseBean<List<String>>> getDepartmentList(@Field("key") String key);


    //医生职位列表
    @POST("index.php?act=doctor_extend&op=doctor_position")
    @FormUrlEncoded
    Observable<BaseBean<List<String>>> getDoctorPosition(@Field("key") String key);


    //医生扩展信息详情
    @GET("index.php?act=doctor_extend&op=detail")
    Observable<BaseBean<DoctorInfoBean>> getDoctorInfo(@Query("key") String key, @Query("doctor_id") String doctor_id);
}
