package com.aite.mainlibrary.activity.allsetting.uu.minepostbookchriend;


import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.MineTogetherServiceBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allqr.qrcode.QrCodeActivity;
import com.aite.mainlibrary.activity.allsetting.helpdocotorinformationbook.PostInformationBookActivity;
import com.aite.mainlibrary.activity.allshopcard.talkbook.TalkBookActivity;
import com.aite.mainlibrary.adapter.PostServiceBookRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MinePostBookChriendFragment extends BaseFragment<MinePostBookChriendContract.View, MinePostBookChriendPresenter> implements MinePostBookChriendContract.View {
    private PostServiceBookRecyAdapter postServiceBookRecyAdapter;
    private List<MineTogetherServiceBean.ListBean> minelistbean = new ArrayList<>();
    private String mType = "1";
    private String mUrl = "1";
    private String mCometype = "MINEGETBOOKACTIVITY";

    @Override
    protected void initModel() {
        if (mType.equals("1"))
            mPresenter.getListInformation(!mCometype.equals("MINEGETBOOKACTIVITY") ?
                    AppConstant.POSTTIMEBANKMINETOGETHERLISTURL :
                    AppConstant.TIMEBANKMINETOGETHERLISTURL, initParams());
        else if (mType.equals("2"))
            mPresenter.getListInformation(!mCometype.equals("MINEGETBOOKACTIVITY") ?
                    AppConstant.POSTHELPDOCTORMINETOGETHERLISTURL :
                    AppConstant.HELPDOCTORMINETOGETHERLISTURL, initParams());
        else if (mType.equals("3"))
            mPresenter.getListInformation(!mCometype.equals("MINEGETBOOKACTIVITY") ?
                    AppConstant.POSTAIRSERVICEMINETOGETHERLISTURL :
                    AppConstant.AIRSERVICEMINETOGETHERLISTURL, initParams());
    }

    @Override
    protected void initViews() {
        initMoreRecy();
        //smartlayout
        initSmartLayout(true);
        //初始化加载
        initLoadingAnima();
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBaserecyclerView.setAdapter(postServiceBookRecyAdapter = new PostServiceBookRecyAdapter(context, minelistbean));
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
                    bundle.putString("chriendtype", !mCometype.equals("MINEGETBOOKACTIVITY") ?
                            AppConstant.POSTTIMEBANKINFORMATIONMINETOGETHERURL :
                            AppConstant.TIMEBANKINFORMATIONMINETOGETHERURL);
                    bundle.putString("tb_id", minelistbean.get(postion).getTb_id());
                } else if (mType.equals("2")) {
                    bundle.putString("id", String.valueOf(minelistbean.get(postion).getId()));
                    bundle.putString("chriendtype", !mCometype.equals("MINEGETBOOKACTIVITY") ?
                            AppConstant.POSTHELPDOCTORINFORMATIONMINETOGETHERURL :
                            AppConstant.HELPDOCTORINFORMATIONMINETOGETHERURL);
                    bundle.putString("tb_id", minelistbean.get(postion).getTb_id());
                }
//                startActivity(
//                        PostInformationBookActivity.class,
//                        "id",
//                        String.valueOf(minelistbean.get(postion).getId()),
//                        "chriendtype",
//                        !getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY") ?
//                                AppConstant.POSTHELPDOCTORINFORMATIONMINETOGETHERURL :
//                                AppConstant.HELPDOCTORINFORMATIONMINETOGETHERURL);
                else if (mType.equals("3")) {
                    bundle.putString("id", String.valueOf(minelistbean.get(postion).getId()));
                    bundle.putString("chriendtype", !mCometype.equals("MINEGETBOOKACTIVITY") ?
                            AppConstant.POSTAIRSERVICEINFORMATIONMINETOGETHERURL :
                            AppConstant.AIRSERVICEINFORMATIONMINETOGETHERURL);
                    bundle.putString("tb_id", minelistbean.get(postion).getTb_id());
                }
//                startActivity(
//                        PostInformationBookActivity.class,
//                        "id",
//                        String.valueOf(minelistbean.get(postion).getId()),
//                        "chriendtype",
//                        !getIntent().getStringExtra("COMETYPE").equals("MINEGETBOOKACTIVITY") ?
//                                AppConstant.POSTAIRSERVICEINFORMATIONMINETOGETHERURL :
//                                AppConstant.AIRSERVICEINFORMATIONMINETOGETHERURL);
                bundle.putString("type", mType);
                startActivity(PostInformationBookActivity.class, bundle);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.smart_recy_right_toolbar;
    }



    //get
    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
//        //页面类型 1日托 2培训 3就业 4助残活动 5其他服务  必须
//        httpParams.put("page_type", getIntent().getStringExtra("type"));
        //当前页码
        httpParams.put("curpage", mCurrentPage);
        return httpParams;
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        initModel();

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        if (minelistbean != null) {
            minelistbean.clear();
            postServiceBookRecyAdapter.notifyDataSetChanged();
        }
        initModel();

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
//        minelistbean.addAll(((MineTogetherServiceBean) msg).getList());
//        postServiceBookRecyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelBookSuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean != null && twoSuccessCodeBean.getResult().equals("1")) {
            showToast(twoSuccessCodeBean.getMsg());
        }

    }

    @Override
    public void onClick(View v) {

    }
}
