package com.aite.mainlibrary.activity.allstep.vipStep;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.mainlibrary.activity.allsetting.editSosUser.RetrofitInterface;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.baseRetrofit.http.RetrofitClient;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.ErrorBean;
import com.lzy.basemodule.net.RxScheduler;

import org.json.JSONObject;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-13
 * @desc:
 */
public class StepViewModel extends BaseViewModel {
    public static final String TOKEN_GET_DATA_SUCCESS = "TOKEN_GET_DATA_SUCCESS";
    public static final String TOKEN_STEP_SUCCESS = "TOKEN_STEP_SUCCESS";

    public StepViewModel(@NonNull Application application) {
        super(application);
        titleTv.set("签到");
        stepTipsTv.set("累计签到0天，便可领取小礼品");
    }

    public ObservableField<String> titleTv = new ObservableField<>();
    public ObservableField<String> currentNumberTv = new ObservableField<>();
    //android:text="累计签到20天，便可领取小礼品"
    public ObservableField<String> stepTipsTv = new ObservableField<>();
    public BindingCommand backOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onBackPressed();
        }
    });
    public BindingCommand StepOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onUserStep(AppConstant.KEY);
        }
    });

    /**
     * @param key
     */
    @SuppressLint("CheckResult")
    public void onGetStepInformation(String key) {
        RetrofitClient.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetUserStepData(key)
                .compose(RxScheduler.Flo_io_main())
                .filter(stepInformationBeanBaseData -> {
                    if (stepInformationBeanBaseData.isSuccessed()) {
                        return false;
                    } else {
                        if (stepInformationBeanBaseData.getDatas().getError() != null) {
                            ToastUtils.showShort(stepInformationBeanBaseData.getDatas().getError());
                            LogUtils.e(stepInformationBeanBaseData.getDatas().getError());
                            dismissDialog();
                        }
                        return true;
                    }
                })
                .map(BaseData::getDatas)
                .filter(stepInformationBean -> {
                    if (stepInformationBean.getError() != null) {
                        ToastUtils.showShort(stepInformationBean.getError());
                        LogUtils.e(stepInformationBean.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(stepInformationBean -> {
            Messenger.getDefault().send(stepInformationBean, StepViewModel.TOKEN_GET_DATA_SUCCESS);
            onGetStepInformationSuccess(stepInformationBean);
            dismissDialog();


        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });
    }

    /**
     * @param key
     */
    @SuppressLint("CheckResult")
    public void onUserStep(String key) {
        RetrofitClient.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onUserStep(key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code.equals("200")) {
                            ToastUtils.showShort("签到成功");
                            Messenger.getDefault().send("SUCCESS", StepViewModel.TOKEN_STEP_SUCCESS);
                        } else {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
                            if (errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                            }
                        }
                    }
                });
    }

    /**
     * * 返回字段	类型	说明
     * * datas->signCount	整型	总签到数
     * * datas->continuousCount	整型	连续签到数
     * * datas->dayCount	整型	当前月总天数
     * * datas->app_thisMonthInfo[]	数组	当前月已签到日期
     *
     * @param stepInformationBean
     */

    private void onGetStepInformationSuccess(StepInformationBean stepInformationBean) {
        try {
            if (stepInformationBean.getContinuousCount() != null && !stepInformationBean.getContinuousCount().equals("null"))
                stepTipsTv.set(String.format("已连续签到%s天，累计连续签到20天，便可领取小礼品", stepInformationBean.getContinuousCount()));
            else
                stepTipsTv.set("累计连续签到20天，便可领取小礼品");
            currentNumberTv.set(stepInformationBean.getSignCount());
        } catch (Exception e) {
            LogUtils.e(e);
        }


    }
}
