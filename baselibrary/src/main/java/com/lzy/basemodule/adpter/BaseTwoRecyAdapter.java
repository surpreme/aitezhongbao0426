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

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-14
 * @desc:
 */
public class BaseTwoRecyAdapter extends RecyclerView.Adapter<BaseTwoRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<String> messages;
    private int mSelectedPosition = 0;

    public BaseTwoRecyAdapter(Context context, List<String> messages) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.messages = messages;
    }

    public void addDatas(List<String> messages) {
        if (this.messages == null)
            this.messages = new ArrayList<>();
        this.messages.addAll(messages);
        notifyDataSetChanged();
    }

    public void clearDatas() {
        if (this.messages != null) {
            if (!this.messages.isEmpty()) {
                this.messages.clear();
                notifyDataSetChanged();
            }
        } else {
            this.messages = new ArrayList<>();
        }
    }
    public void refreshSelected(int position) {
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.text, parent, false));
    }

    private OnClickLstenerInterface.OnRecyClickInterface clickInterface;


    public void setClickInterface(OnClickLstenerInterface.OnRecyClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (position == mSelectedPosition) {
            holder.text.setBackgroundColor(context.getResources().getColor(R.color.blue));
            holder.text.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.text.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.text.setTextColor(context.getResources().getColor(R.color.black));
        }
        holder.text.setText(messages.get(position));
        holder.itemView.setOnClickListener(v -> {
            if (clickInterface != null)
                clickInterface.getPosition(position);
        });

    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
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
