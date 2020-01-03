package com.aite.mainlibrary.activity.allshopcard.orderdiscuss;

import android.annotation.SuppressLint;

import com.lzy.basemodule.baseRetrofit.http.RetrofitClient;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.mvp.BasePresenterImpl;
import com.lzy.basemodule.net.RxScheduler;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderDiscussPresenter extends BasePresenterImpl<OrderDiscussContract.View> implements OrderDiscussContract.Presenter {

    /**
     * new Callback<BaseData<OrderDiscussBean>>() {
     *
     * @param goods_id
     * @param type
     * @param curpage
     * @Override public void onResponse(Call<BaseData<OrderDiscussBean>> call, Response<BaseData<OrderDiscussBean>> response) {
     * LogUtils.d(response.body());
     * Gson gson = new Gson();
     * OrderDiscussBean orderDiscussBean = gson.fromJson(response.body().toString());
     * <p>
     * }
     * @Override public void onFailure(Call<BaseData<OrderDiscussBean>> call, Throwable t) {
     * <p>
     * }
     * }
     */

    @SuppressLint("CheckResult")
    @Override
    public void getDiscuss(String goods_id, String type, int curpage) {
        RetrofitResultInterface retrofitResultInterface =
                RetrofitClient.getInstance().getRetrofit().create(RetrofitResultInterface.class);
        retrofitResultInterface.getOrderDiscuss(goods_id, type, curpage)
                .compose(RxScheduler.Flo_io_main())
                .filter(o -> !o.getCode().toString().equals("200"))
                .map(BaseData::getDatas)
                .filter(p -> {
                    if (p.getError() != null) {
                        mView.showError(p.getError());
                        return false;
                    } else {
                        return true;
                    }
                })
                .subscribe(orderDiscussBeanBaseData ->
                                mView.onGetDiscussSuccess(orderDiscussBeanBaseData,
                                        orderDiscussBeanBaseData.getIs_nextpage() > 0),
                        throwable -> mView.showError(throwable.getMessage()));
    }
}