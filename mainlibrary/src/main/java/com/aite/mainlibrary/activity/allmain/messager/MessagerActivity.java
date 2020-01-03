package com.aite.mainlibrary.activity.allmain.messager;


import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Constant.MainUIConstant;
import com.aite.mainlibrary.Mainbean.SystemMsgBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.im.mallchater.MallChaterActivity;
import com.aite.mainlibrary.activity.im.systemmsg.SyStemMsgActivity;
import com.aite.mainlibrary.adapter.GridViewIconAdapter;
import com.aite.mainlibrary.adapter.SyStemMsgAdapter;
import com.lzy.basemodule.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessagerActivity extends BaseActivity<MessagerContract.View, MessagerPresenter> implements MessagerContract.View {
    @BindView(R2.id.setting_gridview)
    GridView settingGridview;
    private SyStemMsgAdapter syStemMsgAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.message_layout;
    }

    @Override
    protected void initView() {
        initToolbar("消息");
        initRecy();
        mBaserecyclerView.setAdapter(syStemMsgAdapter = new SyStemMsgAdapter(this));
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        settingGridview.setAdapter(new GridViewIconAdapter(context, MainUIConstant.MessageConstant.settingImg, MainUIConstant.MessageConstant.settingTv));
        settingGridview.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 2) {
                startActivity(SyStemMsgActivity.class);
            } else if (position == 1) {
                startActivity(MallChaterActivity.class);
            }

        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initDatas() {
        mPresenter.onGetSystemMsg(initKeyParams());
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    public void onGetSystemMsgSuccess(Object msg) {
        SystemMsgBean systemMsgBean=(SystemMsgBean)msg;
        if (systemMsgBean.getMessage_array()!=null){
            syStemMsgAdapter.appendData(systemMsgBean.getMessage_array());
        }

    }
}
