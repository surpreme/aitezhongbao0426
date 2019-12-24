package com.lzy.basemodule.baseRetrofit.net;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 创建时间 2019/12/23 10:37
 * 描述:
 */
public interface APIService {

    //添加医生信息
    @POST("index.php?act=doctor_extend&op=add")
    @FormUrlEncoded
    Flowable<String> addDoctorInfo(@Field("key") String key, @Field("work_address") String work_address, @Field("adept") String adept,
                                   @Field("introduce") String introduce, @Field("professional_types") int professional_types,
                                   @Field("departments") String departments, @Field("position ") String position);



}
