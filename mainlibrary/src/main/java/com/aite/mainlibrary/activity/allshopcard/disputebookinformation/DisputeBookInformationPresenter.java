package com.aite.mainlibrary.activity.allshopcard.disputebookinformation;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.BookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.DisputeInformationBean;
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

public class DisputeBookInformationPresenter extends BasePresenterImpl<DisputeBookInformationContract.View> implements DisputeBookInformationContract.Presenter {

    @Override
    public void getDispute(HttpParams httpParams) {
        OkGo.<BaseData<DisputeInformationBean>>post(AppConstant.GETALLINFORMATIONDISPUTEINFORMATIONDATAURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<DisputeInformationBean>>() {
                    @Override
                    public BaseData<DisputeInformationBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            DisputeInformationBean disputeInformationBean = BeanConvertor.convertBean(object.toString(), DisputeInformationBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetDisputeSuccess(disputeInformationBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<DisputeInformationBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<DisputeInformationBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
