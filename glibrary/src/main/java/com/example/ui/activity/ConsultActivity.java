package com.example.ui.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.Utils.TextUtil;
import com.example.app.Constants;
import com.example.base.GBaseActivity;
import com.example.bean.BaseBean;
import com.example.bean.ConsultListBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.mvp.consult.ConsultContract;
import com.example.mvp.consult.ConsultPresenter;
import com.example.ui.adapter.ConsultAdapter1;
import com.example.ui.view.AlterPopup;
import com.lxj.xpopup.XPopup;
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
import butterknife.OnClick;

/**
 * 创建时间 2019/12/20 18:13
 * 描述: 价格咨询
 */
public class ConsultActivity extends GBaseActivity<ConsultPresenter> implements ConsultContract.View {
    @BindView(R2.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.rb_image_text)
    RadioButton mRbImageText;
    @BindView(R2.id.rb_call)
    RadioButton mRbCall;
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


    private ConsultAdapter1 mAdapter;

    private AlterPopup mAlterPopup;
    private String mKey;

    private int mCurpage = 1;

    //咨询类型,1为图文咨询,2为电话咨询
    private int mConsultType = 1;
    //修改 2 /  添加 1
    private int mType;

    private String mDoctorId;
    private String mCPriceId;


    @Override

    public int setLayoutId() {
        return R.layout.acticity_consult;
    }

    @Override
    public void initOthers() {
        mTvTitleRight.setText("添加");
        initToolbar("咨询价格");
        mIvIcon.setVisibility(View.GONE);
        mTvTitleRight.setVisibility(View.VISIBLE);


        mDoctorId = getIntent().getStringExtra("mDoctorId");


        mPresenter = new ConsultPresenter();
        mPresenter.attachView(this);

        mKey = AppConstant.KEY;
        log("nKey-----" + mKey);
        mAdapter = new ConsultAdapter1(mContext);
        mRecyView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView.setAdapter(mAdapter);
        initListener();
        if (getIntent() != null) {
            if (getIntent().getStringExtra("isPhoneTalk") != null) {
                if (getIntent().getStringExtra("isPhoneTalk").equals("true")) {
                    mRbCall.setChecked(true);
                    mRbImageText.setChecked(false);
                }

            } else {
                network();
            }
        }
    }

    private void initListener() {
        mAdapter.setOnAlterClickListener(new ConsultAdapter1.onAlterClickListener() {
            @Override
            public void setAlter(int position) {
                mType = 2;
                //修改
                List<ConsultListBean.DatasBean> dataList = mAdapter.getDataList();
                mCPriceId = dataList.get(position).getC_price_id();


                alterPopup(dataList.get(position));
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
                        mPresenter.getConsultList(mKey, mConsultType, mCurpage, Constants.PAGE_SIZE);
                        mRefreshLayout.finishLoadMore();
                    }
                }, 500);
            }
        });

        mLlRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_image_text) {
                    mConsultType = 1;
                    network();
                } else if (checkedId == R.id.rb_call) {
                    mConsultType = 2;
                    network();
                }
            }
        });

    }


    private void network() {
        mAdapter.emptyData();
        mCurpage = 1;
        mPresenter.getConsultList(mKey, mConsultType, mCurpage, Constants.PAGE_SIZE);
    }

    /**
     * @param bean
     */
    @Override
    public void setConsultPrice(BaseBean bean) {
        if (bean.getCode() == 200) {
            toast(bean.getDatas().toString());
            network();
        } else {
            toast(bean.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param bean
     */
    @Override
    public void getConsultList(ConsultListBean bean) {
        if (bean.getCode() == 200) {
            List<ConsultListBean.DatasBean> datas = bean.getDatas();
            mLlNoData.setVisibility(datas.size() > 0 ? View.GONE : View.VISIBLE);
            mRefreshLayout.setVisibility(datas.size() > 0 ? View.VISIBLE : View.GONE);

            if (!bean.isHasmore()) {
                mRefreshLayout.setNoMoreData(true);
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            }
            mAdapter.appendData(datas);
        }
    }

    /**
     * 修改
     *
     * @param bean
     */
    @Override
    public void modifyConsultPrice(BaseBean bean) {
        if (bean.getCode() == 200) {
            toast(bean.getDatas().toString());
            network();
        } else {
            toast(bean.getMessage());
        }
    }


    @Override
    public void onError(Throwable throwable) {
        log("报错:" + throwable.getMessage());
    }


    @OnClick(R2.id.tv_title_right)
    public void onViewClicked() {
        mType = 1;
        alterPopup(null);
    }

    /**
     * 修改 / 添加 咨询数据
     *
     * @param bena
     */
    private void alterPopup(ConsultListBean.DatasBean bena) {
        // 添加
        if (mAlterPopup == null) {
            mAlterPopup = new AlterPopup(mContext);
            mAlterPopup.setListener(new AlterPopup.onSubmitClickListener() {
                @Override
                public void onSubmitClick(String tiem, String price) {
                    log("tiem==" + tiem + "price=" + price);
                    if (mType == 1) {
                        //医生咨询价格添加
                        mPresenter.setConsultPrice(mKey, mDoctorId, mConsultType, tiem, price);
                    } else {
                        //医生咨询价格修改
                        mPresenter.modifyConsultPrice(mKey, mCPriceId, mConsultType, tiem, price);
                    }
                    TextUtil.closeKeyboard(mActivity);
                }
            });
        }
        mAlterPopup.setType(mType);
        if (mType == 2) {
            //修改数据
            mAlterPopup.setData(bena.getTime(), bena.getPrice());
        }
        new XPopup.Builder(mContext)
                .asCustom(mAlterPopup)
                .show();
    }
}
