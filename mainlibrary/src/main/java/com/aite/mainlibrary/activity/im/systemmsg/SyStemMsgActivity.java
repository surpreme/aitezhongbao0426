package com.aite.mainlibrary.activity.im.systemmsg;


import com.aite.mainlibrary.Mainbean.SystemMsgBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.adapter.SyStemMsgAdapter;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SyStemMsgActivity extends BaseActivity<SyStemMsgContract.View, SyStemMsgPresenter> implements SyStemMsgContract.View {
    private SyStemMsgAdapter syStemMsgAdapter;
    private List<SystemMsgBean.MessageArrayBean> messageArrayBeanList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.smart_recy_toolbar;
    }

    @Override
    protected void initView() {
        initToolbar("系统消息");
        initRecy();
        initSmartLayout(true);
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBaserecyclerView.setAdapter(syStemMsgAdapter = new SyStemMsgAdapter(this));
        syStemMsgAdapter.appendData(messageArrayBeanList);

    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        initDatas();
    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        syStemMsgAdapter.emptyData();
        initDatas();

    }

    @Override
    protected void initDatas() {
        mPresenter.getSystemList(initListHttpParams(true, new ContentValue("curpage", mCurrentPage)));

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onGetSystemListSuccess(Object msg, boolean isHaveMore, String allPages) {
        hasMore = isHaveMore;
        SystemMsgBean systemMsgBean = (SystemMsgBean) msg;
        if (systemMsgBean.getMessage_array().isEmpty())
            initNodata();
        else {
            stopNoData();
            syStemMsgAdapter.appendData(messageArrayBeanList = systemMsgBean.getMessage_array());
        }


    }
}
