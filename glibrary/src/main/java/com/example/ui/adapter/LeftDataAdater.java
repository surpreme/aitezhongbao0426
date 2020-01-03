package com.example.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.BaseRecyclerViewAdapter;
import com.example.bean.ListBean;
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
public class LeftDataAdater extends BaseRecyclerViewAdapter<ListBean> {

    private Context mContext;

    private int mIndex = -1;

    public LeftDataAdater(Context context) {
        mContext = context;
    }

    public void updateData(@NonNull List<ListBean> dataList, int index) {
        super.updateData(dataList);
        mIndex = index;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_left, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }


    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.view)
        View mView;
        @BindView(R2.id.tv_level1_item)
        TextView mTvLevel1Item;
        @BindView(R2.id.rl_level1_item)
        RelativeLayout mRlLevel1Item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


        @Override
        protected void bindView(ListBean listBeans) {
            mTvLevel1Item.setText(listBeans.getArea_name());
            mView.setSelected(listBeans.getChecked());
            mRlLevel1Item.setSelected(listBeans.getChecked());
            mTvLevel1Item.setSelected(listBeans.getChecked());

            if (getLayoutPosition() != mIndex) {
                List<ListBean.CitylistBean> citylist = listBeans.getCitylist();
                for (int i = 0; i < citylist.size(); i++) {
                    citylist.get(i).setChecked(false);
                }
            }
        }


    }
}
