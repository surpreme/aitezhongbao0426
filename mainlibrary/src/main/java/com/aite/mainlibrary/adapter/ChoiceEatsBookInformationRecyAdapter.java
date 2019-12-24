package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.ChioceEatBookinformationBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.OnClickLstenerInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChoiceEatsBookInformationRecyAdapter extends RecyclerView.Adapter<ChoiceEatsBookInformationRecyAdapter.ViewHolder> {

    private Context context;
    private List<ChioceEatBookinformationBean.OrderInfoBean.ExtendOrderGoodsBean> extendOrderGoodsBeans;


    public ChoiceEatsBookInformationRecyAdapter(Context context, List<ChioceEatBookinformationBean.OrderInfoBean.ExtendOrderGoodsBean> extendOrderGoodsBeans) {
        this.context = context;
        this.extendOrderGoodsBeans = extendOrderGoodsBeans;

    }

    private OnClickLstenerInterface.OnItemRecyClickInterface onItemRecyClickInterface;

    public void setOnItemRecyClickInterface(OnClickLstenerInterface.OnItemRecyClickInterface onItemRecyClickInterface) {
        this.onItemRecyClickInterface = onItemRecyClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chreniditem_thingbook_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(extendOrderGoodsBeans.get(position).getImage_60_url()).into(holder.iconIv);
        holder.titleTv.setText(String.format("%s  x%s", extendOrderGoodsBeans.get(position).getGoods_name(), extendOrderGoodsBeans.get(position).getGoods_num()));
        holder.priceTv.setText(String.format("共计￥ %s", extendOrderGoodsBeans.get(position).getGoods_pay_price()));
        holder.bookDisputeTv.setVisibility(extendOrderGoodsBeans.get(position).getIf_complain() == 1 ? View.VISIBLE : View.GONE);
        holder.bookDisputeTv.setOnClickListener(v -> {
            if (onItemRecyClickInterface != null) onItemRecyClickInterface.getPosition(position);

        });

    }

    @Override
    public int getItemCount() {
        return extendOrderGoodsBeans == null ? 0 : extendOrderGoodsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.icon_iv)
        ImageView iconIv;
        @BindView(R2.id.title_tv)
        TextView titleTv;
        @BindView(R2.id.information_tv)
        TextView informationTv;
        @BindView(R2.id.price_tv)
        TextView priceTv;
        @BindView(R2.id.book_dispute_tv)
        TextView bookDisputeTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
