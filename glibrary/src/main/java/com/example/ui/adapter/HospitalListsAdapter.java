package com.example.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.BaseRecyclerViewAdapter;
import com.example.glibrary.R;
import com.example.glibrary.R2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/11 20:23
 * 描述:
 */
public class HospitalListsAdapter extends BaseRecyclerViewAdapter<String> {

    private Context mContext;

    public HospitalListsAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hospital, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }


    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.tv_num)
        TextView mTvNum;
        @BindView(R2.id.tv_hospital)
        TextView mTvHospital;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void bindView(String s) {

            mTvHospital.setText(s);

            mTvNum.setText(String.valueOf(getLayoutPosition() + 1));
            mTvNum.setBackgroundResource(getLayoutPosition() % 2 == 0 ? R.color.green1: R.color.yellow);
        }
    }
}
