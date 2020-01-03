package com.example.mvp.doctorInfo;

import com.example.base.GBasePresenter;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.net.RxScheduler;
import com.lzy.basemodule.mvp.BasePresenter;

import io.reactivex.functions.Consumer;


/**
 * 创建时间 2020/1/2 15:21
 * 描述:
 */
public class DoctorInfoPresenter extends GBasePresenter<DoctorInfoContract.View> implements DoctorInfoContract.Presenter {

    private DoctorInfoModel mModel;

    public DoctorInfoPresenter() {
        mModel = new DoctorInfoModel();
    }


    @Override
    public void getDoctorInfo(String key, String doctor_id) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        mModel.getDoctorInfo(key, doctor_id)
                .compose(RxScheduler.<BaseBean<DoctorInfoBean>>Obs_io_main())
                .as(mView.<BaseBean<DoctorInfoBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseBean<DoctorInfoBean>>() {
                    @Override
                    public void accept(BaseBean<DoctorInfoBean> bean) throws Exception {
                        mView.getDoctorInfo(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });
    }
}
