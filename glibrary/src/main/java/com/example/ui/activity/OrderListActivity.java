package com.example.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.base.OnClickRecyclerViewListener;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.ui.adapter.OrderListsAdapter;
import com.lzy.basemodule.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间 2019/12/20 14:23
 * 描述:
 */
public class OrderListActivity extends BaseActivity {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.ll_rg)
    RadioGroup mLlRg;
    @BindView(R2.id.recy_view1)
    RecyclerView mRecyView1;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.rb_all)
    RadioButton mRbAll;
    @BindView(R2.id.rb_proceed)
    RadioButton mRbProceed;
    @BindView(R2.id.rb_complete)
    RadioButton mRbComplete;

    private OrderListsAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        initToolbar("订单");
        mTvTitleRight.setVisibility(View.GONE);
    }

    @Override
    protected void initDatas() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("----");
        }

        mAdapter = new OrderListsAdapter(context);
        mRecyView1.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        // mRecyView1.setNestedScrollingEnabled(false);
        mAdapter.appendData(data);
        mRecyView1.setAdapter(mAdapter);
        initListener();

    }

    private void initListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                //订单详情
                Intent intent = new Intent(context, OrdersInfoActivity.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @OnClick({R2.id.rb_all, R2.id.rb_proceed, R2.id.rb_complete})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.rb_all) {
            //全部订单

        } else if (id == R.id.rb_proceed) {
            //进行中

        } else if (id == R.id.rb_complete) {
            //完成

        }

    }
}

