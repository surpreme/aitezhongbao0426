package com.example.mvp.consult;

import com.example.base.BaseView;
import com.example.bean.BaseBean;
import com.example.bean.ConsultListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.Query;

/**
 * @Auther: liziyang
 * @datetime: 2020/1/3
 * @desc:
 */
public interface ConsultContract {

    interface Model {

        //医生咨询价格列表
        Observable<ConsultListBean> getConsultList(String key, int type, int curpage, int pagesize);


        //医生咨询价格添加
        Observable<BaseBean> setConsultPrice(String key, String doctor_id, int type, String time, String price);


        //医生咨询价格修改
        Observable<BaseBean> modifyConsultPrice(String key, String c_price_id, int type, String time, String price);

    }

    interface View extends BaseView {
        void setConsultPrice(BaseBean bean);

        void getConsultList(ConsultListBean bean);

        void modifyConsultPrice(BaseBean bean);

    }

    interface Presenter {

        void setConsultPrice(String key, String doctor_id, int type, String time, String price);


        void getConsultList(String key, int type, int curpage, int pagesize);

        void modifyConsultPrice(String key, String c_price_id, int type, String time, String price);
    }

}
