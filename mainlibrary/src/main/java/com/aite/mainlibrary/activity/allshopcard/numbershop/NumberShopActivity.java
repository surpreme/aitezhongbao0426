package com.aite.mainlibrary.activity.allshopcard.numbershop;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.TimeShoplistBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.adapter.TimeShopRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class NumberShopActivity extends BaseActivity<NumberShopContract.View, NumberShopPresenter> implements NumberShopContract.View {
    private TimeShopRecyAdapter timeShopRecyAdapter;

    private List<TimeShoplistBean.ListBean> timeShoplistbean = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.recy_toolbar;
    }

    @Override
    protected void initView() {
        initToolbar("积分商城");
        timeShopRecyAdapter = new TimeShopRecyAdapter(context, timeShoplistbean);
        initRecy();
        mBaserecyclerView.setAdapter(timeShopRecyAdapter);
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        timeShopRecyAdapter.setClickInterface(postion -> {
            PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, "您确认要兑换此物品吗?", v -> {
                mPresenter.replaceThing(initParams(timeShoplistbean.get(postion).getPgoods_id()));
                PopwindowUtils.getmInstance().dismissPopWindow();
            });


        });
    }

    @Override
    protected void initDatas() {
        mPresenter.GetShopList(initKeyParams());

    }

    private HttpParams initParams(String PGOODS_ID) {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("pgoods_id", PGOODS_ID);
        return params;
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    public void onGetShopListSuccess(Object msg) {
        timeShoplistbean.addAll(((TimeShoplistBean) msg).getList());
        timeShopRecyAdapter.notifyDataSetChanged();

    }

    @Override
    public void onReplaceThingSuccess(Object msg) {
        if ((String) msg != null) {
            showToast("兑换成功");
            onBackPressed();
        }

    }
}
