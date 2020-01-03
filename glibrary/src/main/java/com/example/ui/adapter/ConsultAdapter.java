package com.example.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.base.BaseRecyclerViewAdapter;
import com.example.bean.ConsultBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/16 14:36
 * 描述:
 */
public class ConsultAdapter extends BaseRecyclerViewAdapter<ConsultBean> {

    private Context mContext;

    public ConsultAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R2.id.tv_type)
        TextView mTvType;
        @BindView(R2.id.tv_content)
        TextView mTvContent;
        @BindView(R2.id.tv_price)
        TextView mTvPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void bindView(ConsultBean consultBean) {
            int imageResId = getImageResId(consultBean.getPic());
            Glide.with(mContext).load(imageResId).into(mIvIcon);

            mTvType.setText(consultBean.getType());
            mTvContent.setText(consultBean.getConsultTime());

            mTvPrice.setText(consultBean.getPrice());
        }

        /**
         * 获取mipmap包下的图片Id
         * @param imageNumber
         * @return
         */
        public int getImageResId(String imageNumber) {
            return mContext.getResources().getIdentifier(imageNumber, "mipmap", mContext.getPackageName());
        }

    }
}
