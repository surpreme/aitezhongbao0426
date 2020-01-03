package com.example.ui.view;

import android.content.Context;

import com.example.base.OnClickRecyclerViewListener;
import com.example.bean.LabelBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.ui.adapter.SortAdapter;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.impl.PartShadowPopupView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/31 15:41
 * 描述:
 */
public class SortPopup extends PartShadowPopupView {

    @BindView(R2.id.recy_view)
    RecyclerView mRecyView;
    private Context mContext;

    private SortAdapter mAdapter;

    private List<LabelBean> mData;

    private onClickSortListener mOnClickSortListener;

    public onClickSortListener getOnClickSortListener() {
        return mOnClickSortListener;
    }

    public void setOnClickSortListener(onClickSortListener onClickSortListener) {
        mOnClickSortListener = onClickSortListener;
    }

    public SortPopup(@NonNull Context context, List<LabelBean> data) {
        super(context);
        mContext = context;
        mData = data;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_sort;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        mAdapter = new SortAdapter(mContext);
        mRecyView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyView.setAdapter(mAdapter);
        mAdapter.updateData(mData);
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                //  List<LabelBean> dataList = mAdapter.getDataList();
                for (int i = 0; i < mData.size(); i++) {
                    mData.get(i).setSelected(position == i);
                }
                mAdapter.updateData(mData);
                mOnClickSortListener.getSortData(mData.get(position).getText(), position);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }


   public interface onClickSortListener {
        void getSortData(String text, int position);
    }

}


