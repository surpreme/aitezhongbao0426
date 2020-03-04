package com.example.mvp.addDoctr;


import com.example.base.BaseView;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoPersonBean;

import io.reactivex.Observable;

/**
 * 创建时间 2019/12/23 14:14
 * 描述:
 */
public interface AddDoctrContract {

    interface Model {
        //添加
        Observable<BaseBean> addDoctorInfo(String key, String doctor_id, String work_address, String adept, String introduce, int professional_types, String departments, String position, String longitude, String latitude);

        Observable<BaseBean> editDoctorInfo(String key, String doctor_id, String work_address, String adept, String introduce, int professional_types, String departments, String position, String longitude, String latitude);

        Observable<BaseBean<DoctorInfoPersonBean>> getDoctorInfoPerson(String key, String member_id);

    }


    interface View extends BaseView {

        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void addDoctorInfo(BaseBean bean);

        void getDoctorInfoPerson(BaseBean<DoctorInfoPersonBean> bean);

        void editInfoSuccess(String s);
    }


    interface Presenter {

        void addDoctorInfo(String key, String doctor_id, String work_address, String adept, String introduce,
                           int professional_types, String departments, String position, String longitude, String latitude);

        void editDoctorInfo(String key, String doctor_id, String work_address, String adept, String introduce,
                            int professional_types, String departments, String position, String longitude, String latitude);

        void getDoctorInfoPerson(String key, String member_id);


    }

}
