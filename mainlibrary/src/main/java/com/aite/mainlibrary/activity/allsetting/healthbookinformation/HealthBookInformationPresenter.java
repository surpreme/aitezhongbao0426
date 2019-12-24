package com.aite.mainlibrary.activity.allsetting.healthbookinformation;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.HealthBookInformationBean;
import com.aite.mainlibrary.Mainbean.HealthListBean;
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

public class HealthBookInformationPresenter extends BasePresenterImpl<HealthBookInformationContract.View> implements HealthBookInformationContract.Presenter {

    @Override
    public void getSickInformation(HttpParams httpParams) {
        OkGo.<BaseData<HealthBookInformationBean>>get(AppConstant.ALLMORELISTMSGHEALTHINFORMATIONDATAURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<HealthBookInformationBean>>() {
                    @Override
                    public BaseData<HealthBookInformationBean> convertResponse(okhttp3.Response response) throws Throwable {
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
                        HealthBookInformationBean healthBookInformationBean = gson.fromJson(object.toString(), HealthBookInformationBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetSickInformationSuccess(healthBookInformationBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<HealthBookInformationBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<HealthBookInformationBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
