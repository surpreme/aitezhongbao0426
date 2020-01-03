package com.aite.mainlibrary.activity.allmain.soundadvice;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.BaseFirstResultBean;
import com.aite.mainlibrary.Mainbean.SoundAdviceInformationTitleBean;
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

public class SoundAdvicePresenter extends BasePresenterImpl<SoundAdviceContract.View> implements SoundAdviceContract.Presenter {

    @Override
    public void getUiTitleSuccess(HttpParams httpParams) {
        OkGo.<BaseData<SoundAdviceInformationTitleBean>>get(AppConstant.GET_READTITLEBOOKINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<SoundAdviceInformationTitleBean>>() {
                    @Override
                    public BaseData<SoundAdviceInformationTitleBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            SoundAdviceInformationTitleBean soundAdviceInformationTitleBean = gson.fromJson(object.toString(), SoundAdviceInformationTitleBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetUiTitleSuccess(soundAdviceInformationTitleBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<SoundAdviceInformationTitleBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<SoundAdviceInformationTitleBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });

    }

    @Override
    public void getUriContentSuccess(HttpParams httpParams) {
        OkGo.<BaseData<BaseFirstResultBean>>get(AppConstant.GET_READBOOKINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<BaseFirstResultBean>>() {
                    @Override
                    public BaseData<BaseFirstResultBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            BaseFirstResultBean baseFirstResultBean = gson.fromJson(object.toString(), BaseFirstResultBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetUriContentSuccess(baseFirstResultBean));
                        }

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<BaseFirstResultBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<BaseFirstResultBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
