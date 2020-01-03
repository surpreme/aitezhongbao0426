package com.example.mvp.doctorInfo;

import com.example.base.BaseView;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;

import io.reactivex.Observable;
import retrofit2.http.Query;


/**
 * 创建时间 2020/1/2 15:21
 * 描述:
 */
public interface DoctorInfoContract {

    interface Model {
        Observable<BaseBean<DoctorInfoBean>> getDoctorInfo(String key, String doctor_id);
    }

    interface View extends BaseView {
        void getDoctorInfo(BaseBean<DoctorInfoBean> bean);

    }

    interface Presenter {
        void getDoctorInfo(String key, String doctor_id);
    }

}
