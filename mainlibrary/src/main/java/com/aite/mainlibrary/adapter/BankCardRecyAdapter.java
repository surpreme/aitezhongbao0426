package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.MineBankListBean;
import com.aite.mainlibrary.R;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class BankCardRecyAdapter extends RecyclerView.Adapter<BankCardRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<MineBankListBean.BankListBean> bankListBeans;

    public BankCardRecyAdapter(Context context, List<MineBankListBean.BankListBean> bankListBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.bankListBeans = bankListBeans;
    }

    @NonNull
    @Override
    public BankCardRecyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_bank_carlayout, parent, false);
        BankCardRecyAdapter.ViewHolder viewHolder = new BankCardRecyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BankCardRecyAdapter.ViewHolder holder, int position) {
        holder.tv_bankname.setText(bankListBeans.get(position).getBank_name());
        holder.tv_banknumber.setText(bankListBeans.get(position).getBank_no());
//        holder.swipeMenuLayout
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemRecyClickInterface.getPosition(position);
            }
        });

    }

    private OnClickLstenerInterface.OnItemRecyClickInterface onItemRecyClickInterface;

    public void setOnItemRecyClickInterface(OnClickLstenerInterface.OnItemRecyClickInterface onItemRecyClickInterface) {
        this.onItemRecyClickInterface = onItemRecyClickInterface;
    }

    @Override
    public int getItemCount() {
        return bankListBeans == null ? 0 : bankListBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_bankname, tv_banknumber;
        private SwipeMenuLayout swipeMenuLayout;
        private TextView tv_delete;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_bankname = itemView.findViewById(R.id.tv_item_bankname);
            tv_banknumber = itemView.findViewById(R.id.tv_banknumber);
            swipeMenuLayout = itemView.findViewById(R.id.swipeMenuLayout);
            tv_delete = itemView.findViewById(R.id.tv_delete);

        }
    }
}
