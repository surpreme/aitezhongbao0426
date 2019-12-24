package com.aite.mainlibrary.fragment.disputeChridren.disputebooklist;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.BookLessBodyFamilyBean;
import com.aite.mainlibrary.Mainbean.DisputeBookLIstBean;
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

public class DisputeBookLIstPresenter extends BasePresenterImpl<DisputeBookLIstContract.View> implements DisputeBookLIstContract.Presenter {

    @Override
    public void getListInformation(HttpParams httpParams) {
        OkGo.<BaseData<DisputeBookLIstBean>>get(AppConstant.GETLISTDISPUTEINFORMATIONDATAURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<DisputeBookLIstBean>>() {
                    @Override
                    public BaseData<DisputeBookLIstBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        BaseData baseData = BeanConvertor.convertBean(jsonObject.toString(), BaseData.class);
                        if (baseData.getDatas().getError() != null) {
                            mView.showError(baseData.getDatas().getError());
                            return null;
                        } else {
                            DisputeBookLIstBean disputeBookLIstBean = BeanConvertor.convertBean(jsonObject.toString(), DisputeBookLIstBean.class);
                            ((Activity) mView.getContext()).runOnUiThread(()
                                    -> mView.onGetListInformationSuccess(disputeBookLIstBean));
                        }


                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<DisputeBookLIstBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<DisputeBookLIstBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });


    }
}
