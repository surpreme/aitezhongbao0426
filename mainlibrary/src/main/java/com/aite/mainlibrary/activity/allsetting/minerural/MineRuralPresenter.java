package com.aite.mainlibrary.activity.allsetting.minerural;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.AirMainListBean;
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



public class MineRuralPresenter extends BasePresenterImpl<MineRuralContract.View> implements MineRuralContract.Presenter {

    @Override
    public void GetMineList(HttpParams httpParams) {
        OkGo.<BaseData<MineRuralPushListBean>>get(AppConstant.MINECOLLECTPOSTLIST)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<MineRuralPushListBean>>() {
                    @Override
                    public BaseData<MineRuralPushListBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            JSONObject object = jsonObject.optJSONObject("datas");
                            MineRuralPushListBean mineRuralPushListBean = BeanConvertor.convertBean(object.toString(), MineRuralPushListBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetMineListSuccess(mineRuralPushListBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<MineRuralPushListBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<MineRuralPushListBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
