package com.example.ui.activity;

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
import com.example.Utils.ProgressDialog;
import com.example.base.GBaseActivity;
import com.example.bean.BaseBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.mvp.addDoctr.AddDoctrContract;
import com.example.mvp.addDoctr.AddDoctrPresenter;
import com.example.ui.adapter.AddSiteAdapter;
import com.example.ui.view.FlowLayout;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lzy.basemodule.BaseConstant.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

        initToolbar("添加地址");
        mTvTitleRight.setVisibility(View.GONE);

        List<String> data = new ArrayList<>();
        // data.add("地址地址");
        mAdapter = new AddSiteAdapter(mContext);
        mRecyView1.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        // mAdapter.appendData(data);
        mRecyView1.setAdapter(mAdapter);
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

        //介绍
        String introduce = mEtIntroduce.getText().toString();
        //科室
        String departments = mEtDepartments.getText().toString();

        mPresenter.addDoctorInfo(key, site, adept, introduce, mTypeIndex + 1, departments, String.valueOf(mPosition1 + 1));
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

    /**
     * 添加地址
     */
    private void addAddress() {
        List<String> dataList = mAdapter.getDataList();
        if (dataList.size() > 0) {
            if (!TextUtils.isEmpty(dataList.get(dataList.size() - 1))) {
                mAdapter.appendData("");
            } else {
                toast("请输入地址再次添加...");
            }
        } else {
            mAdapter.appendData("");
        }
    }
}
