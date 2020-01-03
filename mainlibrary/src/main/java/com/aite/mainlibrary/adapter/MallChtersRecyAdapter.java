package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.MallChaterBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.base.BaseRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/16 14:36
 * 描述:
 */
public class MallChtersRecyAdapter extends BaseRecyclerViewAdapter<MallChaterBean.PlatformCallcenterBean> {
    private Context mContext;

    public MallChtersRecyAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_malltalker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.user_icon)
        ImageView userIcon;
        @BindView(R2.id.tv_name)
        TextView tvName;
        @BindView(R2.id.tv_msg)
        TextView tvMsg;
        @BindView(R2.id.msg_number_tv)
        TextView msgNumberTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         *
         * @param platformCallcenterBeans
         */
        @Override
        protected void bindView(MallChaterBean.PlatformCallcenterBean platformCallcenterBeans) {
            tvName.setText(platformCallcenterBeans.getName());
            msgNumberTv.setText(platformCallcenterBeans.getNum());
            Glide.with(mContext).load(platformCallcenterBeans.getImg()).into(userIcon);
        }

    }
}
