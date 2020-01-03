package com.example.mvp.addDoctr;


import com.example.bean.BaseBean;
import com.example.net.RetrofitClient;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * 创建时间 2019/12/23 14:13
 * 描述:
 */
public class AddDoctrModel implements AddDoctrContract.Model {

    @Override
    public Observable<BaseBean> addDoctorInfo(String key, String work_address, String adept, String introduce, int professional_types, String departments, String position) {
        return RetrofitClient.getInstance().getApi().addDoctorInfo(key, work_address, adept, introduce, professional_types, departments, position);
    }
}
