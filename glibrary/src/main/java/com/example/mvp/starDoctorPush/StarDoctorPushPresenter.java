package com.example.mvp.starDoctorPush;

import com.example.base.GBasePresenter;
import com.example.bean.AllAreaBean;
import com.example.bean.BaseBean;
import com.example.bean.DoctorListBean;
import com.example.net.RxScheduler;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 创建时间 2019/12/30 15:10
 * 描述:
 */
public class StarDoctorPushPresenter extends GBasePresenter<StarDoctorPushContract.View> implements StarDoctorPushContract.Presenter {

    private StarDoctorPushModel mModel;

    public StarDoctorPushPresenter() {
        mModel = new StarDoctorPushModel();
    }

    @Override
    public void getAllAreaList() {
        if (!isViewAttached()) {
            return;
        }
//        mView.showLoading();
        mModel.getAllAreaList()
                .compose(RxScheduler.<AllAreaBean>Obs_io_main())
                .as(mView.<AllAreaBean>bindAutoDispose())
                .subscribe(new Consumer<AllAreaBean>() {
                    @Override
                    public void accept(AllAreaBean bean) throws Exception {
                        mView.getAllAreaList(bean);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        //mView.hideLoading();
                    }
                });

    }

    @Override
    public void getDepartmentList(String key) {
        if (!isViewAttached()) {
            return;
        }
        mModel.getDepartmentList(key)
                .compose(RxScheduler.<BaseBean<List<String>>>Obs_io_main())
                .as(mView.<BaseBean<List<String>>>bindAutoDispose())
                .subscribe(new Consumer<BaseBean<List<String>>>() {
                    @Override
                    public void accept(BaseBean<List<String>> bean) throws Exception {
                        mView.getDepartmentList(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                    }
                });
    }

    @Override
    public void getDoctorPosition(String key) {
        if (!isViewAttached()) {
            return;
        }
        mModel.getDoctorPosition(key)
                .compose(RxScheduler.<BaseBean<List<String>>>Obs_io_main())
                .as(mView.<BaseBean<List<String>>>bindAutoDispose())
                .subscribe(new Consumer<BaseBean<List<String>>>() {
                    @Override
                    public void accept(BaseBean<List<String>> bean) throws Exception {
                        mView.getDoctorPosition(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                    }
                });
    }

    @Override
    public void getDoctorList(String key, String keyword, String position, String departments, int consult_count, int evaluate_score, String provinceid, String cityid, int curpage, int pagesize) {
        if (!isViewAttached()) {
            return;
        }

        mView.showLoading();
        mModel.getDoctorList(key, keyword, position, departments, consult_count, evaluate_score, provinceid, cityid, curpage, pagesize)
                .compose(RxScheduler.<DoctorListBean>Obs_io_main())
                .as(mView.<DoctorListBean>bindAutoDispose())
                .subscribe(new Consumer<DoctorListBean>() {
                    @Override
                    public void accept(DoctorListBean bean) throws Exception {
                        mView.getDoctorList(bean);
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
