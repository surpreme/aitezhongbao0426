package com.example.ui.view;

import android.content.Context;
import android.util.Log;

import com.example.base.OnClickRecyclerViewListener;
import com.example.bean.DepartmentBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.ui.adapter.DepartmentAdapter;
import com.example.ui.adapter.RightDataAdapter;
import com.lxj.xpopup.impl.PartShadowPopupView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/31 10:41
 * 描述:
 */
public class CustomDepartmentPopup extends PartShadowPopupView {

    @BindView(R2.id.recy_view)
    RecyclerView mRecyView;
    private Context mContext;

    private DepartmentAdapter mAdapter;

    private List<DepartmentBean> mList;

    private int indexes = 0;

    private onClick mOnClick;

    public onClick getOnClick() {
        return mOnClick;
    }

    public void setOnClick(onClick onClick) {
        mOnClick = onClick;
    }

    public CustomDepartmentPopup(@NonNull Context context, List<DepartmentBean> list) {
        super(context);
        mContext = context;
        mList = list;

    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_department;
    }


    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mAdapter = new DepartmentAdapter(mContext);
        mRecyView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter.updateData(mList);
        mRecyView.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                indexes = position;
                if (mOnClick != null) {
                    mOnClick.getPosition(mAdapter.getDataList().get(position).getText());
                    List<DepartmentBean> dataList = new ArrayList<>();
                    dataList.addAll(mAdapter.getDataList());
                    for (int i = 0; i < dataList.size(); i++) {
                        dataList.get(i).setIndexes(indexes);
                    }
                    mAdapter.updateData(dataList);
                }
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

    }

    public interface onClick {
        void getPosition(String text);
    }
}
