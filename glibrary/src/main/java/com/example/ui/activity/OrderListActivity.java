package com.example.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.app.Constants;
import com.example.base.GBaseActivity;
import com.example.base.OnClickRecyclerViewListener;
import com.example.bean.OrderListBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.mvp.orderList.OrderListContract;
import com.example.mvp.orderList.OrderListPresenter;
import com.example.ui.adapter.OrderListsAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间 2019/12/20 14:23
 * 描述:
 */
public class OrderListActivity extends GBaseActivity<OrderListPresenter> implements OrderListContract.View {
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R2.id.rb_all)
    RadioButton mRbAll;
    @BindView(R2.id.rb_proceed)
    RadioButton mRbProceed;
    @BindView(R2.id.rb_complete)
    RadioButton mRbComplete;
    @BindView(R2.id.ll_rg)
    RadioGroup mLlRg;
    @BindView(R2.id.recy_view)
    RecyclerView mRecyView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.tv_no_data_hint)
    TextView mTvNoDataHint;
    @BindView(R2.id.ll_no_data)
    LinearLayout mLlNoData;


    private OrderListsAdapter mAdapter;


    private int mCurpage = 1;
    // 0 全部  1待进行  2已完成
    private int mType = 0;
    private String mKey;


    @Override
    public int setLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initOthers() {
        mPresenter = new OrderListPresenter();
        mPresenter.attachView(this);

        initToolbar("订单");
        mTvTitleRight.setVisibility(View.GONE);
        mKey = AppConstant.KEY;

        mAdapter = new OrderListsAdapter(mContext);
        mRecyView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView.setAdapter(mAdapter);
        initListener();

        network();
    }


    private void network() {
        mAdapter.emptyData();
        mCurpage = 1;
        mPresenter.getOrderListData(mKey, mCurpage, Constants.PAGE_SIZE, mType);
//        if (AppConstant.CURRENT_IDENTITY == 2) {
//            //医生
//        } else {
//           //用户
//        }
    }


    private void initListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                //订单详情  c_order_id
                String cOrderId = mAdapter.getDataList().get(position).getC_order_id();
                toActivity(OrdersInfoActivity.class, "OrderId", cOrderId);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });


        mLlRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_all) {
                    mType = 0;
                    network();
                } else if (checkedId == R.id.rb_proceed) {
                    mType = 1;
                    network();
                } else if (checkedId == R.id.rb_complete) {
                    mType = 2;
                    network();
                }
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        network();
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.resetNoMoreData();
                    }
                }, 500);
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载更多
                        mCurpage++;
                        mPresenter.getOrderListData(mKey, mCurpage, Constants.PAGE_SIZE, mType);
                        mRefreshLayout.finishLoadMore();
                    }
                }, 500);
            }
        });


    }


    /**
     * 订单列表
     *
     * @param bean
     */
    @Override
    public void getOrderListData(OrderListBean bean) {
        if (bean.getCode() == 200) {
            List<OrderListBean.DatasBean> datas = bean.getDatas();

            mLlNoData.setVisibility(datas.size() > 0 ? View.GONE : View.VISIBLE);
            mRefreshLayout.setVisibility(datas.size() > 0 ? View.VISIBLE : View.GONE);
            if (!bean.isHasmore()) {
                mRefreshLayout.setNoMoreData(true);
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            }
            mAdapter.appendData(datas);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log("报错:" + throwable.toString());
    }

}

