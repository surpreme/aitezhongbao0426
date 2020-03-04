package com.example.ui.fagment;

import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.aite.amaplibrary.fragment.BaseAmapSelectionsActivity;
import com.example.base.OnClickRecyclerViewListener;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.bean.DoctorWorkAdressBean;
import com.example.mvp.homepage.HomepageContract;
import com.example.mvp.homepage.HomepagePresenter;
import com.example.ui.activity.AmapSelectionsActivity;
import com.example.ui.activity.DoctorInfoActivity;
import com.example.ui.adapter.CommentListAdapter;
import com.example.ui.adapter.HospitalListsAdapter;
import com.example.base.GBaseFragment;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.ui.view.FlowLayout;
import com.example.Utils.TextUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseGaoDeLocationViewActivity;
import com.lzy.basemodule.bean.BeanConvertor;
import com.lzy.basemodule.bean.ContentValue;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

//import com.aite.mainlibrary.R;
//import com.aite.mainlibrary.R2;

/**
 * 创建时间 2019/12/11 18:54
 * 描述:  主页
 */
public class HomepageFagment extends GBaseFragment<HomepagePresenter> implements HomepageContract.View {


    @BindView(R2.id.tv_site)
    TextView mTvSite;
    @BindView(R2.id.recy_view1)
    RecyclerView mRecyView1;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.tv_evaluate_num)
    TextView mTvEvaluateNum;
    @BindView(R2.id.flow_layout)
    FlowLayout mFlowLayout;
    @BindView(R2.id.recy_view2)
    RecyclerView mRecyView2;

    @BindView(R2.id.tv_introduce)
    TextView mTvIntroduce;

    private String mDoctorId;

    private String mMemberId;

    private String mKey;

    private HospitalListsAdapter mAdapter;

    private CommentListAdapter mCommentListAdapter;
    private int mType;
    private List<DoctorWorkAdressBean> mDoctorWorkAdressBeanList;


    @Override
    public int setLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void initOthers() {
        mPresenter = new HomepagePresenter();
        mPresenter.attachView(this);

        mAdapter = new HospitalListsAdapter(mContext);
        mRecyView1.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView1.setNestedScrollingEnabled(false);
        mRecyView1.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                if (mDoctorWorkAdressBeanList.get(position).getLocation() != null) {
                    String[] location = mDoctorWorkAdressBeanList.get(position).getLocation().split(",");
                    Intent intent = new Intent(getActivity(), BaseGaoDeLocationViewActivity.class);
                    intent.putExtra("title", mDoctorWorkAdressBeanList.get(position).getTitle());
                    intent.putExtra("latitude", location[0]);
                    intent.putExtra("longitude", location[1]);
                    startActivity(intent);
//                    startActivityWithCls(
//                            BaseGaoDeLocationViewActivity.class,
//                            0,
//                            new ContentValue("title", mDoctorWorkAdressBeanList.get(position).getTitle()),
//                            new ContentValue("latitude",location[0]),
//                            new ContentValue("longitude", location[1])
//                    );
                }

//                toActivity(Location.class);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
        mKey = AppConstant.KEY;

        assert getParentFragment() != null;

        // todo mType 处理
        try {
            mType = ((DoctorInfoActivity) mActivity).mType;
        } catch (Exception e) {
            mType = ((DoctorInfoFragment) getParentFragment()).mType;
        }


        if (mType == 1) {
            mMemberId = AppConstant.MEMBER_ID;
            mPresenter.getDoctorInfo1(mKey, mMemberId);
        } else {
            mDoctorId = ((DoctorInfoActivity) mActivity).mDoctorId;
            mPresenter.getDoctorInfo(mKey, mDoctorId);
        }

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            data.add("慢性腹泻45");
        }


        //流布局 数据
        loadDataToTypeSearchList(data);


        /**--------评价列表----------**/
//        List<String> dataList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            String s = "222222";
//            dataList.add(s);
//        }
        mCommentListAdapter = new CommentListAdapter(mContext);
        mRecyView2.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView2.setNestedScrollingEnabled(false);
        //    mCommentListAdapter.appendData(dataList);
        mRecyView2.setAdapter(mCommentListAdapter);
    }

    @Override
    public void getDoctorInfo(BaseBean<DoctorInfoBean> bean) {
        if (bean.getCode() == 200) {
            initView(bean);
        }
    }


    @Override
    public void onError(Throwable throwable) {

    }

    private void initView(BaseBean<DoctorInfoBean> bean) {
        //地址
        if (bean.getDatas().getLongitude() != null && !bean.getDatas().getLongitude().equals("null")) {
            List<String> adept = new ArrayList<>();
            mDoctorWorkAdressBeanList = new Gson().fromJson(bean.getDatas().getLongitude(), new TypeToken<List<DoctorWorkAdressBean>>() {
            }.getType());
            for (int i = 0; i < mDoctorWorkAdressBeanList.size(); i++) {
                DoctorWorkAdressBean doctorWorkAdressBean = mDoctorWorkAdressBeanList.get(i);
                adept.add(doctorWorkAdressBean.getTitle());
            }
            mAdapter.appendData(adept);

        }

//           =bean.getDatas().getWork_address();

        //医生介绍
        mTvIntroduce.setText(bean.getDatas().getIntroduce());

    }


    private void loadDataToTypeSearchList(List<String> searchList) {
        for (final String searchKeyword : searchList) {
            TextView textView = new TextView(mContext);
            textView.setText(TextUtil.highlight(mContext, searchKeyword, "45", "#FFB400", 0, 0));
            textView.setBackgroundResource(R.drawable.search_bg_item);
            textView.setGravity(Gravity.CENTER);
            int padding2 = getResources().getDimensionPixelSize(R.dimen.dp_2);
            int padding8 = getResources().getDimensionPixelSize(R.dimen.dp_8);
            textView.setPadding(padding8, padding2, padding8, padding2);
            textView.setTextColor(mContext.getResources().getColor(R.color.text1));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///getSearch(searchKeyword);
                }
            });
            mFlowLayout.addView(textView);
        }
    }


}