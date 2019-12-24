package com.lzy.basemodule.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.R;
import com.lzy.basemodule.bean.BankNameListBean;

import java.util.List;


public class BankNameListBaseRecyAdapter extends RecyclerView.Adapter<BankNameListBaseRecyAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<BankNameListBean> bankNameListBean;

    public BankNameListBaseRecyAdapter(Context context, List<BankNameListBean> bankNameListBean) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.bankNameListBean = bankNameListBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.text, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private OnClickLstenerInterface.OnRecyClickInterface clickInterface;

    public OnClickLstenerInterface.OnRecyClickInterface getClickInterface() {
        return clickInterface;
    }

    public void setClickInterface(OnClickLstenerInterface.OnRecyClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(bankNameListBean.get(position).getText());
        holder.itemView.setAlpha(0.8f);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.getPosition(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bankNameListBean == null ? 0 : bankNameListBean.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);


        }
    }
}
