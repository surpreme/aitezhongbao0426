package com.aite.mainlibrary.activity.allsetting.addbookdispute;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.BinderUserListBean;
import com.aite.mainlibrary.Mainbean.DisputeTypeBean;
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

public class AddBookDisputePresenter extends BasePresenterImpl<AddBookDisputeContract.View> implements AddBookDisputeContract.Presenter {

    @Override
    public void getDisputeType(HttpParams httpParams) {
        OkGo.<BaseData<DisputeTypeBean>>post(AppConstant.DISPUTETYPEINFORMATIONDATAURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<DisputeTypeBean>>() {
                    @Override
                    public BaseData<DisputeTypeBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();

                        DisputeTypeBean disputeTypeBean = gson.fromJson(object.toString(), DisputeTypeBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetDisputeTypeSuccess(disputeTypeBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<DisputeTypeBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<DisputeTypeBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    @Override
    public void postAllDispute(HttpParams httpParams) {
        OkGo.<BaseData<DisputeTypeBean>>post(AppConstant.SAVEDISPUTEINFORMATIONDATAURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<DisputeTypeBean>>() {
                    @Override
                    public BaseData<DisputeTypeBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        String code = jsonObject.optString("code");
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onPostAllDisputeSuccess(code));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<DisputeTypeBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<DisputeTypeBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
