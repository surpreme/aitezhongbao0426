package com.aite.mainlibrary.fragment.choiceEatbook.chirendchoiceeat;

import android.app.Activity;

import com.aite.mainlibrary.Mainbean.BookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.ChioceEatBookListBean;
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

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChirendChoiceEatPresenter extends BasePresenterImpl<ChirendChoiceEatContract.View> implements ChirendChoiceEatContract.Presenter {

    @Override
    public void getBookList(HttpParams httpParams) {
        OkGo.<BaseData<ChioceEatBookListBean>>get(AppConstant.INFORMATIONFACTBOOKURL)
                .tag(mView.getContext())
                .params(httpParams)
                .execute(new AbsCallback<BaseData<ChioceEatBookListBean>>() {
                    @Override
                    public BaseData<ChioceEatBookListBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Gson gson = new Gson();
                        ChioceEatBookListBean chioceEatBookListBean = gson.fromJson(jsonObject.toString(), ChioceEatBookListBean.class);
                        ((Activity) mView.getContext()).runOnUiThread(()
                                -> mView.onGetBookListSuccess(chioceEatBookListBean));

                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseData<ChioceEatBookListBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseData<ChioceEatBookListBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }
}
