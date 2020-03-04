package com.example.mvp.doctorInfo;

import com.example.base.BaseView;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.bean.IMInfoBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Query;


/**
 * 创建时间 2020/1/2 15:21
 * 描述:
 */
public interface DoctorInfoContract {

    interface Model {
        //医生信息(用户)
        Observable<BaseBean<DoctorInfoBean>> getDoctorInfo(String key, String doctor_id);

        //医生信息(自己)
        Observable<BaseBean<DoctorInfoBean>> getDoctorInfo1(String key, String member_id);

        //im
        Observable<IMInfoBean> getIMinfo(String key, String lang, String member_id);

        //收藏医生
        Flowable<ResponseBody> collectDoctor(String key, String fav_id);

        Flowable<ResponseBody> cancelCollectDoctor(String key, String fav_id);

    }

    interface View extends BaseView {
        void getDoctorInfo(BaseBean<DoctorInfoBean> bean);

        void getIMinfo(IMInfoBean bean);

        void onCollectDoctorSuccess(String bean);

        void onCancelCollectDoctorSuccess(String bean);
    }

    interface Presenter {
        void getDoctorInfo(String key, String doctor_id);

        void getDoctorInfo1(String key, String member_id);

        void getIMinfo(String key, String lang, String member_id);

        void collectDoctor(String key, String fav_id);

        void cancelCollectDoctor(String key, String fav_id);

    }

}
