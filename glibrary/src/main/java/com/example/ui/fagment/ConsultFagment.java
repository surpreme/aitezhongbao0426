package com.example.ui.fagment;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.Utils.ReadJsonUtils;

//import com.example.ui.adapter.CommentListAdapter;
//import com.example.ui.adapter.ConsultAdapter;
//import com.example.ui.adapter.QuestionAdapter;
import com.example.app.Constants;
import com.example.base.GBaseFragment;
import com.example.base.OnClickRecyclerViewListener;
import com.example.bean.BaseBean;
import com.example.bean.ConsultBean;
import com.example.bean.DoctorInfoBean;
import com.example.bean.IMInfoBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
//import com.example.ui.view.ConventionPopup;
//import com.example.ui.view.UnopenPopup;
import com.example.mvp.ConsultFagment.ConsultFagmentContract;
import com.example.mvp.ConsultFagment.ConsultFagmentPresenter;
import com.example.ui.activity.ConsultActivity;
import com.example.ui.activity.DoctorInfoActivity;
import com.example.ui.activity.GChatActivity;
import com.example.ui.adapter.CommentListAdapter;
import com.example.ui.adapter.ConsultAdapter;
import com.example.ui.adapter.QuestionAdapter;
import com.example.ui.view.ConventionPopup;
import com.example.ui.view.UnopenPopup;
import com.lxj.xpopup.XPopup;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.util.CallUtils;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

/**
 * 创建时间 2019/12/11 18:55
 * 描述: 咨询
 */
public class ConsultFagment extends GBaseFragment<ConsultFagmentPresenter> implements ConsultFagmentContract.View {


    @BindView(R2.id.recy_view1)
    RecyclerView mRecyView1;
    @BindView(R2.id.recy_view2)
    RecyclerView mRecyView2;
    @BindView(R2.id.tv_comment_num)
    TextView mTvCommentNum;
    @BindView(R2.id.recy_view3)
    RecyclerView mRecyView3;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    //咨询模块
    private ConsultAdapter mConsultAdapter;
    //经典问题
    private QuestionAdapter mQuestionAdapter;
    //点评
    private CommentListAdapter mCommentListAdapter;

    private ConventionPopup mConventionPopup;

    private String mConsultPrice;
    private String mConsultTime;
    private String mPConsultPrice;
    private String mPConsultTime;
    private String mPConsultPhone = "";
    private int mType;
    private String mDoctorId;
    private String mMemberId;
    private String mName;
    private IMInfoBean.DatasBean mImAccountInfo;
    private DoctorInfoBean doctorInfoBean;
    //
    private int mConsulrType;


    @Override
    public int setLayoutId() {
        return R.layout.fragment_consult;
    }

