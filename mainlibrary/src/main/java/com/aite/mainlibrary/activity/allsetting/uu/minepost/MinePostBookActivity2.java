package com.aite.mainlibrary.activity.allsetting.uu.minepost;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.aite.mainlibrary.Mainbean.MineTogetherServiceBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allqr.qrcode.QrCodeActivity;
import com.aite.mainlibrary.activity.allsetting.helpdocotorinformationbook.PostInformationBookActivity;
import com.aite.mainlibrary.activity.allshopcard.talkbook.TalkBookActivity;
import com.aite.mainlibrary.adapter.PostServiceBookRecyAdapter;
import com.google.android.material.tabs.TabLayout;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-05
 * @desc:
 */
public class MinePostBookActivity2 extends BaseActivity<MinePostBookChriendContract.View, MinePostBookChriendPresenter> implements MinePostBookChriendContract.View {
    @BindView(R2.id.tablayout)
    TabLayout tablayout;
    private String mType = "1";
    private PostServiceBookRecyAdapter postServiceBookRecyAdapter;
    private List<MineTogetherServiceBean.ListBean> minelistbean = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_minepost;
    }

    /**
     * startActivity(QrCodeActivity.class, "type", mType);
     */
    @Override
    protected void initView() {
        if (getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY")) {
            initToolbar("我参与的活动", "核销", v ->
                    PopwindowUtils.getmInstance().showLookImagePopupWindow(context, position -> {
                        startActivity(QrCodeActivity.class, "type", String.valueOf(position));
                        PopwindowUtils.getmInstance().dismissPopWindow();
                    }));
        } else {
            initToolbar("我的发布", "核销", v ->
                    PopwindowUtils.getmInstance().showLookImagePopupWindow(context, position -> {
                        startActivity(QrCodeActivity.class, "type", String.valueOf(position));
                        PopwindowUtils.getmInstance().dismissPopWindow();
                    }));
        }
        tablayout.addTab(tablayout.newTab().setText("时间银行"));
        tablayout.addTab(tablayout.newTab().setText("助医服务"));
        tablayout.addTab(tablayout.newTab().setText("喘息服务"));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    mType = "1";
                } else if (tab.getPosition() == 1) {
                    mType = "2";
                } else if (tab.getPosition() == 2) {
                    mType = "3";
                }
                mCurrentPage = 1;
                onSmartRefresh();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        initRecy();
        //smartlayout
        initSmartLayout(true);
        //初始化加载
        initLoadingAnima();
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBaserecyclerView.setAdapter(postServiceBookRecyAdapter = new PostServiceBookRecyAdapter(context, minelistbean));
        postServiceBookRecyAdapter.setWriteOffInterface((qrUrl, tilte) -> {
            PopwindowUtils.getmInstance().showImgPopupWindow(context, qrUrl, tilte, () -> {
                onSmartRefresh();
            });


        });
        postServiceBookRecyAdapter.setUnUsedInterface(new PostServiceBookRecyAdapter.UnUsedInterface() {
            @Override
            public void onUnUsed(String number) {
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, "您确定要取消此服务吗?", v -> {
                    if (mType.equals("1"))
                        mPresenter.cancelBook(AppConstant.CANCELTIMEBANKSERVICEURL, initListHttpParams(true, new ContentValue("id", minelistbean.get(Integer.parseInt(number)).getId())));
                    if (mType.equals("2"))
                        mPresenter.cancelBook(AppConstant.CANCELHELPDOCTORSERVICEURL, initListHttpParams(true, new ContentValue("id", minelistbean.get(Integer.parseInt(number)).getId())));
                    if (mType.equals("3"))
                        mPresenter.cancelBook(AppConstant.CANCELAIRSERVICEURL, initListHttpParams(true, new ContentValue("id", minelistbean.get(Integer.parseInt(number)).getId())));
                    PopwindowUtils.getmInstance().dismissPopWindow();
                    onBackPressed();

                });


            }
        });
        postServiceBookRecyAdapter.setTalkedUsedInterface(new PostServiceBookRecyAdapter.TalkedUsedInterface() {
            @Override
            public void onTalkedUsed(String number) {
                startActivity(TalkBookActivity.class, "id", minelistbean.get(Integer.parseInt(number)).getId());

            }
        });
        postServiceBookRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                Bundle bundle = new Bundle();
                if (mType.equals("1")) {
                    bundle.putString("id", String.valueOf(minelistbean.get(postion).getId()));
                    bundle.putString("chriendtype", !getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY") ?
                            AppConstant.POSTTIMEBANKINFORMATIONMINETOGETHERURL :
                            AppConstant.TIMEBANKINFORMATIONMINETOGETHERURL);
                    bundle.putString("tb_id", minelistbean.get(postion).getTb_id());
                } else if (mType.equals("2")) {
                    bundle.putString("id", String.valueOf(minelistbean.get(postion).getId()));
                    bundle.putString("chriendtype", !getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY") ?
                            AppConstant.POSTHELPDOCTORINFORMATIONMINETOGETHERURL :
                            AppConstant.HELPDOCTORINFORMATIONMINETOGETHERURL);
                    bundle.putString("tb_id", minelistbean.get(postion).getTb_id());
                } else if (mType.equals("3")) {
                    bundle.putString("id", String.valueOf(minelistbean.get(postion).getId()));
                    bundle.putString("chriendtype", !getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY") ?
                            AppConstant.POSTAIRSERVICEINFORMATIONMINETOGETHERURL :
                            AppConstant.AIRSERVICEINFORMATIONMINETOGETHERURL);
                    bundle.putString("tb_id", minelistbean.get(postion).getTb_id());
                }
                bundle.putString("type", mType);
                startActivity(PostInformationBookActivity.class, bundle);
            }
        });
    }

    @Override
    protected void initDatas() {
        if (mType.equals("1")) {
            mPresenter.getListInformation(!getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY") ?
                    AppConstant.POSTTIMEBANKMINETOGETHERLISTURL :
                    AppConstant.TIMEBANKMINETOGETHERLISTURL, initParams());
        } else if (mType.equals("2"))
            mPresenter.getListInformation(!getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY") ?
                    AppConstant.POSTHELPDOCTORMINETOGETHERLISTURL :
                    AppConstant.HELPDOCTORMINETOGETHERLISTURL, initParams());
        else if (mType.equals("3"))
            mPresenter.getListInformation(!getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY") ?
                    AppConstant.POSTAIRSERVICEMINETOGETHERLISTURL :
                    AppConstant.AIRSERVICEMINETOGETHERLISTURL, initParams());


    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        initDatas();

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        if (minelistbean != null) {
            minelistbean.clear();
            postServiceBookRecyAdapter.notifyDataSetChanged();
        }
        initDatas();

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    //get
    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        //当前页码
        httpParams.put("curpage", mCurrentPage);
        return httpParams;
    }

    @Override
    public void onGetListInformationSuccess(Object msg) {
        if (((MineTogetherServiceBean) msg).getList().isEmpty()) {
            initNodata();
        } else {
            stopLoadingAnim();
            showMoreRecy();
            stopNoData();
            minelistbean.addAll(((MineTogetherServiceBean) msg).getList());
            postServiceBookRecyAdapter.notifyDataSetChanged();
            hasMore = ((MineTogetherServiceBean) msg).getIs_nextpage() > 0;
        }
    }

    @Override
    public void onCancelBookSuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean != null && twoSuccessCodeBean.getResult().equals("1")) {
            showToast(twoSuccessCodeBean.getMsg());
        }

    }
}
