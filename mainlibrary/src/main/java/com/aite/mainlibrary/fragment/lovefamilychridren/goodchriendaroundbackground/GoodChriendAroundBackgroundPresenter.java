package com.aite.mainlibrary.fragment.lovefamilychridren.goodchriendaroundbackground;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.NewsBean;
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

public class GoodChriendAroundBackgroundPresenter extends BasePresenterImpl<GoodChriendAroundBackgroundContract.View> implements GoodChriendAroundBackgroundContract.Presenter {

    @Override
    public void getlist(HttpParams httpParams) {
        OkGo.<BaseData<NewsBean>>get(AppConstant.NEWSURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<NewsBean>>() {
                    @Override
                    public BaseData<NewsBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                            if (baseData.getDatas().getError() != null) {
                                mView.showError(baseData.getDatas().getError());
                                return null;
                            }
                        } catch (Exception e) {

                        }
                        Gson gson = new Gson();
                        NewsBean firstNewUserBean = gson.fromJson(jsonObject.toString(), NewsBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetlistSuccess(firstNewUserBean));


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<NewsBean>, ? extends
                            Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<NewsBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
