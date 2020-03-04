package com.aite.mainlibrary.activity.allshopcard.bookchoiceeatinformation;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.ChioceEatBookinformationBean;
import com.aite.mainlibrary.Mainbean.HelpDoctorInformationBean;
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

public class BookChoiceEatinformationPresenter extends BasePresenterImpl<BookChoiceEatinformationContract.View> implements BookChoiceEatinformationContract.Presenter {

    @Override
    public void getInformations(HttpParams httpParams) {
        OkGo.<BaseData<ChioceEatBookinformationBean>>get(AppConstant.FACTALLBOOKINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<ChioceEatBookinformationBean>>() {
                    @Override
                    public BaseData<ChioceEatBookinformationBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            ChioceEatBookinformationBean helpDoctorInformationBean = gson.fromJson(object.toString(), ChioceEatBookinformationBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetInformationSuccess(helpDoctorInformationBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<ChioceEatBookinformationBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<ChioceEatBookinformationBean>> response) {
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
}
