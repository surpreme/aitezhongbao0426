package com.aite.mainlibrary.activity.allmoney.moneybook;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.MoneyBookBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.adapter.MoneyBookRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.mvp.MVPBaseActivity;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoneyBookActivity extends BaseActivity<MoneyBookContract.View, MoneyBookPresenter> implements MoneyBookContract.View {
    private MoneyBookRecyAdapter moneyBookRecyAdapter;
    private List<MoneyBookBean.ListLogBean> datasBeans = new ArrayList<>();


    @Override
    protected int getLayoutResId() {
        return R.layout.toobar_recy_layout;
    }

    @Override
    protected void initView() {
        initToolbar("账单明细");
        initRecy();
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBaserecyclerView.setAdapter(moneyBookRecyAdapter = new MoneyBookRecyAdapter(context, datasBeans));
        moneyBookRecyAdapter.setClickInterface(position -> {

        });

    }

    @Override
    protected void initDatas() {
        mPresenter.getHostory(initParams());

    }

    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("curpage", mCurrentPage);
//        params.put("search_time", AppConstant.KEY);
        return params;
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onGetHostorySuccess(Object msg, boolean isHaveMore, String allpages) {
        MoneyBookBean moneyBookBean = (MoneyBookBean) msg;
        if (moneyBookBean != null) {
            if (!datasBeans.isEmpty()) {
                datasBeans.clear();
                moneyBookRecyAdapter.notifyDataSetChanged();
            }
            datasBeans.addAll(moneyBookBean.getList_log());
            moneyBookRecyAdapter.notifyDataSetChanged();
        }

    }
}
