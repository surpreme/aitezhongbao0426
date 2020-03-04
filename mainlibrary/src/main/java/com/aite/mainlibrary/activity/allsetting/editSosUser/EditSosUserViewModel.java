package com.aite.mainlibrary.activity.allsetting.editSosUser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.mainlibrary.Mainbean.AddbinduserfamilyBean;
import com.aite.mainlibrary.Mainbean.BinduserfamilyTypeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.activitylife.ActivityManager;
import com.lzy.basemodule.baseRetrofit.http.RetrofitClient;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseData2;
import com.lzy.basemodule.bean.ErrorBean;
import com.lzy.basemodule.net.RxScheduler;
import com.lzy.basemodule.util.FileUtils;
import com.lzy.basemodule.util.RxUtil;
import com.lzy.basemodule.util.TextEmptyUtils;
import com.youth.banner.loader.ImageLoader;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-11
 * @desc:
 */
public class EditSosUserViewModel extends BaseViewModel<MvvMViewAdapter> {
    public static final String TOKEN_GET_DATA_SUCCESS = "TOKEN_GET_DATA_SUCCESS";
    private SosUserInformationBean mSosUserInformationBean;
    public File iconFile;
    public List<BinduserfamilyTypeBean> relationshipList = new ArrayList<>();

    public EditSosUserViewModel(@NonNull Application application) {
        super(application);
        titleTv.set("修改紧急联系人资料");
        getSosUserRelationship(AppConstant.KEY);
    }

