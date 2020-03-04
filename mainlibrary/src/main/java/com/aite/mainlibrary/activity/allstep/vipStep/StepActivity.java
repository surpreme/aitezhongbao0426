package com.aite.mainlibrary.activity.allstep.vipStep;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.databinding.SignInDayLayoutBinding;
import com.aite.mainlibrary.databinding.SignInDayLayoutBindingImpl;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.view.StatusBarUtils;
import com.lzy.basemodule.view.StepsView;
import com.umeng.commonsdk.debug.E;

import butterknife.BindView;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.Messenger;

public class StepActivity extends BaseActivity<SignInDayLayoutBinding, StepViewModel> {
    private int mprogress = 0;
    private int mMondays = 0;
    private Context context;

    @Override
    public void initParam() {
        context = this;
    }


    private void setInitStepview(int month, int progress) {
        binding.stepview.setMonthDays(month);
        binding.stepview.setProgress(progress);
    }

    private void setInitStepview(int progress) {
        binding.stepview.setProgress(progress);

    }

    @Override
    public void initData() {
        StatusBarUtils.setColor(context, getResources().getColor(R.color.blue_day_login));
        Messenger.getDefault().register(context, StepViewModel.TOKEN_GET_DATA_SUCCESS, StepInformationBean.class, stepInformationBean -> {
            try {
                mprogress = Integer.parseInt(stepInformationBean.getSignCount());
                mMondays = Integer.parseInt(stepInformationBean.getDayCount());
                setInitStepview(Integer.parseInt(stepInformationBean.getDayCount()), Integer.parseInt(stepInformationBean.getSignCount()));
            } catch (Exception e) {
                LogUtils.e(e);
            }

        });
        Messenger.getDefault().register(context, StepViewModel.TOKEN_STEP_SUCCESS, String.class, s -> {
            try {
                mprogress++;
                setInitStepview(mprogress);
                viewModel.onGetStepInformation(AppConstant.KEY);
            } catch (Exception e) {
                LogUtils.e(e);
            }

        });
        viewModel.onGetStepInformation(AppConstant.KEY);
        showDialog("正在加载中....");

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.sign_in_day_layout;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
