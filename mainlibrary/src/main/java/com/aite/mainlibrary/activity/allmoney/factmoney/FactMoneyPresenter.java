package com.aite.mainlibrary.activity.allmoney.factmoney;

import android.app.Activity;

import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.PayAwayUiBean;
import com.aite.mainlibrary.Mainbean.PayPostAppAwayUiBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
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

public class FactMoneyPresenter extends BasePresenterImpl<FactMoneyContract.View> implements FactMoneyContract.Presenter {


    @Override
    public void getFactInFormation(HttpParams httpParams) {
        // APP提现页接口 获取展示提现页信息
        OkGo.<BaseData<PayAwayUiBean>>get(AppConstant.GET_FACTMONEYLISTINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<PayAwayUiBean>>() {
                    @Override
                    public BaseData<PayAwayUiBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                            if (baseData.getDatas().getError() != null) {
                                mView.showError(baseData.getDatas().getError());
                            }

                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();
                        PayAwayUiBean payAwayUiBean = gson.fromJson(object.toString(), PayAwayUiBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetFactInFormationSuccess(payAwayUiBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<PayAwayUiBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<PayAwayUiBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    //    // APP充值页接口 获取展示充值页信息
    @Override
    public void getPostForInmationMoney(HttpParams httpParams) {
        OkGo.<BaseData<PayPostAppAwayUiBean>>post(AppConstant.POST_POSTAPPFACTMONEYLISTINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<PayPostAppAwayUiBean>>() {
                    @Override
                    public BaseData<PayPostAppAwayUiBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                            if (baseData.getDatas().getError() != null) {
                                mView.showError(baseData.getDatas().getError());
                            }

                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
//                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();
                        PayPostAppAwayUiBean payPostAppAwayUiBean = gson.fromJson(jsonObject.toString(), PayPostAppAwayUiBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetPostInFormationSuccess(payPostAppAwayUiBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<PayPostAppAwayUiBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<PayPostAppAwayUiBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    //    // APP提现页接口 保存申请提现内容
    @Override
    public void sureGetFactMoney(HttpParams httpParams) {
        OkGo.<BaseData<TwoSuccessCodeBean>>post(AppConstant.POST_SURETOBANKFACTMONEYLISTINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<TwoSuccessCodeBean>>() {
                    @Override
                    public BaseData<TwoSuccessCodeBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                            if (baseData.getDatas().getError() != null) {
                                mView.showError(baseData.getDatas().getError());
                            } else {
                                JSONObject object = jsonObject.optJSONObject("datas");
                                Gson gson = new Gson();
                                TwoSuccessCodeBean twoSuccessCodeBean = gson.fromJson(object.toString(), TwoSuccessCodeBean.class);
                                ((Activity) mView.getContext()).runOnUiThread(()
                                        -> mView.onSureGetFactMoneySuccess(twoSuccessCodeBean));
                            }

                        } catch (Exception e) {
                            LogUtils.e(e);
                        }

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

    //充值 alipay app_wxpay
    @Override
    public void surePostFactMoney(HttpParams httpParams, String payAway) {
        OkGo.<BaseData<AlipayOrderIdBean>>post(AppConstant.POST_LOSEPOSTAPPFACTMONEYLISTINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<AlipayOrderIdBean>>() {
                    @Override
                    public BaseData<AlipayOrderIdBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                            if (baseData.getDatas().getError() != null) {
                                mView.showError(baseData.getDatas().getError());
                            } else {
                                JSONObject object = jsonObject.optJSONObject("datas");
                                Gson gson = new Gson();
                                if (payAway.equals("alipay")) {
                                    AlipayOrderIdBean alipayOrderIdBean = gson.fromJson(object.toString(), AlipayOrderIdBean.class);
                                    ((Activity) mView.getContext()).runOnUiThread(()
                                            -> mView.onSurePostFactMoneySuccess(alipayOrderIdBean));
                                } else if (payAway.equals("app_wxpay")) {
                                    WeChatPayBackBean weChatPayBackBean = gson.fromJson(object.toString(), WeChatPayBackBean.class);
                                    ((Activity) mView.getContext()).runOnUiThread(()
                                            -> mView.onSurePostFactMoneySuccess(weChatPayBackBean));

                                }

                            }

                        } catch (Exception e) {
                            LogUtils.e(e);
                        }

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<AlipayOrderIdBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<AlipayOrderIdBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }


}
