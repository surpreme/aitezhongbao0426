package com.aite.mainlibrary.activity.alldoctor;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.DoctorListAdapter;
import com.aite.mainlibrary.base.OnClickRecyclerViewListener;
import com.lzy.basemodule.mvp2.GBaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StarDoctorPushActvity extends GBaseActivity {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.ll_nationwide)
    LinearLayout mLlNationwide;
    @BindView(R2.id.ll_division)
    LinearLayout mLlDivision;
    @BindView(R2.id.buy_tv)
    TextView mBuyTv;
    @BindView(R2.id.buy_top_img)
    ImageView mBuyTopImg;
    @BindView(R2.id.buy_choice_ll)
    LinearLayout mBuyChoiceLl;
    @BindView(R2.id.ll_sales)
    LinearLayout mLlSales;
    @BindView(R2.id.away_tv)
    TextView mAwayTv;
    @BindView(R2.id.away_top_img)
    ImageView mAwayTopImg;
    @BindView(R2.id.away_choice_ll)
    LinearLayout mAwayChoiceLl;
    @BindView(R2.id.ll_screen)
    LinearLayout mLlScreen;
    @BindView(R2.id.recy_view)
    RecyclerView mRecyView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    //医生列表
    private DoctorListAdapter mAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.star_doctor_push;
    }

    @Override
    public void initOthers() {
        initToolbar("名医列表");
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("111");
        }
        mAdapter = new DoctorListAdapter(mContext);
        mRecyView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView.setNestedScrollingEnabled(false);
        mAdapter.appendData(data);
        mRecyView.setAdapter(mAdapter);
        initListener();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }



    private void initListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                toActivity(DoctorInfoActivity.class);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }




    @OnClick({R2.id.ll_nationwide, R2.id.ll_division, R2.id.ll_sales, R2.id.ll_screen})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ll_nationwide) {
            //全国

        } else if (id == R.id.ll_division) {
            //科室

        } else if (id == R.id.ll_sales) {
            //销量

        } else if (id == R.id.ll_screen) {
            //筛选

        }

    }



}
