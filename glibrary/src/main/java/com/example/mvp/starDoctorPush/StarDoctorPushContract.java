package com.example.mvp.starDoctorPush;

import com.example.base.BaseView;
import com.example.bean.AllAreaBean;
import com.example.bean.BaseBean;
import com.example.bean.DoctorListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.Query;

/**
 * 创建时间 2019/12/30 15:09
 * 描述:
 */
public interface StarDoctorPushContract {

    interface Model {
        //全国地址
        Observable<AllAreaBean> getAllAreaList();

        //科室列表
        Observable<BaseBean<List<String>>> getDepartmentList(String key);

        //医生职位
        Observable<BaseBean<List<String>>> getDoctorPosition(String key);

        //医生列表
        Observable<DoctorListBean> getDoctorList(String key, String keyword, String position, String departments, int consult_count,
                                                 int evaluate_score, String provinceid, String cityid, int curpage, int pagesize);

    }


    interface View extends BaseView {
        void getAllAreaList(AllAreaBean bean);

        void getDepartmentList(BaseBean<List<String>> bean);

        void getDoctorPosition(BaseBean<List<String>> bean);

        void getDoctorList(DoctorListBean bean);

    }

    interface Presenter {
        void getAllAreaList();

        void getDepartmentList(String key);

        void getDoctorPosition(String key);

        void getDoctorList(String key, String keyword, String position, String departments, int consult_count,
                           int evaluate_score, String provinceid, String cityid, int curpage, int pagesize);
    }


}
