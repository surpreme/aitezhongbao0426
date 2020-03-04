package com.example.ui.activity;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.Utils.TextUtil;
import com.example.app.Constants;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.bean.IMInfoBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.im.IMUtils;
import com.example.mvp.doctorInfo.DoctorInfoContract;
import com.example.mvp.doctorInfo.DoctorInfoPresenter;
import com.example.ui.fagment.AppointmentFagment;
import com.example.ui.fagment.ConsultFagment;
import com.example.ui.fagment.HomepageFagment;
import com.example.ui.view.FlowLayout;
import com.example.ui.view.MorePopup;
import com.example.ui.view.UnopenPopup;
import com.lxj.xpopup.XPopup;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.view.RatingBarView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 医生信息
 */
public class DoctorInfoActivity extends BaseGIMActivity<DoctorInfoPresenter> implements DoctorInfoContract.View, MorePopup.onClickListeners {

    @BindView(R2.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.tv_position)
    TextView mTvPosition;
    @BindView(R2.id.tv_hospital)
    TextView mTvHospital;
    @BindView(R2.id.starView)
    RatingBarView mStarView;
    @BindView(R2.id.tv_grade1)
    TextView mTvGrade1;
    @BindView(R2.id.user_icon)
    CircleImageView mUserIcon;
    @BindView(R2.id.tv_grade)
    TextView mTvGrade;
    @BindView(R2.id.tv_subscribe_num)
    TextView mTvSubscribeNum;
    @BindView(R2.id.tv_consult_num)
    TextView mTvConsultNum;
    @BindView(R2.id.tv_reply_num)
    TextView mTvReplyNum;
    @BindView(R2.id.tv_text)
    TextView mTvText;
    @BindView(R2.id.tv_morbidity_type)
    TextView mTvMorbidityType;
    @BindView(R2.id.iv_more)
    ImageView mIvMore;
    @BindView(R2.id.tv_content)
    TextView mTvContent;
    @BindView(R2.id.tv_homepage)
    TextView mTvHomepage;
    @BindView(R2.id.tv_consult)
    TextView mTvConsult;
    @BindView(R2.id.tv_appointment)
    TextView mTvAppointment;
    @BindView(R2.id.fragment_vp)
    FrameLayout mFragmentVp;
    @BindView(R2.id.flow_Layout)
    FlowLayout mFlowLayout;

    private int currentIndex = -1;

    private Fragment[] mFragments = new Fragment[3];

    private Fragment mHomepageFragment, mConsultFragment, mAppointmentFragment;

    public String mDoctorId;
    private String mKey;
    private DoctorInfoBean mDatas;
    //用户类型  1医生  2用户
    public int mType = 2;
    private MorePopup mMorePopup;
    public String mMemberId;
    public String mMemberTruename;
    private boolean mIsCollectDocotor = false;


    @Override
    public int setLayoutId() {
        return R.layout.maindoctor_information;
    }