    public ObservableField<String> titleTv = new ObservableField<>();
    public ObservableField<String> userIconUrl = new ObservableField<>();
    public ObservableField<String> userNameTv = new ObservableField<>();
    public ObservableField<String> userTypeTv = new ObservableField<>();
    public ObservableField<String> userPhoneTv = new ObservableField<>();
    public ObservableBoolean setDefaultChecked = new ObservableBoolean();
    public BindingCommand backOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onBackPressed();
        }
    });
    public BindingCommand ChoiceImageOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            openImg(ActivityManager.getInstance().getCurrentActivity(), 1, BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE);

        }
    });
    public BindingCommand overingCommitOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(userTypeTv.get())) {
                ToastUtils.showShort("请选择用户与您的身份");
                return;
            }
            String typeIndex;
            switch (Objects.requireNonNull(userTypeTv.get())) {
                case "父亲":
                    typeIndex = "1";
                    break;
                case "母亲":
                    typeIndex = "2";
                    break;
                case "子女":
                    typeIndex = "3";
                    break;
                case "爱人":
                    typeIndex = "4";
                    break;
                case "其他":
                default:
                    typeIndex = "5";
                    break;

            }
            showDialog("正在保存中....");
            if (iconFile != null) {
                if (!iconFile.exists()) {
                    dismissDialog();
                    return;
                }
                onSaveInformation(
                        AppConstant.KEY,
                        mSosUserInformationBean.getInfo().getId(),
                        "2",
                        iconFile,
                        userNameTv.get(),
                        userPhoneTv.get(),
                        typeIndex,
                        setDefaultChecked.get() ? "0" : "1"
                );
            } else {
                onSaveInformation(
                        AppConstant.KEY,
                        mSosUserInformationBean.getInfo().getId(),
                        "2",
                        null,
                        userNameTv.get(),
                        userPhoneTv.get(),
                        typeIndex,
                        setDefaultChecked.get() ? "0" : "1"
                );

            }

        }
    });

    /**
     * * key	post	字符串	必须			会员登录key
     * * id	post	整型	可选			紧急联系人id
     * * type	post	整型	必须			保存类型 1添加 2修改(需上传紧急联系人id)
     * * avatar	post	文件	必须	1		头像文件参数
     * * realname	post	字符串	必须			真实姓名
     * * mobile	post	字符串	必须			手机号
     * * relation	post	整型	必须			关系id
     * * is_default	post	整型	可选			是否 默认1- 否0
     */
    @SuppressLint("CheckResult")
    private void onSaveInformation(String key, String id, String type, File file, String realname, String mobile, String relation, String is_default) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        // 添加文件
        if (file != null) {
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("avatar", file.getName(), imageBody);
        }
        builder.addFormDataPart("key", key);
        builder.addFormDataPart("id", id);
        builder.addFormDataPart("type", type);
        builder.addFormDataPart("realname", realname);
        builder.addFormDataPart("mobile", mobile);
        builder.addFormDataPart("relation", relation);
        builder.addFormDataPart("is_default", is_default);

        List<MultipartBody.Part> parts = builder.build().parts();

        RetrofitClient.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onSaveEditSosUserInformation(parts)
                .compose(RxScheduler.Flo_io_main())
                .filter(stateCodeBeanBaseData -> {
                    if (stateCodeBeanBaseData.isSuccessed()) {
                        return false;
                    } else {
                        if (stateCodeBeanBaseData.getDatas().getError() != null) {
                            ToastUtils.showShort(stateCodeBeanBaseData.getDatas().getError());
                            LogUtils.e(stateCodeBeanBaseData.getDatas().getError());
                            dismissDialog();
                        }
                        return true;
                    }
                })
                .map(BaseData::getDatas)
                .filter(successCodeBean -> {
                    if (successCodeBean.getError() != null) {
                        ToastUtils.showShort(successCodeBean.getError());
                        LogUtils.e(successCodeBean.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(stateCodeBean -> {
            if (stateCodeBean.getResult().equals("1") || stateCodeBean.getMsg().equals("保存成功")) {
                dismissDialog();
                ToastUtils.showShort("保存成功");
                onBackPressed();
            }
            LogUtils.d(stateCodeBean.getResult() + stateCodeBean.getMsg());
        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });

    }

    @SuppressLint("CheckResult")
    public void getSosUserInformaion(String key, String id) {
        RetrofitClient.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetSosInformationData(key, id)
                .compose(RxScheduler.Flo_io_main())
                .filter(sosUserInformationBeanBaseData -> {
                    if (sosUserInformationBeanBaseData.isSuccessed()) {
                        return false;
                    } else {
                        if (sosUserInformationBeanBaseData.getDatas().getError() != null) {
                            ToastUtils.showShort(sosUserInformationBeanBaseData.getDatas().getError());
                            LogUtils.e(sosUserInformationBeanBaseData.getDatas().getError());
                            dismissDialog();
                        }
                        return true;
                    }
                })
                .map(BaseData::getDatas)
                .filter(sosUserInformationBean -> {
                    if (sosUserInformationBean.getError() != null) {
                        ToastUtils.showShort(sosUserInformationBean.getError());
                        LogUtils.e(sosUserInformationBean.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(sosUserInformationBean -> {
            registerGetUserInformationSuccess(mSosUserInformationBean = sosUserInformationBean);
        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });

    }

    @SuppressLint("CheckResult")
    public void getSosUserRelationship(String key) {
        RetrofitClient.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetSosRelationshipData(key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code.equals("200")) {
                            JSONArray datasArry = jsonObject.optJSONArray("datas");
                            if (datasArry == null) {
                                ToastUtils.showShort("数据错误");
                                onBackPressed();
                                return;
                            }
                            for (int i = 0; i < datasArry.length(); i++) {
                                LogUtils.d(datasArry.get(i).toString());
                                BinduserfamilyTypeBean binduserfamilyTypeBean = new Gson().fromJson(datasArry.get(i).toString(), BinduserfamilyTypeBean.class);
                                relationshipList.add(binduserfamilyTypeBean);
                            }
                            LogUtils.d(relationshipList);
                        } else {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            if (errorObject == null) return;
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
                            LogUtils.e(errorBean.getError());
                            ToastUtils.showShort(errorBean.getError());
                            onBackPressed();
                        }
                    }
                });

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
     * *Messenger.getDefault().send(sosUserInformationBean, EditSosUserViewModel.TOKEN_GET_DATA_SUCCESS);
     *
     * @sosUserInformationBean
     */
    private void registerGetUserInformationSuccess(SosUserInformationBean sosUserInformationBean) {
        dismissDialog();
        userNameTv.set(sosUserInformationBean.getInfo().getRealname());
        userIconUrl.set(sosUserInformationBean.getInfo().getAvatar());
        userPhoneTv.set(sosUserInformationBean.getInfo().getMobile());
        setDefaultChecked.set(sosUserInformationBean.getInfo().getIs_default().equals("1"));
        switch (sosUserInformationBean.getInfo().getRelation()) {
            case "1":
                userTypeTv.set("父亲");
                break;
            case "2":
                userTypeTv.set("母亲");
                break;
            case "3":
                userTypeTv.set("子女");
                break;
            case "4":
                userTypeTv.set("爱人");
                break;
            case "5":
                userTypeTv.set("其他");
                break;
            default:
                userTypeTv.set("未知");
                break;

        }

    }

    private void openImg(Activity activity, int choiceimgNumber, int resultcode) {
        Matisse.from(activity)
                .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                .countable(true)
                .maxSelectable(choiceimgNumber) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .theme(com.lzy.basemodule.R.style.Matisse_Dracula)
                .forResult(resultcode); // 设置作为标记的请求码
    }

}
