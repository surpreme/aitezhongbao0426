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
import com.lzy.basemodule.bean.IImgBaseBean;
import com.lzy.basemodule.bean.ImBaseBean;

import java.util.List;


public class TextWithIdBaseRecyAdapter extends RecyclerView.Adapter<TextWithIdBaseRecyAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<? extends ImBaseBean> imBaseBeans;

    public TextWithIdBaseRecyAdapter(Context context, List<? extends ImBaseBean> imBaseBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.imBaseBeans = imBaseBeans;
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
        holder.text.setText(imBaseBeans.get(position).getNasme());
        holder.itemView.setAlpha(0.8f);
        holder.itemView.setOnClickListener(v -> {
            if (clickInterface != null)
                clickInterface.getPosition(position);
        });

    }

    @Override
    public int getItemCount() {
        return imBaseBeans == null ? 0 : imBaseBeans.size();
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
