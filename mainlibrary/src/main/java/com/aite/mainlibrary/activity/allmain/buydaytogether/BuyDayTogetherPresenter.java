package com.aite.mainlibrary.activity.allmain.buydaytogether;

import android.app.Activity;
import android.content.Intent;

import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.BuySencondBean;
import com.aite.mainlibrary.Mainbean.LessBodyInformationBean;
import com.aite.mainlibrary.Mainbean.PayHelpServiceSuccessBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.androidlife.AppManager;
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

public class BuyDayTogetherPresenter extends BasePresenterImpl<BuyDayTogetherContract.View> implements BuyDayTogetherContract.Presenter {

    @Override
    public void buyService(HttpParams httpParams) {
        OkGo.<BaseData<PayHelpServiceSuccessBean>>post(AppConstant.OVERBUYINFORMATIONLISTMINELISTURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<PayHelpServiceSuccessBean>>() {
                    @Override
                    public BaseData<PayHelpServiceSuccessBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String login = jsonObject.optString("login", jsonObject.toString());
                        if (login.equals("0")) {
                            LogUtils.d(login);
                            Intent intent = new Intent();
                            intent.setAction("com.aite.aitezhongbao.app.activity.login.LoginActivity");
                            AppManager.getInstance().killAllActivity();
                            mView.getContext().startActivity(intent);
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        PayHelpServiceSuccessBean payHelpServiceSuccessBean = BeanConvertor.convertBean(object.toString(), PayHelpServiceSuccessBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onBuyThingSuccesss(payHelpServiceSuccessBean));

                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<PayHelpServiceSuccessBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<PayHelpServiceSuccessBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });

    }

    @Override
    public void getInformation(HttpParams httpParams) {
        OkGo.<BaseData<LessBodyInformationBean>>post(AppConstant.BUYSECONDINFORMATIONLISTMINELISTURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<LessBodyInformationBean>>() {
                    @Override
                    public BaseData<LessBodyInformationBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String login = jsonObject.optString("login", jsonObject.toString());
                        if (login.equals("0")) {
                            LogUtils.d(login);
                            Intent intent = new Intent();
                            intent.setAction("com.aite.aitezhongbao.app.activity.login.LoginActivity");
                            AppManager.getInstance().killAllActivity();
                            mView.getContext().startActivity(intent);
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        BuySencondBean buySencondBean = BeanConvertor.convertBean(object.toString(), BuySencondBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetInformationSuceess(buySencondBean));

                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<LessBodyInformationBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<LessBodyInformationBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    @Override
    public void getPayList(HttpParams httpParams) {
        OkGo.<BaseData<PayListBean>>get(AppConstant.PAYAWYGETINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<PayListBean>>() {
                    @Override
                    public BaseData<PayListBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                            if (baseData.getDatas().getError() != null) {
                                mView.showError(baseData.getDatas().getError());
                                return null;
                            } else {
//                            JSONObject object = jsonObject.optJSONObject("datas");
                            }
                        } catch (Exception e) {

                        }

                        Gson gson = new Gson();
                        PayListBean payListBean = gson.fromJson(jsonObject.toString(), PayListBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onPayListSuccess(payListBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<PayListBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<PayListBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    @Override
    public void PayCollect(HttpParams httpParams) {
        OkGo.<BaseData<TwoSuccessCodeBean>>get(AppConstant.NOREALPAYCOLLECTMORNINGMEALAWYGETINFORMATIONURL)
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
                                return null;
                            }
                        } catch (Exception e) {
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();
                        TwoSuccessCodeBean twoSuccessCodeBean = gson.fromJson(object.toString(), TwoSuccessCodeBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onPayCollectSuccess(twoSuccessCodeBean));

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

    @Override
    public void PayThreeElse(HttpParams httpParams, String payAway) {
        OkGo.<BaseData<AlipayOrderIdBean>>get(AppConstant.GET_UNFACTORDERPAYTHREEAPPFACTMONEYLISTINFORMATIONURL)
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
                                            -> mView.onPayThreeElseSuccess(alipayOrderIdBean, "alipay"));
                                } else if (payAway.equals("app_wxpay")) {
                                    WeChatPayBackBean weChatPayBackBean = gson.fromJson(object.toString(), WeChatPayBackBean.class);
                                    ((Activity) mView.getContext()).runOnUiThread(()
                                            -> mView.onPayThreeElseSuccess(weChatPayBackBean, "app_wxpay"));

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