    @Override
    public void initOthers() {
        mPresenter = new ConsultFagmentPresenter();
        mPresenter.attachView(this);

        /*-----咨询类型-----*/
        mConsultAdapter = new ConsultAdapter(mContext);
        mRecyView1.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecyView1.setNestedScrollingEnabled(false);
        mRecyView1.setAdapter(mConsultAdapter);


        /*--------经典问题--------*/
        mQuestionAdapter = new QuestionAdapter(mContext);
        mRecyView2.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView2.setNestedScrollingEnabled(false);
        mRecyView2.setAdapter(mQuestionAdapter);


        /*---------点评--------*/
        mCommentListAdapter = new CommentListAdapter(mContext);
        mRecyView3.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView3.setNestedScrollingEnabled(false);
        mRecyView3.setAdapter(mCommentListAdapter);


        String mKey = AppConstant.KEY;


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
            log("mMemberId=" + mMemberId);
        } else {
            mName = ((DoctorInfoActivity) mActivity).mMemberTruename;
            mDoctorId = ((DoctorInfoActivity) mActivity).mDoctorId;
            mPresenter.getDoctorInfo(mKey, mDoctorId);
            log("mDoctorId=" + mDoctorId);

//            //IM
//            mPresenter.getIMinfo(mKey, Constants.LANG, mMemberId);
            log("mDoctorId=" + mDoctorId);
        }
        initListener();
    }


    private void initListener() {
        mConsultAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        //用户类型  1医生  2用户 mType
                        if (mType != 1) {
                            mConsulrType = 1;
                            //图文咨询
                            goConsult();
                        } else {
                            //医生本人
                            Intent intent = new Intent(ConsultFagment.this.getActivity(), ConsultActivity.class);
                            intent.putExtra("mDoctorId", AppConstant.DOCTORID);
                            startActivity(intent);

                        }
                        break;
                    case 1:
                        if (mType != 1) {
                            mConsulrType = 2;
                            //电话咨询
                            goTelephone();
                        } else {
                            //医生本人
                            Intent intent = new Intent(ConsultFagment.this.getActivity(), ConsultActivity.class);
                            intent.putExtra("mDoctorId", AppConstant.DOCTORID);
                            intent.putExtra("isPhoneTalk", "true");
                            startActivity(intent);

                        }
                        break;
                    case 2:
                        //视频咨询
                        new XPopup.Builder(mContext)
                                .asCustom(new UnopenPopup(mContext, "该板块暂未开放"))
                                .show();
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }


    @Override
    public void onError(Throwable throwable) {
        log("报错 :" + throwable.toString());
    }

    @Override
    public void getDoctorInfo(BaseBean<DoctorInfoBean> bean) {
        if (bean.getCode() == 200) {
            //咨询
            doctorInfoBean = bean.getDatas();
            initData(doctorInfoBean);
            //IM
            mPresenter.getIMinfo(AppConstant.KEY, Constants.LANG, doctorInfoBean.getMember_id());
        }

    }

    @Override
    public void getIMinfo(IMInfoBean bean) {
        if (bean.getError_code() == 0) {
            mImAccountInfo = bean.getDatas();
        }
    }

    private void initData(DoctorInfoBean bean) {
        //图文咨询
        mConsultPrice = bean.getConsult_price();
        if (mConsultPrice.equals("null")) mConsultPrice = "";
        mConsultTime = bean.getConsult_time();
        if (mConsultTime.equals("null")) mConsultTime = "";
        if (bean.getMember_name() != null)
            mPConsultPhone = bean.getMember_name();
//            mPConsultPhone = bean.getMember_mobile();
        //电话咨询
        mPConsultPrice = bean.getP_consult_price();
        if (mPConsultPrice.equals("null")) mPConsultPrice = "";
        mPConsultTime = bean.getP_consult_time();
        if (mPConsultTime.equals("null")) mPConsultTime = "";

        List<ConsultBean> bean1 = ReadJsonUtils.getBean(ReadJsonUtils.getJson(mContext, "text1.json"), "consult_icon");
        //todo 设置咨询价格

        if (!TextUtils.isEmpty(mConsultTime) && !TextUtils.isEmpty(mPConsultTime)) {
            if (!mConsultTime.equals("null") && !mPConsultTime.equals("null")) {
                if (mPConsultPrice.equals("null")) return;
                bean1.get(0).setPrice(mConsultPrice + "元/" + getString(R.string.g_minute, Integer.valueOf(mConsultTime)));
                bean1.get(1).setPrice(mPConsultPrice + "元/" + getString(R.string.g_minute, Integer.valueOf(mPConsultTime)));
            }

        }

        /**--------评价列表----------**/
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String s = "";
            dataList.add(s);
        }

        mConsultAdapter.appendData(bean1);
        mQuestionAdapter.appendData(dataList);
        mCommentListAdapter.appendData(dataList);
    }


    private void goTelephone() {
        if (mConventionPopup == null) {
            mConventionPopup = new ConventionPopup(mContext, "是否同意医生调取健康档案", "拒绝", "同意");
            mConventionPopup.setOnBut1ClickListener(new ConventionPopup.onBut1ClickListener() {
                @Override
                public void onBut1ClickListener() {
                    mConventionPopup.dismiss();
                }
            });
            mConventionPopup.setOnBut2ClickListener(new ConventionPopup.onBut2ClickListener() {
                @Override
                public void onBut2ClickListener() {
                    //同意
//                    goConsult();
                    if (mPConsultPhone == null || mPConsultPhone.equals("") || mPConsultPhone.equals("null")) {
                        ToastUtils.showToast(mContext, "暂无手机号码");
                        mConventionPopup.dismiss();
                        return;
                    }
//                    if (getActivity() != null && !mPConsultPhone.equals(""))
                    CallUtils.callkeyboredPhone(mPConsultPhone, getActivity());
                    mConventionPopup.dismiss();
                }
            });
        }
        new XPopup.Builder(mContext)
                .asCustom(mConventionPopup)
                .show();
    }


    private void goConsult() {


        //IM
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setId(mImAccountInfo.getIdentifier());
//        chatInfo.setId(doctorInfoBean.getMember_id());
        chatInfo.setChatName(mName);
        chatInfo.setType(TIMConversationType.C2C);

//        chatInfo.setConsulrType(mConsulrType);
//        chatInfo.setDoctorId(mDoctorId);
        Intent intent = new Intent(mContext, GChatActivity.class);
        intent.putExtra("type", mConsulrType);
        intent.putExtra("mDoctorId", mDoctorId);
        intent.putExtra("chatInfo", chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}