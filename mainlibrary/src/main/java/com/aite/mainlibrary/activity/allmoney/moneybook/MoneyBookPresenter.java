package com.aite.mainlibrary.activity.allmoney.moneybook;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.MoneyBookBean;
import com.aite.mainlibrary.Mainbean.PayAwayUiBean;
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

public class MoneyBookPresenter extends BasePresenterImpl<MoneyBookContract.View> implements MoneyBookContract.Presenter {

    @Override
    public void getHostory(HttpParams httpParams) {
        // APP账单明细 获取会员账单明细
        OkGo.<BaseListData<MoneyBookBean>>get(AppConstant.GET_HOSTORYLISTMONEYLISTINFORMATIONURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseListData<MoneyBookBean>>() {
                    @Override
                    public BaseListData<MoneyBookBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            BaseListData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseListData.class);
                            if (baseData.getDatas().getError() != null) {
                                mView.showError(baseData.getDatas().getError());
                            } else {
                                JSONObject object = jsonObject.optJSONObject("datas");
                                Gson gson = new Gson();
                                MoneyBookBean moneyBookBean = gson.fromJson(object.toString(), MoneyBookBean.class);
                                ((Activity) mView.getContext()).runOnUiThread(()
                                        -> mView.onGetHostorySuccess(moneyBookBean, baseData.isHasmore(), baseData.getPage_total()));
                            }

                        } catch (Exception e) {
                            LogUtils.e(e);
                        }

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseListData<MoneyBookBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseListData<MoneyBookBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
