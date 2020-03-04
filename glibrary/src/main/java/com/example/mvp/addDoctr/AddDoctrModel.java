package com.example.mvp.addDoctr;


import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoPersonBean;
import com.example.net.RetrofitClient;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * 创建时间 2019/12/23 14:13
 * 描述:
 */
public class AddDoctrModel implements AddDoctrContract.Model {

    @Override
    public Observable<BaseBean> addDoctorInfo(String key,String doctor_id, String work_address, String adept, String introduce, int professional_types, String departments, String position,String longitude,String latitude) {
        return RetrofitClient.getInstance().getApi().addDoctorInfo(key,doctor_id, work_address, adept, introduce, professional_types, departments, position,longitude,latitude);
    }

    @Override
    public Observable<BaseBean> editDoctorInfo(String key,String doctor_id,String work_address, String adept, String introduce, int professional_types, String departments, String position,String longitude,String latitude) {
        return RetrofitClient.getInstance().getApi().editDoctorInfo(key,doctor_id, work_address, adept, introduce, professional_types, departments, position,longitude,latitude);
    }

    @Override
    public Observable<BaseBean<DoctorInfoPersonBean>> getDoctorInfoPerson(String key, String work_address) {
        return RetrofitClient.getInstance().getApi().getDoctorInfoPersoner(key, work_address);
    }
}
