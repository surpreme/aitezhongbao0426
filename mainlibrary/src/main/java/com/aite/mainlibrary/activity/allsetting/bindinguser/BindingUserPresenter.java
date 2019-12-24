package com.aite.mainlibrary.activity.allsetting.bindinguser;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.BinderUserListBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BeanConvertor;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.BasePresenterImpl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONObject;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BindingUserPresenter extends BasePresenterImpl<BindingUserContract.View> implements BindingUserContract.Presenter {

    @Override
    public void getInformation(HttpParams httpParams) {
        OkGo.<BaseData<BinderUserListBean>>get(AppConstant.LISTPEPPLEINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<BinderUserListBean>>() {
                    @Override
                    public BaseData<BinderUserListBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();

                        BinderUserListBean binderUserListBean = gson.fromJson(object.toString(), BinderUserListBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetInformationSuccess(binderUserListBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<BinderUserListBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<BinderUserListBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    @Override
    public void deleteInformation(HttpParams httpParams) {
        OkGo.<BaseData<TwoSuccessCodeBean>>post(AppConstant.UNBINDBINDINGUSERURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<TwoSuccessCodeBean>>() {
                    @Override
                    public BaseData<TwoSuccessCodeBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();

                        TwoSuccessCodeBean twoSuccessCodeBean = gson.fromJson(object.toString(), TwoSuccessCodeBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onDeleteInformationSuccess(twoSuccessCodeBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<TwoSuccessCodeBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<TwoSuccessCodeBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
