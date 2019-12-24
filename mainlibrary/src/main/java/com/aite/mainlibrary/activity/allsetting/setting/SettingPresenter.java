package com.aite.mainlibrary.activity.allsetting.setting;

import android.app.Activity;
import android.content.Intent;

import com.aite.mainlibrary.Mainbean.SettingUiInformationBean;
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


public class SettingPresenter extends BasePresenterImpl<SettingContract.View> implements SettingContract.Presenter {

    @Override
    public void getInformation(HttpParams httpParams) {
        OkGo.<BaseData<SettingUiInformationBean>>get(AppConstant.SETTINGINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<SettingUiInformationBean>>() {
                    @Override
                    public BaseData<SettingUiInformationBean> convertResponse(okhttp3.Response response) throws Throwable {
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
                        SettingUiInformationBean settingUiInformationBean = BeanConvertor.convertBean(object.toString(), SettingUiInformationBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetInformationSuccess(settingUiInformationBean));

                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<SettingUiInformationBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<SettingUiInformationBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
