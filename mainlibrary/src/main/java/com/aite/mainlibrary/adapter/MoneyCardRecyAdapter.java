package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.MoneyCollectBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.OnClickLstenerInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoneyCardRecyAdapter extends RecyclerView.Adapter<MoneyCardRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private int[] imgs;
    private String[] names;
    private OnClickLstenerInterface.OnRecyClickInterface onRecyClickInterface;
    private MoneyCollectBean moneyCollectBean;

    public OnClickLstenerInterface.OnRecyClickInterface getOnRecyClickInterface() {
        return onRecyClickInterface;
    }

    public void setOnRecyClickInterface(OnClickLstenerInterface.OnRecyClickInterface onRecyClickInterface) {
        this.onRecyClickInterface = onRecyClickInterface;
    }

    public MoneyCardRecyAdapter(Context context, int[] imgs, String[] names, MoneyCollectBean moneyCollectBean) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.imgs = imgs;
        this.names = names;
        this.moneyCollectBean = moneyCollectBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.itemmine_money_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (moneyCollectBean != null) {
            if (position == 0) {
                if (moneyCollectBean.getCash_info() != null) {
                    if (moneyCollectBean.getCash_info().getBank_name() != null)
                        holder.priceTv.setText(moneyCollectBean.getCash_info().getBank_name());
                    if (moneyCollectBean.getCash_info().getPdc_amount() != null)
                        holder.informationTv.setText(moneyCollectBean.getCash_info().getPdc_amount());
                    if (moneyCollectBean.getCash_info().getPdc_payment_time() != null)
                        holder.timeTv.setText(moneyCollectBean.getCash_info().getPdc_payment_time());
                }
            } else if (position == 1) {
                if (moneyCollectBean.getRecharge_info() != null) {
                    if (moneyCollectBean.getRecharge_info().getPdr_payment_name() != null)
                        holder.priceTv.setText(moneyCollectBean.getRecharge_info().getPdr_payment_name());
                    if (moneyCollectBean.getRecharge_info().getPdc_amount() != null)
                        holder.informationTv.setText(moneyCollectBean.getRecharge_info().getPdc_amount());
                    if (moneyCollectBean.getRecharge_info().getPdc_payment_time() != null)
                        holder.timeTv.setText(moneyCollectBean.getRecharge_info().getPdc_payment_time());
                }
            } else if (position == 2) {
                if (moneyCollectBean.getLog_info() != null) {
                    if (moneyCollectBean.getLog_info().getLg_av_amount() != null)
                        holder.priceTv.setText(moneyCollectBean.getLog_info().getLg_av_amount());
                    if (moneyCollectBean.getLog_info().getLg_desc() != null)
                        holder.informationTv.setText(moneyCollectBean.getLog_info().getLg_desc());
                    if (moneyCollectBean.getLog_info().getLg_add_time() != null)
                        holder.timeTv.setText(moneyCollectBean.getLog_info().getLg_add_time());
                }
            } else {
                holder.priceTv.setText("");
                holder.informationTv.setText("");
                holder.informationTv.setVisibility(View.GONE);
                holder.timeTv.setVisibility(View.GONE);
            }
        }

        holder.icon.setImageResource(imgs[position]);
        holder.typeTv.setText(names[position]);
        holder.fatherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyClickInterface.getPosition(position);

            }
        });
//        holder.tv_bankname.setText(banknames.get(position));
//        holder.tv_banknumber.setText(banknumbers.get(position));
//        holder.swipeMenuLayout

    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.length;
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.icon)
        ImageView icon;
        @BindView(R2.id.type_tv)
        TextView typeTv;
        @BindView(R2.id.father_layout)
        LinearLayout fatherLayout;
        @BindView(R2.id.price_tv)
        TextView priceTv;
        @BindView(R2.id.information_tv)
        TextView informationTv;
        @BindView(R2.id.time_tv)
        TextView timeTv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setData(MoneyCollectBean moneyCollectBean) {
        this.moneyCollectBean = moneyCollectBean;
        notifyDataSetChanged();
    }
}