    @Override
    public void initOthers() {
        super.initOthers();
        mPresenter = new DoctorInfoPresenter();
        mPresenter.attachView(this);
        mKey = AppConstant.KEY;
        log("mKey=" + mKey);
        // mType = Integer.valueOf(Objects.requireNonNull(getIntent().getStringExtra("type")));
//        if (mType == 1) {
//            mMemberId = getIntent().getStringExtra("memberId");
//            mPresenter.getDoctorInfo1(mKey, mMemberId);
//            log("mMemberId=" + mMemberId);
//        } else {
        mDoctorId = getIntent().getStringExtra("doctorId");
        mPresenter.getDoctorInfo(mKey, mDoctorId);
        //IM
        mPresenter.getIMinfo(mKey, Constants.LANG, AppConstant.MEMBER_ID);
        log("mDoctorId=" + mDoctorId);
//        }

        showFragment(0);

        mIvIcon.setVisibility(View.VISIBLE);
        mTvTitleRight.setVisibility(View.GONE);
        if (mType == 1) {
            mIvIcon.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.user_icon));
        } else if (mType == 2) {
            mIvIcon.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.upcollect_icon));
        }


    }


    @Override
    public void getDoctorInfo(BaseBean<DoctorInfoBean> bean) {
        if (bean.getCode() == 200) {
            mDatas = bean.getDatas();
            initView(mDatas);
        }
    }

    /**
     * IM配置
     *
     * @param bean
     */
    @Override
    public void getIMinfo(IMInfoBean bean) {
        if (bean.getError_code() == 0) {
            IMInfoBean.DatasBean imAccountInfo = bean.getDatas();
            IMUtils.loginToIM(imAccountInfo.getIdentifier(), imAccountInfo.getUserSig());
        }
    }

    @Override
    public void onCollectDoctorSuccess(String bean) {
        mIvIcon.setImageDrawable(getResources().getDrawable(R.drawable.collect_doctor_red));
        mIsCollectDocotor = true;

    }

    @Override
    public void onCancelCollectDoctorSuccess(String bean) {
        mIvIcon.setImageDrawable(getResources().getDrawable(R.mipmap.upcollect_icon));
        mIsCollectDocotor = false;

    }

    @Override
    public void onError(Throwable throwable) {
        log("报错:" + throwable.toString());
    }

    /**
     * 数据初始化
     *
     * @param datas
     */
    private void initView(DoctorInfoBean datas) {
        mDoctorId = datas.getDoctor_id();
        AppConstant.DOCTOR_ID = mDoctorId;
        initToolbar("个人主页-" + datas.getMember_truename());
        //名称
        mMemberTruename = datas.getMember_truename();
        mTvName.setText(mMemberTruename);
        //职位
        switch (datas.getPosition()) {
            case "0":
                mTvPosition.setText(Constants.DoctorType1);
                break;
            case "1":
                mTvPosition.setText(Constants.DoctorType2);
                break;
            case "2":
                mTvPosition.setText(Constants.DoctorType3);
                break;
            case "3":
                mTvPosition.setText(Constants.DoctorType4);
                break;
        }

        //执业地址
        List<String> work_address = datas.getWork_address();
        if (work_address.size() > 0) {
            mTvHospital.setText("第一执业" + work_address.get(0));
        }
        //评分 evaluate_score
        mStarView.setStar(datas.getEvaluate_count());
        mTvGrade1.setText(String.valueOf(datas.getEvaluate_count()));
        //粉丝 collection_count
        mTvGrade.setText(TextUtil.highlight(mContext, "粉丝" + datas.getCollection_count(), String.valueOf(datas.getCollection_count()), "#F4EA2A", 0, 0));
        //咨询量  consult_count
        mTvSubscribeNum.setText(TextUtil.highlight(mContext, "咨询量" + datas.getConsult_count(), datas.getConsult_count(), "#F4EA2A", 0, 0));
        //预约数  todo
        mTvConsultNum.setText(TextUtil.highlight(mContext, "预约数99+", "99+", "#F4EA2A", 0, 0));
        //回复 todo
        mTvReplyNum.setText(TextUtil.highlight(mContext, "回复速度9.9", "9.9", "#F4EA2A", 0, 0));
        //擅长
        mTvMorbidityType.setVisibility(datas.getAdept().size() > 0 ? View.VISIBLE : View.GONE);
        if (datas.getAdept().size() > 0) {
//            mTvMorbidityType.setText(datas.getAdept().get(0));
            for (int i = 0; i < datas.getAdept().size(); i++) {
                addLabel(datas.getAdept().get(i));
            }
        }
        //介绍 introduce
        mTvContent.setText(datas.getIntroduce());
        mIvMore.setTag(datas.getIntroduce());
        if (mTvContent.getLineCount() > 3) {
            mTvContent.setMaxLines(3);
            int lineEndIndex = mTvContent.getLayout().getLineEnd(2);
            String text = mTvContent.getText().subSequence(0, lineEndIndex - 4) + "...";
            mTvContent.setText(text);
            mIvMore.setOnClickListener(v -> {
                if (mTvContent.getLineCount() > 3) {
                    mTvContent.setMaxLines(3);
                    int lineEndIndexs = mTvContent.getLayout().getLineEnd(2);
                    String tt = mTvContent.getText().subSequence(0, lineEndIndexs - 5) + "...";
                    mTvContent.setText(tt);
                    mIvMore.setImageResource(R.mipmap.down_icon);
                } else {
                    mTvContent.setMaxLines(Integer.MAX_VALUE / 2);
                    mTvContent.setText((String) v.getTag());
                    mIvMore.setImageResource(R.mipmap.top);

                }
            });
        }
        //头像 member_avatar
        String member_avatar = datas.getMember_avatar();
        Glide.with(mContext).load(member_avatar).into(mUserIcon);
        //用户收藏
        if (mType == 2) {
            mIsCollectDocotor = datas.getIs_collection().equals("1");
            if (mIsCollectDocotor)
                mIvIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.collect_doctor_red));
            else
                mIvIcon.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.upcollect_icon));

        }
    }


    private void showFragment(int index) {
        initTabState(index);
        if (currentIndex != index) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //如果已经有旧内容在显示 隐藏旧的dettach
            if (currentIndex != -1) {
                hideFragments(ft);
            }
            //显示新的：
            if (mFragments[index] == null) {
                //第一次显示：创建对象（记录） 添加
                switch (index) {
                    case 0:
                        if (mHomepageFragment != null) {
                            ft.show(mHomepageFragment);
                            // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                        } else {
                            mHomepageFragment = new HomepageFagment();
                            mFragments[0] = mHomepageFragment;
                            ft.add(R.id.fragment_vp, mHomepageFragment);
                        }
                        break;
                    case 1:
                        if (mConsultFragment != null) {
                            ft.show(mConsultFragment);
                            // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                        } else {
                            mConsultFragment = new ConsultFagment();
                            mFragments[1] = mConsultFragment;
                            ft.add(R.id.fragment_vp, mConsultFragment);
                        }
                        break;
                    case 2:
                        if (mAppointmentFragment != null) {
                            ft.show(mAppointmentFragment);
                            // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                        } else {
                            mAppointmentFragment = new AppointmentFagment();
                            mFragments[2] = mAppointmentFragment;
                            ft.add(R.id.fragment_vp, mAppointmentFragment);
                        }
                        break;
                }
            } else {
                //已有对象 显示attach
                ft.show(mFragments[index]);
            }
            ft.commit();
            currentIndex = index;
        }
    }

    public void hideFragments(FragmentTransaction ft) {
        if (mHomepageFragment != null)
            ft.hide(mHomepageFragment);
        if (mConsultFragment != null)
            ft.hide(mConsultFragment);
        if (mAppointmentFragment != null)
            ft.hide(mAppointmentFragment);

    }

    private void initTabState(int index) {
        mTvHomepage.setSelected(index == 0);
        mTvConsult.setSelected(index == 1);
        mTvAppointment.setSelected(index == 2);

    }


    @OnClick({R2.id.tv_homepage, R2.id.tv_consult, R2.id.tv_appointment, R2.id.iv_icon})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_homepage) {
            //主页
            showFragment(0);
        } else if (id == R.id.tv_consult) {
            //咨询
            showFragment(1);
        } else if (id == R.id.tv_appointment) {
//            //预约
            // TODO: 2019/12/16  模块未开放
            new XPopup.Builder(mContext)
                    .asCustom(new UnopenPopup(mContext, "该板块暂未开放"))
                    .show();
        } else if (id == R.id.iv_icon) {
            if (mType == 1) {
                mMorePopup = new MorePopup(mContext);
                mMorePopup.setOnClickListener(this);
                new XPopup.Builder(mContext)
                        .atView(view)
                        .asCustom(mMorePopup)
                        .show();
            } else {
                if (mIsCollectDocotor) {
                    mPresenter.cancelCollectDoctor(mKey, mDoctorId);
                } else {
                    mPresenter.collectDoctor(mKey, mDoctorId);

                }
            }
        }


    }

    /**
     * 添加标签
     */
    private void addLabel(String s) {

        //添加到集合中
//        mLabelArratList.add(s);
        TextView textView = new TextView(mContext);
        textView.setText(s);
        textView.setBackgroundResource(R.drawable.morbidity_type_bg);
        textView.setGravity(Gravity.CENTER);
        int padding2 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_2);
        int padding8 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_8);
        textView.setPadding(padding8, padding2, padding8, padding2);
        textView.setTextColor(mContext.getResources().getColor(R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        mFlowLayout.addView(textView);
    }

    @Override
    public void onOrderClick() {
        //点击订单
        startActivity(OrderListActivity.class);
        mMorePopup.dismiss();
    }

    @Override
    public void onRenewClick() {
        // TODO: 2019/12/20  价格咨询
        toActivity(ConsultActivity.class, "mDoctorId", mDoctorId);
        mMorePopup.dismiss();
    }


    @Override
    public void addDoctrInfo() {
        startActivity(AddDoctrInfoActivity.class);
        mMorePopup.dismiss();
    }
}
