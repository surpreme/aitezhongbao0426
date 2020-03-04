package com.aite.mainlibrary.activity.allsetting.editSosUser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;

import androidx.lifecycle.ViewModelProviders;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.aite.mainlibrary.Mainbean.BinduserfamilyTypeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.adapter.AddBindingUserAdapter;
import com.aite.mainlibrary.adapter.AddBindingUserAdapter2;
import com.aite.mainlibrary.databinding.EditSosUserBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.util.FileUtils;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Auther: liziyang
 * @datetime: 2020/1/11
 * @desc:
 */
public class EditSosUserActivity extends BaseActivity<EditSosUserBinding, EditSosUserViewModel> {
    private Context context;
    private List<Uri> mSelected = new ArrayList<>();


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.edit_sos_user;
    }

    @Override
    public void initParam() {
        context = this;

    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public EditSosUserViewModel initViewModel() {
        return ViewModelProviders.of(this).get(EditSosUserViewModel.class);
    }

    @Override
    public void initData() {
        StatusBarUtils.setColor(context, getResources().getColor(R.color.white));
        getData();
        binding.userTypeTitleTv.setOnClickListener(v -> {
            if (viewModel.relationshipList != null && !viewModel.relationshipList.isEmpty()) {
                AddBindingUserAdapter2 addBindingUserAdapter = new AddBindingUserAdapter2(context, viewModel.relationshipList);
                PopwindowUtils.getmInstance().showChioceBottomPopupWindow(context, Gravity.BOTTOM, 0.7f, addBindingUserAdapter);
                addBindingUserAdapter.setClickInterface(postion -> {
                    LogUtils.d("id=======" + viewModel.relationshipList.get(postion).getId());
                    PopwindowUtils.getmInstance().dismissPopWindow();
                    viewModel.userTypeTv.set(viewModel.relationshipList.get(postion).getTitle());

                });
            } else {
                ToastUtils.showShort("数据错误");
                onBackPressed();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            if (mSelected.isEmpty()) return;
            Glide.with(context).load(mSelected.get(0)).apply(RequestOptions.circleCropTransform()).into(binding.userIcon);
            viewModel.iconFile = FileUtils.getFileByUri(context, mSelected.get(0));
        }

    }

    private void getData() {
        if (getIntent() != null && getIntent().getStringExtra("id") != null) {
            showDialog("正在加载数据....");
            viewModel.getSosUserInformaion(AppConstant.KEY, getIntent().getStringExtra("id"));
        } else {
            ToastUtils.showShort("信息错误");
            LogUtils.e("信息错误");
            onBackPressed();
        }
    }

    /**
     * 返回字段	类型	说明
     * datas->info[]	数组	信息
     * datas->info[]->id	整型	ID
     * datas->info[]->realname	字符串	姓名
     * datas->info[]->mobile	字符串	联系电话
     * datas->info[]->relation	字符串	关系
     * datas->info[]->avatar	字符串	头像
     * datas->info[]->is_default	字符串	是否默认
     * error	字符串	错误信息 code=200 正确 其他编码错误
     * "id": 1,
     * "title": "父亲"
     * "id": 2,
     * "title": "母亲"
     * "id": 3,
     * "title": "子女"
     * "id": 4,
     * "title": "爱人"
     * "id": 5,
     * "title": "其他"
     *
     * @sosUserInformationBean
     */
    private void registerGetUserInformationSuccess() {
        Messenger.getDefault().register(context, EditSosUserViewModel.TOKEN_GET_DATA_SUCCESS, SosUserInformationBean.class, sosUserInformationBean -> {
            LogUtils.d(sosUserInformationBean.toString());
//            Glide.with(context).load(sosUserInformationBean.getInfo().getAvatar()).apply(RequestOptions.circleCropTransform()).into(binding.userIcon);
            viewModel.userNameTv.set(sosUserInformationBean.getInfo().getRealname());
            viewModel.userIconUrl.set(sosUserInformationBean.getInfo().getAvatar());
            viewModel.userPhoneTv.set(sosUserInformationBean.getInfo().getMobile());
            viewModel.setDefaultChecked.set(sosUserInformationBean.getInfo().getIs_default().equals("1"));
            RxBus.getDefault().post(sosUserInformationBean.getInfo().getAvatar());
            switch (sosUserInformationBean.getInfo().getRelation()) {
                case "1":
                    viewModel.userTypeTv.set("父亲");
                    break;
                case "2":
                    viewModel.userTypeTv.set("母亲");
                    break;
                case "3":
                    viewModel.userTypeTv.set("子女");
                    break;
                case "4":
                    viewModel.userTypeTv.set("爱人");
                    break;
                case "5":
                    viewModel.userTypeTv.set("其他");
                    break;
                default:
                    viewModel.userTypeTv.set("未知");
                    break;

            }
        });
    }


}
