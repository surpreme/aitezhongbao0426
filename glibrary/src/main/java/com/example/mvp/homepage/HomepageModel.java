package com.example.mvp.homepage;

import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.net.RetrofitClient;

import io.reactivex.Observable;

/**
 * 创建时间 2020/1/2 18:29
 * 描述:
 */
public class HomepageModel implements HomepageContract.Model {
    @Override
    public Observable<BaseBean<DoctorInfoBean>> getDoctorInfo(String key, String doctor_id) {
        return RetrofitClient.getInstance().getApi().getDoctorInfo(key, doctor_id);
    }
}
