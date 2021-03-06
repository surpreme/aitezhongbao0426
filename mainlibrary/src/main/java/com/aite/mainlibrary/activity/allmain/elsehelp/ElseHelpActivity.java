package com.aite.mainlibrary.activity.allmain.elsehelp;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.ElseServiceIconBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allshopcard.daytogether.DayTogetherActivity;
import com.aite.mainlibrary.activity.allstep.vipStep.StepActivity;
import com.aite.mainlibrary.adapter.ElseServiceUIAdapter;
import com.lzy.basemodule.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ElseHelpActivity extends BaseActivity<ElseHelpContract.View, ElseHelpPresenter> implements ElseHelpContract.View {
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    private ElseServiceUIAdapter elseServiceUIAdapter;
    private List<ElseServiceIconBean.ListClassBean> elseServiceIconBeans = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.else_help_layout;
    }

    @Override
    protected void initView() {
        initToolbar("其他服务");
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(elseServiceUIAdapter = new ElseServiceUIAdapter(context, elseServiceIconBeans));
        elseServiceUIAdapter.setOnClickLstenerInterface(postion -> {
            if (postion == elseServiceIconBeans.size() - 1)
                startActivity(StepActivity.class);
            else {
                startActivity(DayTogetherActivity.class, "type", "5", "else_list_class", elseServiceIconBeans.get(postion).getGc_id());
            }
        });


    }

    @Override
    protected void initDatas() {
        mPresenter.GetIcon(initKeyParams());

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    public void onGetIconSuccess(Object msg) {
        elseServiceIconBeans.addAll(((ElseServiceIconBean) msg).getList_class());
        elseServiceUIAdapter.notifyDataSetChanged();

    }
}
