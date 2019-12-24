package com.aite.mainlibrary.fragment.lovefamilychridren.goodchriendaroundbackground;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.NewsBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.adapter.GoodlikeRecyAdapter;
import com.aite.mainlibrary.adapter.NewsRecyAdapter;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.mvp.MVPBaseFragment;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GoodChriendAroundBackgroundFragment extends BaseFragment<GoodChriendAroundBackgroundContract.View, GoodChriendAroundBackgroundPresenter> implements GoodChriendAroundBackgroundContract.View {
    private GoodlikeRecyAdapter goodlikeRecyAdapter;
    private List<NewsBean.DatasBean.ListBean> listBeans = new ArrayList<>();

    @Override
    protected void initModel() {
        mPresenter.getlist(initParams());

    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("is_recommend", 0);
        httpParams.put("pagesize", 100);
        return httpParams;
    }

    @Override
    protected void initViews() {
        initMoreRecy();
        mBaserecyclerView.setAdapter(goodlikeRecyAdapter = new GoodlikeRecyAdapter(context, listBeans));
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        goodlikeRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {

            }
        });


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.recy_layout;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetlistSuccess(Object msg) {
        listBeans.addAll(((NewsBean) msg).getDatas().getList());
        goodlikeRecyAdapter.notifyDataSetChanged();
    }
}
