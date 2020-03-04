package com.example.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.aite.amaplibrary.fragment.BaseAmapSelectionsActivity;
import com.amap.api.location.AMapLocationClient;
import com.example.Utils.ProgressDialog;
import com.example.base.GBaseActivity;
import com.example.bean.BaseBean;
import com.example.bean.DoctorInfoPersonBean;
import com.example.bean.DoctorWorkAdressBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.mvp.addDoctr.AddDoctrContract;
import com.example.mvp.addDoctr.AddDoctrPresenter;
import com.example.ui.adapter.AddSiteAdapter;
import com.example.ui.view.FlowLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.map.BaseGaodeAmap;
import com.lzy.basemodule.util.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间 2019/12/23 14:48
 * 描述: 添加医生信息
 */
public class AddDoctrInfoActivity extends GBaseActivity<AddDoctrPresenter> implements AddDoctrContract.View {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.et_name)
    EditText mEtName;
    @BindView(R2.id.tv_position)
    TextView mTvPosition;
    @BindView(R2.id.recy_view1)
    RecyclerView mRecyView1;
    @BindView(R2.id.flow_Layout)
    FlowLayout mFlowLayout;
    @BindView(R2.id.ll_add_address)
    LinearLayout mLlAddAddress;
    @BindView(R2.id.tv_type)
    TextView mTvType;
    @BindView(R2.id.ll_add_label)
    LinearLayout mLlAddLabel;
    @BindView(R2.id.et_departments)
    EditText mEtDepartments;
    @BindView(R2.id.et_introduce)
    EditText mEtIntroduce;
    @BindView(R2.id.tv_professional_types)
    TextView mTvProfessionalTypes;
    @BindView(R2.id.rl_professional_type)
    RelativeLayout mRlProfessionalType;
    @BindView(R2.id.rl_position1)
    RelativeLayout mRlPosition1;
    @BindView(R2.id.but_submit)
    Button mButSubmit;


    //经纬度
    private List<String> mLatitudeLongitude = new ArrayList<>();
    //地址
    private List<String> mLocationAdress = new ArrayList<>();

    //添加地址
    private AddSiteAdapter mAdapter;

    //职位
    private String[] mPositions = new String[]{"主任医师", "副主任医师", "主治医师", "医师"};
    //职位选择索引
    public int mPosition1 = -1;
    //标签集合
    private List<String> mLabelArratList = new ArrayList<>();

    //专业
    private String[] mType = new String[]{"中医", "西医"};
    public int mTypeIndex = -1;
    private AMapLocationClient locationClientSingle = null;
    private String longitude = "";
    private String latitude = "";


    @Override
    public int setLayoutId() {
        return R.layout.acticity_add_doctr;
    }

    @Override
    public void initOthers() {
        initView();
    }

    private void initView() {
        mPresenter = new AddDoctrPresenter();
        mPresenter.attachView(this);

        initToolbar("添加资料");
        mTvTitleRight.setVisibility(View.GONE);
        BaseGaodeAmap.startSingleLocation(locationClientSingle, aMapLocation -> {
            longitude = String.valueOf(aMapLocation.getLongitude());
            latitude = String.valueOf(aMapLocation.getLatitude());
        }, mContext);

//        List<String> data = new ArrayList<>();
        // data.add("地址地址");
        mAdapter = new AddSiteAdapter(mContext);
        mRecyView1.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        // mAdapter.appendData(data);
        mRecyView1.setAdapter(mAdapter);
        mPresenter.getDoctorInfoPerson(AppConstant.KEY, AppConstant.MEMBER_ID);
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
    public void onError(Throwable throwable) {
        log("报错" + throwable.getMessage());
    }

    @Override
    public void addDoctorInfo(BaseBean bean) {
        if (bean.getCode() == 200) {
            finish();
        }
        toast(bean.getMessage());

    }

    /**
     * code	整型	状态码
     * doctor_id	datas	医生扩展表id
     * member_id	datas	会员id
     * work_address	datas	执业地址
     * adept	datas	擅长
     * introduce	datas	介绍
     * professional_types	datas	专业类型,1,中医,2,西医
     * departments	datas	科室
     * position	datas	职位
     * collection_count	datas	收藏数
     * consult_count	datas	咨询数
     * evaluate_count	datas	点评数
     * evaluate_score	datas	评分
     * consult_price	datas	最低价格
     * consult_time	datas	最低价格对应咨询时间
     * p_consult_price	datas	电话咨询价格
     * p_consult_time	datas	电话咨询时间
     *
     * @param bean
     */
    @Override
    public void getDoctorInfoPerson(BaseBean<DoctorInfoPersonBean> bean) {
        LogUtils.d(bean.toString());
        try {
            if (bean.getDatas() == null) return;
            if (bean.getDatas().getMember_name() != null)
                mEtName.setText(bean.getDatas().getMember_name());
            if (bean.getDatas().getPosition() != null) {
                mPosition1 = Integer.parseInt(bean.getDatas().getPosition());
                mTvPosition.setText(mPositions[Integer.parseInt(bean.getDatas().getPosition())-1]);

            }
            if (bean.getDatas().getDepartments() != null)
                mEtDepartments.setText(bean.getDatas().getDepartments());
            if (bean.getDatas().getProfessional_types() != null) {
                mTvType.setText(bean.getDatas().getProfessional_types().equals("1") ? "中医" : "西医");
                try {
                    mTypeIndex = Integer.parseInt(bean.getDatas().getProfessional_types());

                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            if (bean.getDatas().getIntroduce() != null)
                mEtIntroduce.setText(bean.getDatas().getIntroduce());
            if (bean.getDatas().getAdept() != null && !bean.getDatas().getAdept().isEmpty()) {
                for (int i = 0; i < bean.getDatas().getAdept().size(); i++) {
                    addLabel(bean.getDatas().getAdept().get(i));
                }
            }
//            if (bean.getDatas().getWork_address() != null && !bean.getDatas().getWork_address().isEmpty()) {
//                for (int i = 0; i < bean.getDatas().getWork_address().size(); i++) {
//                    mAdapter.appendData(bean.getDatas().getWork_address().get(i));
//                }
//            }
            if (bean.getDatas().getLongitude()!=null){
                if (!bean.getDatas().getLongitude().equals("[]")&&!bean.getDatas().getLongitude().equals("")){
                    List<DoctorWorkAdressBean> list=new Gson().fromJson(bean.getDatas().getLongitude(),new TypeToken<List<DoctorWorkAdressBean>>(){}.getType());
                    for (int i=0;i<list.size();i++){
                        mAdapter.appendData(list.get(i).getTitle());
                        mLatitudeLongitude.add(list.get(i).getLocation());
                        mLocationAdress.add(list.get(i).getTitle());

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editInfoSuccess(String s) {

    }


    @OnClick({R2.id.ll_add_address, R2.id.rl_professional_type, R2.id.but_submit, R2.id.rl_position1, R2.id.ll_add_label})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.rl_position1) {
            //职位
            selectFrame("请选择职位", mPositions, mTvPosition);

        } else if (id == R.id.ll_add_address) {
            //添加地址
            addAddress();
        } else if (id == R.id.ll_add_label) {
            //添加标签
            new XPopup.Builder(mContext).asInputConfirm("擅长", "请输入标签",
                    new OnInputConfirmListener() {
                        @Override
                        public void onConfirm(String text) {
                            if (!TextUtils.isEmpty(text)) {
                                addLabel(text);
                            } else {
                                toast("请填写擅长的标签...");
                            }
                        }
                    }).show();

        } else if (id == R.id.rl_professional_type) {
            //专业选择框
            selectFrame("请选择专业类型", mType, mTvType);
        } else if (id == R.id.but_submit) {
            //提交
            submit();
        }
    }

    /**
     * 提交
     */
    private void submit() {
        String key = AppConstant.KEY;
        String site = ""; //地址
        List<String> dataList = mAdapter.getDataList();
        for (int i = 0; i < dataList.size(); i++) {
            if (!TextUtils.isEmpty(dataList.get(i))) {
                site += dataList.get(i) + ";";
            }
        }

        //擅长
        String adept = "";
        for (int i = 0; i < mLabelArratList.size(); i++) {
            if (!TextUtils.isEmpty(mLabelArratList.get(i))) {
                adept += mLabelArratList.get(i) + ";";
            }
        }
        //上传执业地点的json 地址数据 ：[{"title":"朗景园","location":"22.540989,113.950558"},{"title":"万德莱南座","location":"22.537773,113.948156"}]
        JSONArray array1 = new JSONArray();
        try {
            if (mLatitudeLongitude != null && mLocationAdress != null) {
                if (mLocationAdress.size() == mLatitudeLongitude.size()) {
                    for (int i = 0; i < mLatitudeLongitude.size(); i++) {
                        JSONObject object = new JSONObject();
                        object.put("title", mLocationAdress.get(i));
                        object.put("location", mLatitudeLongitude.get(i));
                        array1.put(object);
                        System.out.println(array1.toString());
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
            ToastUtils.showToast(mContext, "系统繁忙 请稍后再试");
            return;
        }

        //介绍
        String introduce = mEtIntroduce.getText().toString();
        //科室
        String departments = mEtDepartments.getText().toString();
        if (getIntent().getStringExtra("edit") != null) {
//            mPresenter.editDoctorInfo(key, AppConstant.DOCTOR_ID, site, adept, introduce, mTypeIndex + 1, departments, String.valueOf(mPosition1 + 1), longitude, latitude);
            mPresenter.editDoctorInfo(key, AppConstant.DOCTOR_ID, site, adept, introduce, mTypeIndex + 1, departments, String.valueOf(mPosition1 + 1), array1.toString(), latitude);
        } else
            mPresenter.addDoctorInfo(key, AppConstant.DOCTOR_ID, site, adept, introduce, mTypeIndex + 1, departments, String.valueOf(mPosition1 + 1), array1.toString(), latitude);
//            mPresenter.addDoctorInfo(key, AppConstant.DOCTOR_ID, site, adept, introduce, mTypeIndex + 1, departments, String.valueOf(mPosition1 + 1), longitude, latitude);
    }

    /**
     * @param title     标题
     * @param positions 数据
     * @param textView  TextView
     */
    private void selectFrame(String title, String[] positions, TextView textView) {
        new XPopup.Builder(mContext)
                .asBottomList(title, positions,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                if (textView == mTvType) {
                                    mTypeIndex = position;
                                } else {
                                    mPosition1 = position;
                                }

                                textView.setText(text);
                            }
                        })
                .setCheckedPosition(textView == mTvType ? mTypeIndex : mPosition1)
                .show();
    }

    /**
     * 添加标签
     */
    private void addLabel(String s) {

        //添加到集合中
        mLabelArratList.add(s);
        TextView textView = new TextView(mContext);
        textView.setText(s);
        textView.setBackgroundResource(R.drawable.search_bg_item);
        textView.setGravity(Gravity.CENTER);
        int padding2 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_2);
        int padding8 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_8);
        textView.setPadding(padding8, padding2, padding8, padding2);
        textView.setTextColor(mContext.getResources().getColor(R.color.text1));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        mFlowLayout.addView(textView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.ACTIVITY_RESULT_CODE.REQUEST_CODE_ACTIVITY_RESULT && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.getStringExtra("LatitudeLongitude") != null) {
                    mLatitudeLongitude.add(data.getStringExtra("LatitudeLongitude"));
                    if (data.getStringExtra("LocationName") != null) {
                        mLocationAdress.add(data.getStringExtra("LocationName"));
                        LogUtils.d("LatitudeLongitude", data.getStringExtra("LatitudeLongitude") + data.getStringExtra("LocationName"));
                        ToastUtils.showToast(mContext, data.getStringExtra("LatitudeLongitude") + data.getStringExtra("LocationName"));
                        mAdapter.appendData(data.getStringExtra("LocationName"));

                    }
                }
            }
        }
    }

    /**
     * 添加地址
     */
    private void addAddress() {
//        startActivity(BaseAmapSelectionsActivity.class);
        startActivityWithCls(BaseAmapSelectionsActivity.class,
                BaseConstant.ACTIVITY_RESULT_CODE.REQUEST_CODE_ACTIVITY_RESULT,
                new ContentValue("TYPE", "CHOICE_ADDRESS"));
        List<String> dataList = mAdapter.getDataList();
        if (dataList.size() > 0) {
            if (!TextUtils.isEmpty(dataList.get(dataList.size() - 1))) {
//                mAdapter.appendData("");
            } else {
                toast("请选择地址再次添加...");
            }
        } else {
//            mAdapter.appendData("");
        }
    }
}
