package com.aite.mainlibrary.activity.im.mallchater;

import android.app.Activity;
import com.aite.mainlibrary.Mainbean.MallChaterBean;
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

public class MallChaterPresenter extends BasePresenterImpl<MallChaterContract.View> implements MallChaterContract.Presenter {

    @Override
    public void onGetIMChatList(HttpParams httpParams) {
        OkGo.<BaseData<MallChaterBean>>get(AppConstant.GET_IMSHOPMESSAGEINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<MallChaterBean>>() {
                    @Override
                    public BaseData<MallChaterBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            MallChaterBean mallChaterBean = gson.fromJson(object.toString(), MallChaterBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetIMChatListSuccess(mallChaterBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<MallChaterBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<MallChaterBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });

    }
}
