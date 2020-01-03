package com.aite.mainlibrary.activity.allshopcard.orderdiscuss;


import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.OrderDiscussRecyAdapter;
import com.lzy.basemodule.base.BaseSmartLayoutActivity;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseDataWhitoutErrorCode;
import com.lzy.basemodule.bean.OrderDiscussBean;

import butterknife.BindView;
import butterknife.OnClick;


public class OrderDiscussActivity extends BaseSmartLayoutActivity<OrderDiscussContract.View, OrderDiscussPresenter> implements OrderDiscussContract.View {
    @BindView(R2.id.talk_all_tv)
    TextView talkAllTv;
    @BindView(R2.id.talk_goods_tv)
    TextView talkGoodTv;
    @BindView(R2.id.talk_norm_tv)
    TextView talkNormTv;
    @BindView(R2.id.talk_bad_tv)
    TextView talkBadTv;
    private OrderDiscussRecyAdapter orderDiscussRecyAdapter;
    private String type = "0";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_order_discuss;
    }

    @Override
    protected String getToolBarTitle() {
        return "评价详情";
    }

    @Override
    protected void clearDatas() {
        orderDiscussRecyAdapter.emptyData();
    }

    @Override
    protected boolean isRefresh() {
        return true;
    }

    @Override
    protected void initView() {
        super.initView();
        mBaserecyclerView.setAdapter(orderDiscussRecyAdapter = new OrderDiscussRecyAdapter(context));
    }

    @Override
    protected void initDatas() {
        mPresenter.getDiscuss(getIntent().getStringExtra("goods_id"), type, mCurrentPage);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ClearState() {
        talkAllTv.setBackground(getDrawable(R.mipmap.good_sell_talkback));
        talkGoodTv.setBackground(getDrawable(R.mipmap.good_sell_talkback));
        talkNormTv.setBackground(getDrawable(R.mipmap.good_sell_talkback));
        talkBadTv.setBackground(getDrawable(R.mipmap.good_sell_talkback));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R2.id.talk_all_tv, R2.id.talk_goods_tv, R2.id.talk_norm_tv, R2.id.talk_bad_tv})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.talk_all_tv) {
            type = "0";
            clearDatas();
            mCurrentPage = 1;
            initDatas();
            ClearState();
            talkAllTv.setBackground(getDrawable(R.drawable.round_background_yellow));
        } else if (v.getId() == R.id.talk_goods_tv) {
            type = "1";
            clearDatas();
            mCurrentPage = 1;
            initDatas();
            ClearState();
            talkGoodTv.setBackground(getDrawable(R.drawable.round_background_yellow));
        } else if (v.getId() == R.id.talk_norm_tv) {
            type = "2";
            clearDatas();
            mCurrentPage = 1;
            initDatas();
            ClearState();
            talkNormTv.setBackground(getDrawable(R.drawable.round_background_yellow));
        } else if (v.getId() == R.id.talk_bad_tv) {
            type = "3";
            clearDatas();
            mCurrentPage = 1;
            initDatas();
            ClearState();
            talkBadTv.setBackground(getDrawable(R.drawable.round_background_yellow));
        }

    }

    /**
     * 返回字段	类型	说明
     * goods_evaluate_info	数组	评论统计数组
     * goods_evaluate_info.all	整型	全部评论数
     * goods_evaluate_info.good	整型	好评数
     * goods_evaluate_info.good_percent	字符串	好评比率
     * goods_evaluate_info.normal	整型	中评数
     * goods_evaluate_info.normal_percent	字符串	中评比率
     * goods_evaluate_info.bad	整型	差评数
     * goods_evaluate_info.bad_percent	字符串	差评比率
     * goods_evaluate_info.good_star	整型	平均好评分数
     * goods_evaluate_info.star_average	整型	平均分数
     *
     * @param orderDiscussBean
     * @param isHaveMore
     */
    @Override
    public void onGetDiscussSuccess(OrderDiscussBean orderDiscussBean, boolean isHaveMore) {
        talkAllTv.setText(String.format("全部(%d)", orderDiscussBean.getGoods_evaluate_info().getAll()));
        talkBadTv.setText(String.format("差评(%d）", orderDiscussBean.getGoods_evaluate_info().getBad()));
        talkGoodTv.setText(String.format("好评(%d)", orderDiscussBean.getGoods_evaluate_info().getGood()));
        talkNormTv.setText(String.format("中评(%d)", orderDiscussBean.getGoods_evaluate_info().getNormal()));
        hasMore = isHaveMore;
        if (orderDiscussBean.getGoodsevallist().isEmpty()) {
            if (mCurrentPage == 1) showImgNoData();
        } else {
            stopImgNodata();
            orderDiscussRecyAdapter.appendData(orderDiscussBean.getGoodsevallist());
        }
    }
}
