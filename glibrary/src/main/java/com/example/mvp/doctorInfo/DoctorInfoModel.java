package com.example.mvp.doctorInfo;

import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.bean.IMInfoBean;
import com.example.net.RetrofitClient;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 创建时间 2020/1/2 15:21
 * 描述:
 */
public class DoctorInfoModel implements DoctorInfoContract.Model {
    @Override
    public Observable<BaseBean<DoctorInfoBean>> getDoctorInfo(String key, String doctor_id) {
        return RetrofitClient.getInstance().getApi().getDoctorInfo(key, doctor_id);
    }

    @Override
    public Observable<BaseBean<DoctorInfoBean>> getDoctorInfo1(String key, String member_id) {
        return RetrofitClient.getInstance().getApi().getDoctorInfo1(key, member_id);
    }

    @Override
    public Observable<IMInfoBean> getIMinfo(String key, String lang, String member_id) {
        return RetrofitClient.getInstance().getApi().getIMinfo(key, lang, member_id);
    }

    @Override
    public Flowable<ResponseBody> collectDoctor(String key, String fav_id) {
        return RetrofitClient.getInstance().getApi().collectDoctor(key, fav_id);
    }

    @Override
    public Flowable<ResponseBody> cancelCollectDoctor(String key, String fav_id) {
        return RetrofitClient.getInstance().getApi().cancelCollectDoctor(key, fav_id);
    }
}
