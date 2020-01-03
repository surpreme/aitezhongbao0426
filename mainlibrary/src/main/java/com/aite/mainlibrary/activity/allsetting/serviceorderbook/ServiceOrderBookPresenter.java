package com.aite.mainlibrary.activity.allsetting.serviceorderbook;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.BookLessBodyFamilyBean;
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

public class ServiceOrderBookPresenter extends BasePresenterImpl<ServiceOrderBookContract.View> implements ServiceOrderBookContract.Presenter {

    @Override
    public void onGetOrderBookList(HttpParams httpParams) {
        OkGo.<BaseData<BookLessBodyFamilyBean>>get(AppConstant.BOOKINFORMATIONLISTMINELISTURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<BookLessBodyFamilyBean>>() {
                    @Override
                    public BaseData<BookLessBodyFamilyBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            BookLessBodyFamilyBean bookLessBodyFamilyBean = BeanConvertor.convertBean(object.toString(), BookLessBodyFamilyBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetOrderBookListSuccess(bookLessBodyFamilyBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<BookLessBodyFamilyBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<BookLessBodyFamilyBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });

    }

    @Override
    public void Cancelinformation(HttpParams httpParams) {
        OkGo.<BaseData<TwoSuccessCodeBean>>post(AppConstant.POST_CANCELBOOKINFORMATIONLISTMINELISTURL)
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
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            TwoSuccessCodeBean twoSuccessCodeBean = gson.fromJson(object.toString(), TwoSuccessCodeBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onCancelinformationSuccess(twoSuccessCodeBean));
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
}
