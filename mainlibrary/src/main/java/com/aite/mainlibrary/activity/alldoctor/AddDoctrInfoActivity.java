package com.aite.mainlibrary.activity.alldoctor;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.AddSiteAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lzy.basemodule.dailogwithpop.ProgressDialog;
import com.lzy.basemodule.mvp2.GBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间 2019/12/23 14:48
 * 描述: 添加医生信息
 */
public class AddDoctrInfoActivity extends GBaseActivity {
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.et_name)
    EditText mEtName;
    @BindView(R2.id.tv_position)
    TextView mTvPosition;
    @BindView(R2.id.recy_view)
    RecyclerView mRecyView;
    @BindView(R2.id.ll_add_address)
    LinearLayout mLlAddAddress;
    @BindView(R2.id.et_departments)
    EditText mEtDepartments;
    @BindView(R2.id.tv_professional_types)
    TextView mTvProfessionalTypes;
    @BindView(R2.id.rl_professional_type)
    RelativeLayout mRlProfessionalType;

    @BindView(R2.id.rl_position1)
    RelativeLayout mRlPosition1;

    @BindView(R2.id.but_submit)
    Button mButSubmit;

    private AddSiteAdapter mAdapter;
    private int mPosition = -1;
    private String[] positions = new String[]{"主任医师", "副主任医师", "主治医师", "医师"};

    @Override
    public int setLayoutId() {
        return R.layout.acticity_add_doctr;
    }

    @Override
    public void initOthers() {
        initView();
    }

    private void initView() {
        List<String> data = new ArrayList<>();
        data.add("地址地址");
        mAdapter = new AddSiteAdapter(mContext);
        mRecyView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter.appendData(data);
        mRecyView.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(mContext);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onError(Throwable throwable) {

    }


    @OnClick({R2.id.ll_add_address, R2.id.rl_professional_type, R2.id.but_submit, R2.id.rl_position1})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.rl_position1) {
            //职位
            // 这种弹窗从 1.0.0版本开始实现了优雅的手势交互和智能嵌套滚动
            new XPopup.Builder(mContext)
                    .asBottomList("请选择职位", positions,
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    mPosition = position;
                                    mTvPosition.setText(text);
                                }
                            })
                    .setCheckedPosition(mPosition)
                    .show();

        } else if (id == R.id.ll_add_address) {
            List<String> dataList = mAdapter.getDataList();
            if (!TextUtils.isEmpty(dataList.get(dataList.size() - 1))) {
                List<String> newDataList = new ArrayList<>();
                for (int i = 0; i < dataList.size(); i++) {
                    newDataList.add(dataList.get(i));
                }
                newDataList.add("");
                mAdapter.emptyData();
                mAdapter.appendData(newDataList);
            } else {
                toast("请输入地址再次添加...");
            }
        } else if (id == R.id.rl_professional_type) {
            
        } else if (id == R.id.but_submit) {

        }
    }


}
