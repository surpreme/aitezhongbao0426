package com.example.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.BaseRecyclerViewAdapter;
import com.example.bean.DepartmentBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/30 15:58
 * 描述:
 */
public class DepartmentAdapter extends BaseRecyclerViewAdapter<DepartmentBean> {


    private Context mContext;

    public DepartmentAdapter(Context context) {
        mContext = context;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_right, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.tv_level1_item)
        TextView mTvLevel1Item;
        @BindView(R2.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R2.id.rl_level1_item)
        RelativeLayout mRlLevel1Item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void bindView(DepartmentBean departmentBean) {
            mTvLevel1Item.setText(departmentBean.getText());
            mTvLevel1Item.setSelected(departmentBean.getIndexes() == getLayoutPosition());
            mIvIcon.setVisibility(departmentBean.getIndexes() == getLayoutPosition() ? View.VISIBLE : View.GONE);
            mRlLevel1Item.setSelected(departmentBean.getIndexes() == getLayoutPosition());
        }


    }
}
