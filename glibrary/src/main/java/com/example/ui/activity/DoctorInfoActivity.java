package com.example.ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Utils.TextUtil;
import com.example.app.Constants;
import com.example.base.GBaseActivity;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.mvp.doctorInfo.DoctorInfoContract;
import com.example.mvp.doctorInfo.DoctorInfoPresenter;
import com.example.ui.fagment.AppointmentFagment;
import com.example.ui.fagment.ConsultFagment;
import com.example.ui.fagment.HomepageFagment;
import com.example.ui.view.MorePopWindow;
import com.example.ui.view.UnopenPopup;
import com.lxj.xpopup.XPopup;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.view.RatingBarView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 医生信息
 */

public class DoctorInfoActivity extends GBaseActivity<DoctorInfoPresenter> implements MorePopWindow.OnPopWindowItemClickListener, DoctorInfoContract.View {

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

    private int currentIndex = -1;

    private Fragment[] mFragments = new Fragment[3];

    private Fragment mHomepageFragment, mConsultFragment, mAppointmentFragment;

    private MorePopWindow mOrderPopup;
    public String mDoctorId;
    private String mKey;
    private DoctorInfoBean mDatas;


    @Override
    public int setLayoutId() {
        return R.layout.maindoctor_information;
    }

    @Override
    public void initOthers() {

        mDoctorId = getIntent().getStringExtra("doctorId");
        log("mDoctorId=" + mDoctorId);

        mPresenter = new DoctorInfoPresenter();
        mPresenter.attachView(this);

        mKey = AppConstant.KEY;
        mPresenter.getDoctorInfo(mKey, mDoctorId);

        showFragment(0);

        //头像
        Glide.with(mContext).load(mContext.getResources().getDrawable(R.mipmap.orange_else)).into(mUserIcon);
    }

//    @Override
//    public void showLoading() {
//        ProgressDialog.getInstance().show(mContext);
//    }

//    @Override
//    public void hideLoading() {
//        ProgressDialog.getInstance().dismiss();
//    }


    @Override
    public void getDoctorInfo(BaseBean<DoctorInfoBean> bean) {
        if (bean.getCode() == 200) {
            mDatas = bean.getDatas();

            initView(mDatas);
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    /**
     * 数据初始化
     *
     * @param datas
     */
    private void initView(DoctorInfoBean datas) {
        initToolbar("个人主页-" + datas.getMember_truename());

        //名称 member_truename
        mTvName.setText(datas.getMember_truename());
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
        mTvHospital.setText("执业地址");
        //评分 evaluate_score
        mStarView.setStar(datas.getEvaluate_count());
        mTvGrade1.setText(String.valueOf(datas.getEvaluate_count()));
        //粉丝 collection_count
        mTvGrade.setText(TextUtil.highlight(mContext, "粉丝" + datas.getCollection_count(), String.valueOf(datas.getCollection_count()), "#F4EA2A", 0, 0));
        //咨询量  consult_count
        mTvSubscribeNum.setText(TextUtil.highlight(mContext, "咨询量" + datas.getConsult_count(), datas.getConsult_count(), "#F4EA2A", 0, 0));
        //预约数  todo
        mTvConsultNum.setText(TextUtil.highlight(mContext, "预约数null", "null", "#F4EA2A", 0, 0));
        //回复 todo
        mTvReplyNum.setText(TextUtil.highlight(mContext, "回复速度null", "null", "#F4EA2A", 0, 0));
        //擅长
        mTvMorbidityType.setVisibility(datas.getAdept().size() > 0 ? View.VISIBLE : View.GONE);
        if (datas.getAdept().size() > 0) {
            mTvMorbidityType.setText(datas.getAdept().get(0));
        }
        //介绍 introduce
        mTvContent.setText(datas.getIntroduce());
        //todo 头像

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


    @OnClick({R2.id.tv_homepage, R2.id.tv_consult, R2.id.tv_appointment, R2.id.tv_title_right})
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
//            showFragment(2);
            // TODO: 2019/12/16  模块未开放
            new XPopup.Builder(mContext)
                    .asCustom(new UnopenPopup(mContext, "该板块暂未开放"))
                    .show();
        } else if (id == R.id.tv_title_right) {
            MorePopWindow popWindow = new MorePopWindow(mContext, this);
            popWindow.showPopupWindow(view);
        }
    }

    @Override
    public void onOrderClick() {
        //点击订单
        startActivity(OrderListActivity.class);
    }

    @Override
    public void onRenewClick() {
        //续费
    }

    @Override
    public void addDoctrInfo() {
        //添加医生信息
        startActivity(AddDoctrInfoActivity.class);
    }

}
