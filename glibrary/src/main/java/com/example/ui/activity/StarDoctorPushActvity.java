package com.example.ui.activity;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Utils.TextUtil;
import com.example.app.Constants;
import com.example.base.GBaseActivity;
import com.example.bean.DepartmentBean;
import com.example.bean.DoctorListBean;
import com.example.bean.LabelBean;
import com.example.bean.ListBean;
import com.example.base.OnClickRecyclerViewListener;
import com.example.bean.AllAreaBean;
import com.example.bean.BaseBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.mvp.starDoctorPush.StarDoctorPushContract;
import com.example.mvp.starDoctorPush.StarDoctorPushPresenter;
import com.example.ui.adapter.DoctorListAdapter;
import com.example.ui.view.CustomAddressPopup;
import com.example.ui.view.CustomDepartmentPopup;
import com.example.ui.view.ScreenPopup;
import com.example.ui.view.SortPopup;
import com.lxj.xpopup.XPopup;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;


public class StarDoctorPushActvity extends GBaseActivity<StarDoctorPushPresenter> implements StarDoctorPushContract.View, CustomAddressPopup.OnCompleteListener,
        CustomDepartmentPopup.onClick, ScreenPopup.onCompleteListener, SortPopup.onClickSortListener {

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

    @BindView(R2.id.tv_department)
    TextView mTmDepartment;

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
    @BindView(R2.id.tv_site)
    TextView mTvSite;
    @BindView(R2.id.et_keyword)
    EditText mEtKeyWord;
    @BindView(R2.id.ll_no_data)
    LinearLayout mLlNoData;


    //医生列表
    private DoctorListAdapter mAdapter;
    //省市区数据
    private List<ListBean> mDistrictList = new ArrayList<>();

    private CustomAddressPopup mAddressPopupView;
    private String mProvincId;
    private String mCitID;
    private String mKey;
    //科室
    private List<DepartmentBean> mDepartmentList = new ArrayList<>();
    //排序
    private List<LabelBean> sortList = new ArrayList<>();
    //筛选
    private List<LabelBean> mLabelList = new ArrayList<>();
    private CustomDepartmentPopup mCustomDepartmentPopup;
    private ScreenPopup mScreenPopup;
    private SortPopup mSortPopup;

    private int mCurpage = 1;
    //科室
    private String mDepartments = "";

    //咨询排序  1为升序,2为降序
    private int mConsultCount = 0;
    //评价  1为升序,2为降序
    private int mEvaluateScore = 0;
    //
    private String mPosition = "";


    @Override
    public int setLayoutId() {
        return R.layout.star_doctor_push;
    }

    @Override
    public void initOthers() {
        initView();
    }

    private void initView() {
        mPresenter = new StarDoctorPushPresenter();
        mPresenter.attachView(this);
        mKey = AppConstant.KEY;
        //省市区数据
        mPresenter.getAllAreaList();
        mPresenter.getDepartmentList(mKey);
        mPresenter.getDoctorPosition(mKey);

        network();


        //排序数据
        sortList.add(new LabelBean("按咨询量从高到低"));
        sortList.add(new LabelBean("按评分从高到低"));

        mEtKeyWord.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        initToolbar("名医列表");

        mAdapter = new DoctorListAdapter(mContext);
        mRecyView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView.setNestedScrollingEnabled(false);
        //  mAdapter.appendData(data);
        mRecyView.setAdapter(mAdapter);
        initListener();

    }


    private void network() {
        String keyword = mEtKeyWord.getText().toString();
        mPresenter.getDoctorList(mKey, keyword, mPosition, mDepartments, mConsultCount,
                mEvaluateScore, mProvincId, mCitID, mCurpage, Constants.PAGE_SIZE);
    }

    private void initListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                String doctor_id = mAdapter.getDataList().get(position).getDoctor_id();
                toActivity(DoctorInfoActivity.class, "doctorId", doctor_id, "type", "2");
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        //回车不换行
        mEtKeyWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    refresh();
                    TextUtil.closeKeyboard(mActivity);
                    return true;
                }
                return true;
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
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
                        network();
                        mRefreshLayout.finishLoadMore();
                    }
                }, 500);
            }
        });
    }

    /**
     * 刷新数据
     */
    private void refresh() {
        mCurpage = 1;
        mAdapter.emptyData();
        network();
    }

    //

    /**
     * 获取地址 数据
     *
     * @param bean
     */
    @Override
    public void getAllAreaList(AllAreaBean bean) {
        mDistrictList.clear();
        if (bean.getCode() == 200) {
            mDistrictList = bean.getDatas().getList();
            //设置默认选中状态
            mDistrictList.get(0).setChecked(true);
            mDistrictList.get(0).getCitylist().get(0).setChecked(true);
        }
    }

    /**
     * 科室列表
     *
     * @param bean
     */
    @Override
    public void getDepartmentList(BaseBean<List<String>> bean) {
        if (bean.getCode() == 200) {
            mDepartmentList.clear();
            if (bean.getDatas().size() > 0) {
                for (int i = 0; i < bean.getDatas().size(); i++) {
                    DepartmentBean bean1 = new DepartmentBean();
                    if (i == 0) {
                        bean1.setIndexes(0);
                    }
                    bean1.setText(bean.getDatas().get(i));
                    mDepartmentList.add(bean1);
                }
            }
        }
    }

    @Override
    public void getDoctorPosition(BaseBean<List<String>> bean) {
        if (bean.getCode() == 200) {
            mLabelList.clear();
            List<String> datas = bean.getDatas();
            for (int i = 0; i < datas.size(); i++) {
                LabelBean bean1 = new LabelBean();
                bean1.setText(datas.get(i));
                mLabelList.add(bean1);
            }
        }
    }

    /**
     * 列表数据
     *
     * @param bean
     */
    @Override
    public void getDoctorList(DoctorListBean bean) {
        if (bean.getCode() == 200) {
            List<DoctorListBean.DatasBean> datas = bean.getDatas();

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
        log("报错 :" + throwable.getMessage());
    }

    @OnClick({R2.id.ll_nationwide, R2.id.ll_division, R2.id.ll_sales, R2.id.ll_screen})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ll_nationwide) {
            //全国
            if (mDistrictList.size() > 0) {
                mAddressPopupView = new CustomAddressPopup(mContext, mDistrictList);
                mAddressPopupView.setOnCompleteListener(this);
                new XPopup.Builder(mContext)
                        .atView(view)
                        .dismissOnTouchOutside(false)
                        .asCustom(mAddressPopupView)
                        .show();
            }
        } else if (id == R.id.ll_division) {
            //科室
            if (mDepartmentList.size() > 0) {
                mCustomDepartmentPopup = new CustomDepartmentPopup(mContext, mDepartmentList);
                mCustomDepartmentPopup.setOnClick(this);
                new XPopup.Builder(mContext)
                        .dismissOnTouchOutside(false)
                        .atView(view)
                        .asCustom(mCustomDepartmentPopup)
                        .show();
            }
        } else if (id == R.id.ll_sales) {
            //排序
            if (sortList.size() > 0) {
                mSortPopup = new SortPopup(mContext, sortList);
                mSortPopup.setOnClickSortListener(this);
                new XPopup.Builder(mContext)
                        .dismissOnTouchOutside(false)
                        .atView(view)
                        .asCustom(mSortPopup)
                        .show();
            }
        } else if (id == R.id.ll_screen) {
            //筛选
            if (mLabelList.size() > 0) {
                mScreenPopup = new ScreenPopup(mContext, mLabelList);
                mScreenPopup.setOnCompleteListener(this);
                new XPopup.Builder(mContext)
                        .atView(view)
                        .dismissOnTouchOutside(false)
                        .asCustom(mScreenPopup)
                        .show();
            }
        }
    }

    //筛选全国
    @Override
    public void getData(List<ListBean> mData, int leftIndex, int rightIndex) {
        //省ID
        mProvincId = mDistrictList.get(leftIndex).getArea_id();
        //市ID
        mCitID = mDistrictList.get(leftIndex).getCitylist().get(rightIndex).getArea_id();


        mTvSite.setSelected(true);
        mTvSite.setText(mDistrictList.get(leftIndex).getCitylist().get(rightIndex).getArea_name());
        mAddressPopupView.dismiss();

        refresh();
    }

    //筛选科室
    @Override
    public void getPosition(String text) {
        //
        mDepartments = text;

        mTmDepartment.setText(text);
        mTmDepartment.setSelected(true);
        mCustomDepartmentPopup.dismiss();


        refresh();
    }


    //筛选条件
    @Override
    public void getData(String data) {
        mPosition = data;
        mScreenPopup.dismiss();


        refresh();
    }

    //排序
    @Override
    public void getSortData(String text, int position) {
        //position 0 -->咨询量  1--->评分
        mConsultCount = (position == 0 ? 1 : 0);
        mEvaluateScore = (position == 1 ? 1 : 0);


        mBuyTv.setText(text);
        mBuyTv.setSelected(true);
        mSortPopup.dismiss();


        refresh();
    }
}
