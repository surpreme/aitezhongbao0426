package com.example.mvp.starDoctorPush;

import com.example.bean.AllAreaBean;
import com.example.bean.BaseBean;
import com.example.bean.DoctorListBean;
import com.example.net.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * 创建时间 2019/12/30 15:09
 * 描述:
 */
public class StarDoctorPushModel implements StarDoctorPushContract.Model {
    @Override
    public Observable<AllAreaBean> getAllAreaList() {
        return RetrofitClient.getInstance().getApi().getAllAreaList();
    }

    @Override
    public Observable<BaseBean<List<String>>> getDepartmentList(String key) {
        return RetrofitClient.getInstance().getApi().getDepartmentList(key);
    }

    @Override
    public Observable<BaseBean<List<String>>> getDoctorPosition(String key) {
        return RetrofitClient.getInstance().getApi().getDoctorPosition(key);
    }

    @Override
    public Observable<DoctorListBean> getDoctorList(String key, String keyword, String position, String departments, int consult_count, int evaluate_score, String provinceid, String cityid, int curpage, int pagesize) {
        return RetrofitClient.getInstance().getApi().getDoctorList(key, keyword, position, departments, consult_count, evaluate_score, provinceid, cityid, curpage, pagesize);
    }
}
