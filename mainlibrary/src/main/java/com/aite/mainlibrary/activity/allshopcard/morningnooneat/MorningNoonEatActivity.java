package com.aite.mainlibrary.activity.allshopcard.morningnooneat;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.HelpDoctorListBean;
import com.aite.mainlibrary.Mainbean.MorningNoonEatBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allshopcard.remembershopbook.RememberShopBookActivity;
import com.aite.mainlibrary.adapter.MorningNoonEatsRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MorningNoonEatActivity extends BaseActivity<MorningNoonEatContract.View, MorningNoonEatPresenter> implements MorningNoonEatContract.View {
    private MorningNoonEatsRecyAdapter morningNoonEatRecyAdapter;
    private List<MorningNoonEatBean.GoodsListBean> goodsListBeanList = new ArrayList<>();
    private String moniningnoontyye;

    @Override
    protected int getLayoutResId() {
        return R.layout.smart_recy_toolbar;
    }

    @Override
    protected void initView() {
        moniningnoontyye = getIntent().getStringExtra("type");
        assert moniningnoontyye != null;
        initToolbar(moniningnoontyye.equals("morning") ? "早餐" : "午餐");
        initRecy();
        //smartlayout
        initSmartLayout(true);
        //初始化加载
        initLoadingAnima();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mBaserecyclerView.setLayoutManager(linearLayoutManager);
        morningNoonEatRecyAdapter = new MorningNoonEatsRecyAdapter(context, goodsListBeanList);
        mBaserecyclerView.setAdapter(morningNoonEatRecyAdapter);
        morningNoonEatRecyAdapter.setLstenerInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                startActivity(RememberShopBookActivity.class, "goods_id", goodsListBeanList.get(postion).getGoods_id());
            }
        });

    }

    @Override
    protected void initDatas() {
        mPresenter.getEatDataList(initParams(), moniningnoontyye.equals("morning") ? "1" : "2");

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        if (moniningnoontyye != null)
            params.put("page_type", moniningnoontyye.equals("morning") ? 1 : 2);
        params.put("curpage", mCurrentPage);
        return params;
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        mPresenter.getEatDataList(initParams(), moniningnoontyye.equals("morning") ? "1" : "2");

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        if (goodsListBeanList != null) {
            goodsListBeanList.clear();
            morningNoonEatRecyAdapter.notifyDataSetChanged();
        }

        mPresenter.getEatDataList(initParams(), moniningnoontyye.equals("morning") ? "1" : "2");

    }

    @Override
    public void getDataListSuccess(Object msg) {
        if (((MorningNoonEatBean) msg).getGoods_list() != null) {
            if (((MorningNoonEatBean) msg).getGoods_list().isEmpty()) {
                initNodata();
            } else {
                stopNoData();
                goodsListBeanList.addAll(((MorningNoonEatBean) msg).getGoods_list());
                morningNoonEatRecyAdapter.notifyDataSetChanged();
//                hasMore = ((MorningNoonEatBean) msg).getStatus() > 0;
                hasMore = ((MorningNoonEatBean) msg).getGoods_list().isEmpty();
            }

        }


    }
}
