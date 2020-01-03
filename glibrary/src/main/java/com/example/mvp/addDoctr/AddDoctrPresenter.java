package com.example.mvp.addDoctr;


import com.example.base.GBasePresenter;
import com.example.bean.BaseBean;
import com.example.net.RxScheduler;

import io.reactivex.functions.Consumer;

/**
 * 创建时间 2019/12/23 14:24
 * 描述:
 */
public class AddDoctrPresenter extends GBasePresenter<AddDoctrContract.View> implements AddDoctrContract.Presenter {

    private AddDoctrModel mModel;

    public AddDoctrPresenter() {
        mModel = new AddDoctrModel();
    }

    @Override
    public void addDoctorInfo(String key, String work_address, String adept, String introduce, int professional_types, String departments, String position) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        mModel.addDoctorInfo(key, work_address, adept, introduce, professional_types, departments, position)
                .compose(RxScheduler.<BaseBean>Obs_io_main())
                .as(mView.<BaseBean>bindAutoDispose())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean bean) throws Exception {
                        mView.addDoctorInfo(bean);
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
