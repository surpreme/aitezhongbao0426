package com.aite.mainlibrary.activity.allsetting.healthbookinformation;


import android.widget.ImageView;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.HealthBookInformationBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.util.TextEmptyUtils;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HealthBookInformationActivity extends BaseActivity<HealthBookInformationContract.View, HealthBookInformationPresenter> implements HealthBookInformationContract.View {

    @BindView(R2.id.information_tv)
    TextView informationTv;
    @BindView(R2.id.time_tv)
    TextView timeTv;
    @BindView(R2.id.img_first)
    ImageView imgFirst;
    @BindView(R2.id.img_two)
    ImageView imgTwo;
    @BindView(R2.id.img_three)
    ImageView imgThree;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_healthbookinformation;
    }

    @Override
    protected void initView() {
        initToolbar("详细信息");

    }

    @Override
    protected void initDatas() {
        mPresenter.getSickInformation(initListHttpParams(true, new ContentValue("id", getIntent().getStringExtra("id"))));

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    public void onGetSickInformationSuccess(Object msg) {
        HealthBookInformationBean healthBookInformationBean = (HealthBookInformationBean) msg;
        if (healthBookInformationBean.getName() != null)
            initToolbar(healthBookInformationBean.getName());
        if (healthBookInformationBean.getTime() != null) {
            timeTv.setText(TextEmptyUtils.getText(healthBookInformationBean.getTime()));
        }
        if (healthBookInformationBean.getDescription() != null) {
            informationTv.setText(TextEmptyUtils.getText(healthBookInformationBean.getDescription()));
        }
        if (healthBookInformationBean.getDiseases_imgs() != null) {
            if (healthBookInformationBean.getDiseases_imgs().size() == 1) {
                Glide.with(context).load(healthBookInformationBean.getDiseases_imgs().get(0)).into(imgFirst);

            } else if (healthBookInformationBean.getDiseases_imgs().size() == 2) {
                Glide.with(context).load(healthBookInformationBean.getDiseases_imgs().get(0)).into(imgFirst);
                Glide.with(context).load(healthBookInformationBean.getDiseases_imgs().get(1)).into(imgTwo);

            } else if (healthBookInformationBean.getDiseases_imgs().size() == 3) {
                Glide.with(context).load(healthBookInformationBean.getDiseases_imgs().get(0)).into(imgFirst);
                Glide.with(context).load(healthBookInformationBean.getDiseases_imgs().get(1)).into(imgTwo);
                Glide.with(context).load(healthBookInformationBean.getDiseases_imgs().get(2)).into(imgThree);

            }


        }
    }
}
