package com.aite.mainlibrary.activity.allmain.elsehelp;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.ElseServiceIconBean;
import com.aite.mainlibrary.Mainbean.NewsInformationBean;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.bean.BaseData;
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

public class ElseHelpPresenter extends BasePresenterImpl<ElseHelpContract.View> implements ElseHelpContract.Presenter {

    @Override
    public void GetIcon(HttpParams httpParams) {
        OkGo.<BaseData<ElseServiceIconBean>>get(AppConstant.ELSESERVICEINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<ElseServiceIconBean>>() {
                    @Override
                    public BaseData<ElseServiceIconBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();
                        ElseServiceIconBean elseServiceIconBean = gson.fromJson(object.toString(), ElseServiceIconBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetIconSuccess(elseServiceIconBean));

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<ElseServiceIconBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<ElseServiceIconBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
