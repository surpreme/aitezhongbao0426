package com.example.mvp.doctorInfo;

import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.net.RetrofitClient;

import io.reactivex.Observable;

/**
 * 创建时间 2020/1/2 15:21
 * 描述:
 */
public class DoctorInfoModel implements DoctorInfoContract.Model {
    @Override
    public Observable<BaseBean<DoctorInfoBean>> getDoctorInfo(String key, String doctor_id) {
        return RetrofitClient.getInstance().getApi().getDoctorInfo(key, doctor_id);
    }
}
