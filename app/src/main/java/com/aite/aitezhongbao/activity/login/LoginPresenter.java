package com.aite.aitezhongbao.activity.login;

import android.app.Activity;

import com.aite.aitezhongbao.app.App;
import com.aite.aitezhongbao.bean.FirstNewUserBean;
import com.aite.aitezhongbao.bean.LogInBean;
import com.aite.aitezhongbao.bean.SureFindPasswordCodeBean;
import com.aite.mainlibrary.Mainbean.ThreeSuccessCodeBean;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseDataEtras;
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

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(HttpParams params) {
        OkGo.<BaseDataEtras<LogInBean>>post(AppConstant.LOGINURL)
                .tag(App.getContext())
                .params(params)
                .execute(new AbsCallback<BaseDataEtras<LogInBean>>() {
                    @Override
                    public BaseDataEtras<LogInBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseDataEtras baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseDataEtras.class);
                        LogUtils.d(baseData.getRegister_step() + "------" + baseData.getKey());
                        if (baseData.getRegister_step() != null || baseData.getKey() != null) {
                            if (baseData.getRegister_step().equals("2") || baseData.getRegister_step().equals("3"))
                                mView.logInNeedMoreMsg(baseData.getRegister_step(), baseData.getKey());
                        } else if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
//                            JSONObject registejson = jsonObject.optJSONObject("register_step");
//                            JSONObject keyjson = jsonObject.optJSONObject("key");
//                            LogUtils.d(registejson.toString() + "------" + keyjson.toString());
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            LogInBean logInBean = BeanConvertor.convertBean(datas.toString(), LogInBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.logInSuccess(logInBean));

//                            mView.setNewPasswordonSuccess(sureFindPasswordCodeBean);

                        }

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseDataEtras<LogInBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseDataEtras<LogInBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });

    }

    @Override
    public void weChatLogin(HttpParams httpParams) {
        OkGo.<BaseDataEtras<ThreeSuccessCodeBean>>post(AppConstant.WECHATLOGINURL)
                .tag(App.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseDataEtras<ThreeSuccessCodeBean>>() {
                    @Override
                    public BaseDataEtras<ThreeSuccessCodeBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseDataEtras baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseDataEtras.class);
                        LogUtils.d(baseData.getRegister_step() + "------" + baseData.getKey());
                        if (baseData.getRegister_step() != null || baseData.getKey() != null) {
                            if (baseData.getRegister_step().equals("2") || baseData.getRegister_step().equals("3"))
                                mView.logInNeedMoreMsg(baseData.getRegister_step(), baseData.getKey());
                        } else if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            ThreeSuccessCodeBean threeSuccessCodeBean = gson.fromJson(datas.toString(), ThreeSuccessCodeBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.weChatLoginSuccess(threeSuccessCodeBean));
                        }

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseDataEtras<ThreeSuccessCodeBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseDataEtras<ThreeSuccessCodeBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    @Override
    public void weChatnowloginLogin(HttpParams httpParams) {
        OkGo.<BaseDataEtras<ThreeSuccessCodeBean>>post(AppConstant.NOWWECHATLOGINURL)
                .tag(App.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseDataEtras<ThreeSuccessCodeBean>>() {
                    @Override
                    public BaseDataEtras<ThreeSuccessCodeBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Gson gsonn = new Gson();
                        BaseDataEtras baseData = gsonn.fromJson(jsonObject.toString(), BaseDataEtras.class);
                        LogUtils.d(baseData.getRegister_step() + "------" + baseData.getKey());
                        if (baseData.getRegister_step() != null || baseData.getKey() != null) {
                            if (baseData.getRegister_step().equals("2") || baseData.getRegister_step().equals("3"))
                                mView.logInNeedMoreMsg(baseData.getRegister_step(), baseData.getKey());
                        } else if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        JSONObject datas = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();
                        ThreeSuccessCodeBean threeSuccessCodeBean = gson.fromJson(datas.toString(), ThreeSuccessCodeBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.weChatnowloginSuccess(threeSuccessCodeBean));


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseDataEtras<ThreeSuccessCodeBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseDataEtras<ThreeSuccessCodeBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }


}