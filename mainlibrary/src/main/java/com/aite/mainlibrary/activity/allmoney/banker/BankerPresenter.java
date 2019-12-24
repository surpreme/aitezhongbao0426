package com.aite.mainlibrary.activity.allmoney.banker;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.MineBankListBean;
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

public class BankerPresenter extends BasePresenterImpl<BankerContract.View> implements BankerContract.Presenter {

    @Override
    public void GetbankList(HttpParams httpParams) {
        OkGo.<BaseData<MineBankListBean>>get(AppConstant.BANKLISTINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<MineBankListBean>>() {
                    @Override
                    public BaseData<MineBankListBean> convertResponse(okhttp3.Response response) throws Throwable {
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
                        MineBankListBean mineBankListBean = gson.fromJson(object.toString(), MineBankListBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetbankListSuccess(mineBankListBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<MineBankListBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<MineBankListBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });

    }

    @Override
    public void Deletebank(HttpParams httpParams) {
        OkGo.<BaseData<TwoSuccessCodeBean>>post(AppConstant.DELETESAVEBANKINFORMATIONURL)
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
                            }

                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();
                        TwoSuccessCodeBean twoSuccessCodeBean = gson.fromJson(object.toString(), TwoSuccessCodeBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onDeletebankSuccess(twoSuccessCodeBean));
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
