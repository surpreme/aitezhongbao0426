package com.aite.mainlibrary.fragment.activityfragment.minefragment;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aite.mainlibrary.Constant.MainUIConstant;
import com.aite.mainlibrary.Mainbean.UseInformationBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allmain.AddDeviceMainActvity;
import com.aite.mainlibrary.activity.allmain.device.DeviceListActivity;
import com.aite.mainlibrary.activity.allmain.messager.MessagerActivity;
import com.aite.mainlibrary.activity.allmoney.moneycart.MoneycartActivity;
import com.aite.mainlibrary.activity.allsetting.LessbodybookActivity;
import com.aite.mainlibrary.activity.allsetting.MinePostBookActivity;
import com.aite.mainlibrary.activity.allsetting.bookdispute.BookDisputeActivity;
import com.aite.mainlibrary.activity.allsetting.healthbook.HealthBookActivity;
import com.aite.mainlibrary.activity.allsetting.helpeatorderbook.HelpEatOrderBookActivity;
import com.aite.mainlibrary.activity.allsetting.minerural.MineRuralActivity;
import com.aite.mainlibrary.activity.allsetting.serviceorderbook.ServiceOrderBookActivity;
import com.aite.mainlibrary.activity.allsetting.setting.SettingActivity;
import com.aite.mainlibrary.activity.allsetting.thingbook.ThingbookActivity;
import com.aite.mainlibrary.activity.allsetting.thingsbook.ChiendThingsbookActivity;
import com.aite.mainlibrary.activity.allsetting.userinformation.UserInformationActivity;
import com.aite.mainlibrary.adapter.GridViewIconAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.TextUtil;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineFragment extends BaseFragment<MineContract.View, MinePresenter> implements MineContract.View {
    @BindView(R2.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R2.id.setting_gridview)
    GridView settingGridview;
    @BindView(R2.id.book_gridview)
    GridView bookGridview;
    @BindView(R2.id.else_gridview)
    GridView elseGridview;
    @BindView(R2.id.device_gridview)
    GridView deviceGridview;
    @BindView(R2.id.fix_friends_btn)
    Button fixFriendsBtn;
    @BindView(R2.id.setting_img)
    ImageView settingImg;
    @BindView(R2.id.user_phone_number_tv)
    TextView userPhoneNumberTv;
    @BindView(R2.id.user_icon)
    ImageView userIcon;
    @BindView(R2.id.message_iv)
    ImageView messageIv;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getUserInformation(initParams());

    }

    @Override
    protected void initModel() {

    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        return httpParams;
    }

    @Override
    protected void initViews() {
        fixFriendsBtn.setOnClickListener(this);
        settingImg.setOnClickListener(this);
        userIcon.setOnClickListener(this);
        messageIv.setOnClickListener(this);
        userPhoneNumberTv.setOnClickListener(this);

        settingGridview.setAdapter(new GridViewIconAdapter(context, MainUIConstant.MineConstant.settingImg, MainUIConstant.MineConstant.settingTv));
        bookGridview.setAdapter(new GridViewIconAdapter(context, MainUIConstant.MineConstant.bookImg, MainUIConstant.MineConstant.bookTv));
        elseGridview.setAdapter(new GridViewIconAdapter(context, MainUIConstant.MineConstant.elseImg, MainUIConstant.MineConstant.elseTv));
        deviceGridview.setAdapter(new GridViewIconAdapter(context, MainUIConstant.MineConstant.deviceImg, MainUIConstant.MineConstant.deviceTv));
        settingGridview.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(DeviceListActivity.class);
                    break;
                case 1:
                    startActivity(MinePostBookActivity.class, "COMETYPE", "MINEGETBOOKACTIVITY");
                    break;
                case 2:
                    startActivity(MineRuralActivity.class);
                    break;
                case 3:
//                        startActivity(MineCollectActivity.class);
                    Intent intent7 = new Intent(getContext(), com.aite.a.activity.FavoriteListFargmentActivity.class);
                    intent7.putExtra("i", 1);
                    intent7.putExtra("key", AppConstant.KEY);
                    intent7.putExtra("url", "");
                    context.startActivity(intent7);
                    break;

                default:
                    break;
            }
        });
        /**
         *     case 2:
         *                     startActivity(LessbodybookActivity.class, "page_type", "1");
         *                     break;
         *                 case 3:
         *                     startActivity(LessbodybookActivity.class, "page_type", "2");
         *                     break;
         *                 case 4:
         *                     startActivity(LessbodybookActivity.class, "page_type", "3");
         *                     break;
         *                 case 5:
         *                     startActivity(LessbodybookActivity.class, "page_type", "4");
         *                     break;
         */
        bookGridview.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(ServiceOrderBookActivity.class);
                    break;
                case 1:
                    startActivity(ThingbookActivity.class);
                    break;
                default:
                    break;
            }
        });
        deviceGridview.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(AddDeviceMainActvity.class);
                    break;
                default:
                    break;
            }
        });
        elseGridview.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(MoneycartActivity.class);
                    break;
                case 1:
                    startActivity(HealthBookActivity.class);
                    break;
                case 2:
                    startActivity(MinePostBookActivity.class, "COMETYPE", "MINEPOSTBOOKACTIVITY");
                    break;
                case 3:
                    startActivity(BookDisputeActivity.class);
                    break;

                default:
                    break;
            }
        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_fragment;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fix_friends_btn) {
            LogUtils.d("被点击");

        } else if (v.getId() == R.id.setting_img) {
            startActivity(SettingActivity.class);
        } else if (v.getId() == R.id.user_phone_number_tv || v.getId() == R.id.user_icon)
            startActivity(UserInformationActivity.class);
        else if (v.getId() == R.id.message_iv) {
            startActivity(MessagerActivity.class);
        }

    }

    /**
     * 返回字段	类型	说明
     * member_info[].member_name	字符串	会员名称
     * member_info[].nickname	字符串	昵称
     * member_info[].member_truename	字符串	姓名
     * member_info[].member_sex	字符串	性别 1男 2女 其它保密
     * member_info[].member_birthday	字符串	生日
     * member_info[].member_mobile	字符串	手机号
     * member_info[].member_email	字符串	邮箱
     * member_info[].point	字符串	积分数
     * member_info[].predepoit	字符串	可用预存款
     * member_info[].member_areainfo	字符串	省市区地址
     * member_info[].member_avatar	字符串	头像
     * member_info[].is_allowtalk	整型	是否开启消息推送 1是
     * member_info[].is_member	整型	是否为会员 身份-会员 1是
     * member_info[].is_volunteer	整型	是否为义工 身份-义工 1是
     * member_info[].is_hugong	整型	是否为护工 身份-护工 1是
     * member_info[].is_doctors	整型	是否为医生 身份-医生 1是
     * member_info[].is_nursing	整型	是否为养老院 身份-养老院 1是
     * member_info[].current_identity	整型	用户当前默认身份 1会员 2义工 3护工 4医生
     *
     * @param msg
     */
    @Override
    public void onGetUserInformation(Object msg) {
        if (((UseInformationBean) msg).getMember_info().getMember_avatar() == null) return;
        if (((UseInformationBean) msg).getMember_info().getMember_avatar() != null)
            AppConstant.ICON_URL = ((UseInformationBean) msg).getMember_info().getMember_avatar();
        if (((UseInformationBean) msg).getMember_info().getMember_mobile() != null)
            AppConstant.PHONENUMBER = ((UseInformationBean) msg).getMember_info().getMember_mobile();
        if (((UseInformationBean) msg).getMember_info().getMember_avatar() != null)
            Glide.with(context).load(((UseInformationBean) msg).getMember_info().getMember_avatar()).apply(RequestOptions.circleCropTransform()).into(userIcon);
        if (((UseInformationBean) msg).getMember_info().getMember_truename() != null) {
            AppConstant.USERNAME = ((UseInformationBean) msg).getMember_info().getMember_truename();
            userPhoneNumberTv.setText(((UseInformationBean) msg).getMember_info().getMember_truename());
        }

    }


}
