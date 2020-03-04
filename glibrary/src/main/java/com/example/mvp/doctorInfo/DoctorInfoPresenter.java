package com.example.mvp.doctorInfo;

import com.example.base.GBasePresenter;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.bean.IMInfoBean;
import com.example.net.RxScheduler;
import com.lzy.basemodule.mvp.BasePresenter;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;


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

    @Override
    public void getDoctorInfo1(String key, String member_id) {
        mView.showLoading();
        mModel.getDoctorInfo1(key, member_id)
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

    @Override
    public void getIMinfo(String key, String lang, String member_id) {
        if (!isViewAttached()) {
            return;
        }

        mModel.getIMinfo(key, lang, member_id)
                .compose(RxScheduler.<IMInfoBean>Obs_io_main())
                .as(mView.<IMInfoBean>bindAutoDispose())
                .subscribe(new Consumer<IMInfoBean>() {
                    @Override
                    public void accept(IMInfoBean bean) throws Exception {
                        mView.getIMinfo(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                    }
                });

    }

    @Override
    public void collectDoctor(String key, String fav_id) {
        if (!isViewAttached()) {
            return;
        }
        mModel.collectDoctor(key, fav_id)
                .compose(RxScheduler.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        mView.onCollectDoctorSuccess(responseBody.string());
                    }
                });

    }

    @Override
    public void cancelCollectDoctor(String key, String fav_id) {
        if (!isViewAttached()) {
            return;
        }
        mModel.cancelCollectDoctor(key, fav_id)
                .compose(RxScheduler.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        mView.onCancelCollectDoctorSuccess(responseBody.string());
                    }
                });

    }
}
