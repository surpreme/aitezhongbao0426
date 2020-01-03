package com.aite.mainlibrary.activity.allmain.messager;


import android.app.Activity;

import com.aite.mainlibrary.Mainbean.SyStemRencenterBean;
import com.aite.mainlibrary.Mainbean.SystemMsgBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseListData;
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

public class MessagerPresenter extends BasePresenterImpl<MessagerContract.View> implements MessagerContract.Presenter {

    @Override
    public void onGetSystemMsg(HttpParams httpParams) {
        OkGo.<BaseData<SystemMsgBean>>get(AppConstant.GET_SYSTEMTODAYINFORMATIONLISTMINELISTURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<SystemMsgBean>>() {
                    @Override
                    public BaseData<SystemMsgBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                        } else {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            SystemMsgBean syStemRencenterBean = gson.fromJson(datas.toString(), SystemMsgBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetSystemMsgSuccess(syStemRencenterBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<SystemMsgBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<SystemMsgBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
