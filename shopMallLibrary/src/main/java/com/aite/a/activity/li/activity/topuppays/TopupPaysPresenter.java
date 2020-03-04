package com.aite.a.activity.li.activity.topuppays;

import android.app.Activity;

import com.aite.alipaylibrary.bean.AlipayOrderIdBean;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseData2;
import com.lzy.basemodule.bean.BaseDataX;
import com.lzy.basemodule.bean.BeanConvertor;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.BasePresenterImpl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONObject;


public class TopupPaysPresenter extends BasePresenterImpl<TopupPaysContract.View> implements TopupPaysContract.Presenter {

    @Override
    public void getPayThingInformation(HttpParams httpParams) {
        OkGo.<BaseData<ThingsInformationBean>>get(Constant.GET_PAY_INFORMATION)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<ThingsInformationBean>>() {
                    @Override
                    public BaseData<ThingsInformationBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        if (response.body() != null) {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                            if (baseData != null && baseData.getDatas() != null) {
                                if (baseData.getDatas().getError() != null) {
                                    mView.showError(baseData.getDatas().getError());
                                    return null;
                                } else {
                                    JSONObject object = jsonObject.optJSONObject("datas");
                                    ThingsInformationBean thingsInformationBean = BeanConvertor.convertBean(object.toString(), ThingsInformationBean.class);
                                    mView.onGetPayThingInformationSuccess(thingsInformationBean);

                                }
                            } else {
                                mView.showError("系统繁忙");

                            }

                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<ThingsInformationBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<ThingsInformationBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });

    }

    @Override
    public void getPayConfiguration(HttpParams httpParams, String payAway) {
        OkGo.<BaseData2<WeChatPayBackBean>>get(Constant.GET_PAY_CONFIGURATION)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData2<WeChatPayBackBean>>() {
                    @Override
                    public BaseData2<WeChatPayBackBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            Gson gson = new Gson();
                            if (payAway.equals("alipay")) {
                                String datas = jsonObject.optString("datas");
                                ((Activity) mView.getContext()).runOnUiThread(()
                                        -> mView.onGetPayConfigurationSuccess(datas, "alipay"));
                            } else if (payAway.equals("app_wxpay")) {
                                JSONObject object = jsonObject.optJSONObject("datas");
                                WeChatPayBackBean weChatPayBackBean = gson.fromJson(object.toString(), WeChatPayBackBean.class);
                                ((Activity) mView.getContext()).runOnUiThread(()
                                        -> mView.onGetPayConfigurationSuccess(weChatPayBackBean, "app_wxpay"));

                            }

                        } catch (Exception e) {
                            LogUtils.e(e);
                            mView.showError("系统错误");
                        }

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData2<WeChatPayBackBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData2<WeChatPayBackBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
