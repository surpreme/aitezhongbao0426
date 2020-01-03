package com.aite.mainlibrary.activity.im.systemmsg;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.MorningNoonBookInformationBean;
import com.aite.mainlibrary.Mainbean.SystemMsgBean;
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

public class SyStemMsgPresenter extends BasePresenterImpl<SyStemMsgContract.View> implements SyStemMsgContract.Presenter {
    //系统站内信列表
    @Override
    public void getSystemList(HttpParams httpParams) {
        OkGo.<BaseListData<SystemMsgBean>>get(AppConstant.GET_IMSYSTEMMESSAGEINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseListData<SystemMsgBean>>() {
                    @Override
                    public BaseListData<SystemMsgBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseListData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseListData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            SystemMsgBean systemMsgBean = gson.fromJson(object.toString(), SystemMsgBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetSystemListSuccess(systemMsgBean, baseData.isHasmore(), baseData.getPage_total()));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseListData<SystemMsgBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseListData<SystemMsgBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
