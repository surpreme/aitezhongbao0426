package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.alldoctor.ConsultActivity;
import com.aite.mainlibrary.activity.alldoctor.EvaluateActivity;
import com.aite.mainlibrary.base.BaseRecyclerViewAdapter;
import com.lzy.basemodule.util.TextUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/20 14:57
 * 描述:
 */
public class OrderListsAdapter extends BaseRecyclerViewAdapter<String> {
    private Context mContext;

    public OrderListsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.tv_name)
        TextView mTvName;
        @BindView(R2.id.tv_type)
        TextView mTvType;
        @BindView(R2.id.tv_price)
        TextView mTvPrice;
        @BindView(R2.id.tv_total)
        TextView mTvTotal;
        @BindView(R2.id.tv_type1)
        TextView mTvType1;
        @BindView(R2.id.tv_type2)
        TextView mTvType2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


            mTvType1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2019/12/20  价格咨询
                    Intent mIntent = new Intent(mContext, ConsultActivity.class);
                    mContext.startActivity(mIntent);
                }
            });


            mTvType2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, EvaluateActivity.class);
                    mContext.startActivity(mIntent);
                }
            });



        }

        @Override
        protected void bindView(String s) {
            mTvTotal.setText(TextUtil.highlight(mContext, "共咨询20分钟 合计：131元", "131元", "#FFBC21", 0, 0));
        }
    }
}
