package com.lzy.basemodule.rxjava;

import android.content.Intent;

import com.google.gson.JsonSyntaxException;
import com.lzy.basemodule.activitylife.ActivityManager;
import com.lzy.basemodule.base.BaseResponse;
import com.lzy.basemodule.base.androidlife.AppManager;
import com.lzy.basemodule.baseRetrofit.http.ApiException;
import com.lzy.basemodule.baseRetrofit.http.ExceptionHandler;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseDataWhitoutErrorCode;
import com.lzy.basemodule.bean.ErrorBean;
import com.lzy.basemodule.bean.HasExtraBean;
import com.lzy.basemodule.bean.OrderDiscussBean;
import com.lzy.basemodule.bean.PageBean;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.basemodule.mvp3.IView;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.umeng.commonsdk.debug.E;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver3<T> extends DisposableObserver<BaseDataWhitoutErrorCode<T>> {

    private BaseView baseView;

    private boolean isShowError;

    private String errorMsg;

    public BaseObserver3(@NotNull BaseView baseView) {
        this.baseView = baseView;
    }

    public BaseObserver3(@NotNull BaseView baseView, boolean isShowError) {
        this(baseView);
        this.isShowError = isShowError;
    }

    public BaseObserver3(@NotNull BaseView baseView, String errorMsg, boolean isShowError) {
        this(baseView, isShowError);
        this.errorMsg = errorMsg;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onNext(BaseDataWhitoutErrorCode<T> baseResponse) {
        if (baseView != null) {
            baseView.showNormal();
        }
        String code = baseResponse.getCode().toString();
        String msg = baseResponse.getMessage();
        String login = baseResponse.getLogin();
        // 兼容 gank api
        boolean isOk = baseResponse.isSuccessed();
        if (code.equals("0")) {
            if (login != null && login.equals("0")) {
                Intent intent = new Intent();
                intent.setAction("com.aite.aitezhongbao.app.activity.login.LoginActivity");
                AppManager.getInstance().killAllActivity();
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);

            } else {
                T data = baseResponse.getDatas();
                if (data == null) {
                    if (baseView != null)
                        baseView.showEmpty();
                    return;
                } else if (data instanceof List) {
                    List<Object> list = (List<Object>) data;
                    if (list.isEmpty() && baseView != null) {
                        baseView.showEmpty();
                        return;
                    }
                } else if (data instanceof ErrorBean) {
                    if (((ErrorBean) data).getError() != null && baseView != null) {
                        baseView.showError(((ErrorBean) data).getError());
                        return;
                    }
                }
                // 将服务端获取到的正常数据传递给上层调用方
                onSuccess(data);
            }
        } else if (isOk) {   // gank api
            T data = baseResponse.getDatas();
            onSuccess(data);
        } else {
            onError(new ApiException(code, msg));
        }
    }

    /**
     * 回调正常数据
     *
     * @param data
     */
    protected abstract void onSuccess(T data);

    /**
     * 异常处理，包括两方面数据：
     * (1) 服务端没有没有返回数据，HttpException，如网络异常，连接超时;
     * (2) 服务端返回了数据，但 errcode!=0,即服务端返回的data为空，如 密码错误,App登陆超时,token失效
     */
    @Override
    public void onError(Throwable e) {
        ExceptionHandler.handleException(e);
        LogUtils.e(e.toString());
        if (e instanceof JsonSyntaxException) {
            LogUtils.e(e.toString());
        } else if (e instanceof ApiException) {
            ToastUtils.showToast(ActivityManager.getInstance().getCurrentActivity(), ((ApiException) e).getErrmsg());
        } else if (isShowError) {
            if (errorMsg != null && !errorMsg.isEmpty())
                baseView.showError(errorMsg);
            else
                baseView.showError(e.getMessage());
        } else {
            baseView.showError("");
        }
    }

    @Override
    public void onComplete() {
        if (baseView != null) {
            baseView.dimissLoading();
        }
    }


}
