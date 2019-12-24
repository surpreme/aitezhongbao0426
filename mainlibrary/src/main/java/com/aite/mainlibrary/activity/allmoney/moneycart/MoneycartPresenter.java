package com.aite.mainlibrary.activity.allmoney.moneycart;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.MoneyCollectBean;
import com.google.gson.Gson;
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

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoneycartPresenter extends BasePresenterImpl<MoneycartContract.View> implements MoneycartContract.Presenter {

    @Override
    public void GetInformation(HttpParams httpParams) {
        OkGo.<BaseData<MoneyCollectBean>>get(AppConstant.MYWALLETINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<MoneyCollectBean>>() {
                    @Override
                    public BaseData<MoneyCollectBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        }
                        JSONObject object = jsonObject.optJSONObject("datas");
                        Gson gson = new Gson();

                        MoneyCollectBean moneyCollectBean = gson.fromJson(object.toString(), MoneyCollectBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetInformationSuccess(moneyCollectBean));
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<MoneyCollectBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<MoneyCollectBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
