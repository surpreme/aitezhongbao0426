package com.aite.mainlibrary.activity.allsetting.editSosUser;

import com.aite.mainlibrary.Mainbean.AddbinduserfamilyBean;
import com.aite.mainlibrary.Mainbean.BinduserfamilyTypeBean;
import com.aite.mainlibrary.Mainbean.MoneyBookBean;
import com.aite.mainlibrary.Mainbean.StateCodeBean;
import com.aite.mainlibrary.Mainbean.SuccessCodeBean;
import com.aite.mainlibrary.activity.allstep.vipStep.StepInformationBean;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseData2;
import com.lzy.basemodule.bean.BaseDataX;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-11
 * @desc:
 */
public interface RetrofitInterface {
    /**
     * 得到紧急联系人信息
     *
     * @param key
     * @param id  紧急联系人id
     * @return
     */
    @GET("index.php?act=member_contact&op=my_detail")
    Flowable<BaseData<SosUserInformationBean>> onGetSosInformationData(
            @Query("key") String key,
            @Query("id") String id
    );

    /**
     * 得到紧急联系人关系信息
     *
     * @param key
     * @return
     */
    @GET("index.php?act=member_associate&op=relation")
    Flowable<ResponseBody> onGetSosRelationshipData(
            @Query("key") String key
    );

    /**
     * key	post	字符串	必须			会员登录key
     * id	post	整型	可选			紧急联系人id
     * type	post	整型	必须			保存类型 1添加 2修改(需上传紧急联系人id)
     * avatar	post	文件	必须	1		头像文件参数
     * realname	post	字符串	必须			真实姓名
     * mobile	post	字符串	必须			手机号
     * relation	post	整型	必须			关系id
     * is_default	post	整型	可选			是否 默认1- 否0
     *
     * @PartMap Map<String, ResponseBody> responseBodyMap
     * @Part("key") String key,
     * @Part("id") String id,
     * @Part("type") String type,
     * @Part("avatar") MultipartBody.Part avatar,
     * @Part("realname") String realname,
     * @Part("mobile") String mobile,
     * @Part("relation") String relation,
     * @Part("is_default") String is_default
     */

    @Multipart
//    @FormUrlEncoded
    @POST("index.php?act=member_contact&op=my_save")
    Flowable<BaseData<StateCodeBean>> onSaveEditSosUserInformation(
            @Part List<MultipartBody.Part> content
    );

    /**
     * APP签到页
     *
     * @param key
     * @return
     */
    @GET("index.php?act=member_sign&op=appindex")
    Flowable<BaseData<StepInformationBean>> onGetUserStepData(
            @Query("key") String key
    );

    /**
     * APP签到 点击签到
     *
     * @param key
     * @return
     */
    @GET("index.php?act=member_sign&op=sign")
    Flowable<ResponseBody> onUserStep(
            @Query("key") String key
    );

    /**
     * APP-安卓版本号 判断是否自动更新
     *
     * @param key
     * @return
     */
    @GET("index.php?act=index&op=android_version")
    Flowable<ResponseBody> onGetUpdateVersion(
            @Query("key") String key
    );

    /**
     * APP账单明细 获取会员账单明细
     *
     * @param key
     * @param curpage
     * @return
     */
    @GET("index.php?act=predeposit&op=yck_list")
    Flowable<BaseDataX<MoneyBookBean>> onBillingDetails(
            @Query("key") String key,
            @Query("curpage") int curpage
    );
}
