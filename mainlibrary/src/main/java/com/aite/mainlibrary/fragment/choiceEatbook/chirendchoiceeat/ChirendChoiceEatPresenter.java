package com.aite.mainlibrary.fragment.choiceEatbook.chirendchoiceeat;

import android.app.Activity;

import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.BookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.ChioceEatBookListBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
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

import java.util.List;


public class ChirendChoiceEatPresenter extends BasePresenterImpl<ChirendChoiceEatContract.View> implements ChirendChoiceEatContract.Presenter {

    @Override
    public void getBookList(HttpParams httpParams) {
        OkGo.<BaseData<ChioceEatBookListBean>>get(AppConstant.INFORMATIONFACTBOOKURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<ChioceEatBookListBean>>() {
                    @Override
                    public BaseData<ChioceEatBookListBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Gson gson = new Gson();
                        ChioceEatBookListBean chioceEatBookListBean = gson.fromJson(jsonObject.toString(), ChioceEatBookListBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetBookListSuccess(chioceEatBookListBean));

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<ChioceEatBookListBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<ChioceEatBookListBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    @Override
    public void cancleOrder(HttpParams httpParams) {
        OkGo.<BaseData<TwoSuccessCodeBean>>post(AppConstant.POST_CANCEL_FACT_ORDER_URL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<TwoSuccessCodeBean>>() {
                    @Override
                    public BaseData<TwoSuccessCodeBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                            if (baseData.getDatas().getError() != null)
                                mView.showError(baseData.getDatas().getError());
                            return null;
                        } catch (Exception e) {
                            LogUtils.d(e);
                        }

                        String object = jsonObject.optString("datas");
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onCancleOrderSuccess(object.toString()));
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
    public void payOrder(HttpParams httpParams) {

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
//                        dfadsg

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
    public void PayFactCollect(HttpParams httpParams) {
        OkGo.<BaseData<TwoSuccessCodeBean>>get(AppConstant.PAYCOLLECTMORNINGMEALAWYGETINFORMATIONURL)
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
                                -> mView.onFactPayCollectSuccess(twoSuccessCodeBean));

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
    public void PayFactThreeElse(HttpParams httpParams, String payAway) {
        OkGo.<BaseData<AlipayOrderIdBean>>get(AppConstant.GET_FACTORDERPAYTHREEAPPFACTMONEYLISTINFORMATIONURL)
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
                                            -> mView.onFactPayThreeElseSuccess(alipayOrderIdBean, "alipay"));
                                } else if (payAway.equals("app_wxpay")) {
                                    WeChatPayBackBean weChatPayBackBean = gson.fromJson(object.toString(), WeChatPayBackBean.class);
                                    ((Activity) mView.getContext()).runOnUiThread(()
                                            -> mView.onFactPayThreeElseSuccess(weChatPayBackBean, "app_wxpay"));

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
