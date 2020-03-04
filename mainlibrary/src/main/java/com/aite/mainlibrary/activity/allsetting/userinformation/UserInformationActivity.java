package com.aite.mainlibrary.activity.allsetting.userinformation;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.UseInformationBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.changeuserinformation.ChangeUserInformationActivity;
import com.aite.mainlibrary.activity.image.BaseImageActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class UserInformationActivity extends BaseActivity<UserInformationContract.View, UserInformationPresenter> implements UserInformationContract.View {
    @BindView(R2.id.user_icon)
    ImageView userIcon;
    @BindView(R2.id.user_icon_ll)
    LinearLayout userIconLl;
    @BindView(R2.id.usernumber_tv)
    TextView usernumberTv;
    @BindView(R2.id.user_name_tv)
    TextView userNameTv;
    @BindView(R2.id.gender_tv)
    TextView genderTv;
    @BindView(R2.id.birthday_tv)
    TextView birthdayTv;


    @Override
    protected int getLayoutResId() {
        return R.layout.user_setting_layout;
    }

    @Override
    protected void initView() {
        initToolbar("个人信息", "编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChangeUserInformationActivity.class);

            }
        });
        userIconLl.setOnClickListener(this);
    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        return httpParams;
    }

    @OnClick({R2.id.user_icon_ll})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_icon_ll)
            startActivity(BaseImageActivity.class, "imageUrl", AppConstant.ICON_URL);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initResume() {
        mPresenter.getUserInformation(initParams());

    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onGetUserInformation(Object msg) {
        if (((UseInformationBean) msg).getMember_info().getMember_avatar() == null) return;
        Glide.with(context)
                .load(((UseInformationBean) msg).getMember_info().getMember_avatar())
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
                .skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).into(userIcon);
        userNameTv.setText(((UseInformationBean) msg).getMember_info().getMember_truename());
        usernumberTv.setText(((UseInformationBean) msg).getMember_info().getMember_mobile());
        genderTv.setText(((UseInformationBean) msg).getMember_info().getMember_sex() == 1 ? "男" : "女");
        if (((UseInformationBean) msg).getMember_info().getMember_birthday() != null && !String.valueOf(((UseInformationBean) msg).getMember_info().getMember_birthday()).equals("null"))
            birthdayTv.setText(String.valueOf(((UseInformationBean) msg).getMember_info().getMember_birthday()));


    }


}

