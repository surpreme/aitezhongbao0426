package com.aite.mainlibrary.activity.allshopcard.bookinformation;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.HelpDoctorInformationBean;
import com.aite.mainlibrary.Mainbean.MorningNoonBookInformationBean;
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

public class BookinformationPresenter extends BasePresenterImpl<BookinformationContract.View> implements BookinformationContract.Presenter {

    @Override
    public void GetBookInformation(HttpParams httpParams) {
        OkGo.<BaseData<MorningNoonBookInformationBean>>get(AppConstant.INFORMATIONUNFACTBOOKURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<MorningNoonBookInformationBean>>() {
                    @Override
                    public BaseData<MorningNoonBookInformationBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            Gson gson = new Gson();
                            MorningNoonBookInformationBean morningNoonBookInformationBean = gson.fromJson(object.toString(), MorningNoonBookInformationBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetBookInformationSuccess(morningNoonBookInformationBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<MorningNoonBookInformationBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<MorningNoonBookInformationBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
