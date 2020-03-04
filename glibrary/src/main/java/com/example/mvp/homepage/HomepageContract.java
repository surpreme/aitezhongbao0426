package com.example.mvp.homepage;

import com.example.base.BaseView;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;

import io.reactivex.Observable;

/**
 * 创建时间 2020/1/2 18:22
 * 描述:
 */
public interface HomepageContract {

    interface Model {
        Observable<BaseBean<DoctorInfoBean>> getDoctorInfo(String key, String doctor_id);

        //医生信息(自己)
        Observable<BaseBean<DoctorInfoBean>> getDoctorInfo1(String key, String member_id);

    }

    interface View extends BaseView {
        void getDoctorInfo(BaseBean<DoctorInfoBean> bean);



    }

    interface Presenter {
        void getDoctorInfo(String key, String doctor_id);

        void getDoctorInfo1(String key, String member_id);
    }

}
