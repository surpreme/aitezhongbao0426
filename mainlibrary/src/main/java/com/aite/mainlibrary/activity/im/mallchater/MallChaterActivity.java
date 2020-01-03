package com.aite.mainlibrary.activity.im.mallchater;


import android.content.Intent;

import com.aite.mainlibrary.Mainbean.MallChaterBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.im.activity.ChatShowActivity;
import com.aite.mainlibrary.adapter.MallChtersRecyAdapter;
import com.lzy.basemodule.base.BaseSmartLayoutActivity;
import com.lzy.basemodule.base.Listener.OnClickRecyclerViewListener;

import java.io.Serializable;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MallChaterActivity extends BaseSmartLayoutActivity<MallChaterContract.View, MallChaterPresenter> implements MallChaterContract.View {
    private MallChtersRecyAdapter mallChaterRecyAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.toolbar_recy;
    }

    @Override
    protected void initDatas() {
        mPresenter.onGetIMChatList(initListHttpParams(true));

    }

    @Override
    protected void initView() {
        super.initView();
        mBaserecyclerView.setAdapter(mallChaterRecyAdapter = new MallChtersRecyAdapter(context));
        mallChaterRecyAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(ChatShowActivity.class);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }

    @Override
    protected String getToolBarTitle() {
        return "客服列表";
    }

    @Override
    protected void clearDatas() {
        mallChaterRecyAdapter.emptyData();

    }

    @Override
    protected boolean isRefresh() {
        return false;
    }


    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onGetIMChatListSuccess(Object msg) {
        MallChaterBean mallChaterBean = (MallChaterBean) msg;
//        mallChaterRecyAdapter.appendData(mallChaterBean.getPlatform_callcenter());
        Intent intent = new Intent(this, ChatShowActivity.class);
        intent.putExtra("server", (Serializable) mallChaterBean.getPlatform_callcenter());
        startActivity(intent);

    }
}
