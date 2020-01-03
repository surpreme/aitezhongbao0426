package com.example.mvp.addDoctr;


import com.example.base.BaseView;
import com.example.bean.BaseBean;

import io.reactivex.Observable;

/**
 * 创建时间 2019/12/23 14:14
 * 描述:
 */
public interface AddDoctrContract {

    interface Model {
        //添加
        Observable<BaseBean> addDoctorInfo(String key, String work_address, String adept, String introduce, int professional_types, String departments, String position);
    }


    interface View extends BaseView {

        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void addDoctorInfo(BaseBean bean);
    }


    interface Presenter {

        void addDoctorInfo(String key, String work_address, String adept, String introduce,
                           int professional_types, String departments, String position);


    }

}
