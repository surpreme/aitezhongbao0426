package com.aite.mainlibrary.fragment.disputeChridren.disputebooklist;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.BookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.DisputeBookLIstBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allshopcard.disputebookinformation.DisputeBookInformationActivity;
import com.aite.mainlibrary.adapter.DisputebookListRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseLazyFragment;
import com.lzy.basemodule.mvp.MVPBaseFragment;
import com.lzy.okgo.model.HttpParams;
import com.umeng.commonsdk.debug.D;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DisputeBookLIstFragment extends BaseLazyFragment<DisputeBookLIstContract.View, DisputeBookLIstPresenter> implements DisputeBookLIstContract.View {

    private DisputebookListRecyAdapter disputebookListRecyAdapter;
    private List<DisputeBookLIstBean.DatasBean.ListBean> disputeBookLIstBean = new ArrayList<>();

    @Override
    public void loadData() {
        mPresenter.getListInformation(initParams());

    }

    /**
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * key	get	字符串	必须			会员登录key
     * curpage	get	整型	可选	1		当前页码
     * pagesize	get	整型	可选	10		每页记录条数
     * complain_state	get	整型	可选	10		投诉状态 2全部 0进行中 1已关闭
     *
     * @return
     */
    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("curpage", mCurrentPage);
        params.put("pagesize", 10);
        if (getArguments() != null && getArguments().getString("position") != null) {
            switch (getArguments().getString("position")) {
                case "0":
                    params.put("complain_state", 2);
                    break;
                case "1":
                    params.put("complain_state", 0);
                    break;
                case "2":
                    params.put("complain_state", 1);
                    break;
                default:
                    params.put("complain_state", 2);
                    break;

            }

        }
        return params;
    }

    @Override
    protected void initModel() {

    }

    @Override
    protected void initViews() {
        initMoreRecy();
        mBaserecyclerView.setAdapter(disputebookListRecyAdapter = new DisputebookListRecyAdapter(context, disputeBookLIstBean));
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        disputebookListRecyAdapter.setClickInterface(position -> {
            startActivity(DisputeBookInformationActivity.class, "complain_id", disputeBookLIstBean.get(position).getComplain_id());
        });
        //smartlayout
        initSmartLayout(true);
        //初始化加载
        initLoadingAnima();
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        mPresenter.getListInformation(initParams());

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        if (disputeBookLIstBean != null) {
            disputeBookLIstBean.clear();
            disputebookListRecyAdapter.notifyDataSetChanged();
        }

        mPresenter.getListInformation(initParams());

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.smartlayout_recy_layout;
    }

    @Override
    public void onGetListInformationSuccess(Object msg) {
        DisputeBookLIstBean databean = (DisputeBookLIstBean) msg;
        if (databean == null) return;
        if (((DisputeBookLIstBean) msg).getDatas().getList().isEmpty()) {
            initNodata();
        } else {
            stopLoadingAnim();
            showMoreRecy();
            stopNoData();
            if (databean != null) {
                disputeBookLIstBean.addAll(databean.getDatas().getList());
                disputebookListRecyAdapter.notifyDataSetChanged();

            }
            hasMore = ((DisputeBookLIstBean) msg).isHasmore();
        }


    }
}
