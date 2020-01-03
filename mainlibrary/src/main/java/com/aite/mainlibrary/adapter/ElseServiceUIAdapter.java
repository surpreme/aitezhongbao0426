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

import com.aite.mainlibrary.Constant.MainUIConstant;
import com.aite.mainlibrary.Mainbean.ElseServiceIconBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.OnClickLstenerInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ElseServiceUIAdapter extends RecyclerView.Adapter<ElseServiceUIAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<ElseServiceIconBean.ListClassBean> elseServiceIconBeans;


    public OnClickLstenerInterface.OnRecyClickInterface getOnClickLstenerInterface() {
        return onClickLstenerInterface;
    }

    public void setOnClickLstenerInterface(OnClickLstenerInterface.OnRecyClickInterface onClickLstenerInterface) {
        this.onClickLstenerInterface = onClickLstenerInterface;
    }

    private OnClickLstenerInterface.OnRecyClickInterface onClickLstenerInterface;

    public ElseServiceUIAdapter(Context context, List<ElseServiceIconBean.ListClassBean> elseServiceIconBeans) {
        this.context = context;
        this.elseServiceIconBeans = elseServiceIconBeans;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.else_service_icon_otem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(elseServiceIconBeans.get(position).getPic()).into(holder.icon);
//        holder.icon.setImageResource(MainUIConstant.ElseHelpConstant.settingImg[position]);
        holder.name.setText(elseServiceIconBeans.get(position).getGc_name());
        holder.fatherLayout.setBackgroundResource(MainUIConstant.ElseHelpConstant.backgrondImg[position]);
        holder.fatherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLstenerInterface.getPosition(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return elseServiceIconBeans == null ? 0 : elseServiceIconBeans.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.icon)
        ImageView icon;
        @BindView(R2.id.name)
        TextView name;
        @BindView(R2.id.father_layout)
        LinearLayout fatherLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
