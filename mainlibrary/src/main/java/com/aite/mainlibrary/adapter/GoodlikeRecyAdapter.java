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

import com.aite.mainlibrary.Mainbean.NewsBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.OnClickLstenerInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-04
 * @desc:
 */
public class GoodlikeRecyAdapter extends RecyclerView.Adapter<GoodlikeRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<NewsBean.DatasBean.ListBean> datasBeans;

    public GoodlikeRecyAdapter(Context context, List<NewsBean.DatasBean.ListBean> datasBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.datasBeans = datasBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recy_goodbackground, parent, false);
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
//        holder.text.setText(datasBeans.get(position).getTitle());
        Glide.with(context).load(datasBeans.get(position).getCircle_img()).into(holder.icon);
        holder.titleTv.setText(String.format("圈子  %s", datasBeans.get(position).getCircle_name()));
        holder.informationTv.setText(String.format("话题 %s组员 %s", datasBeans.get(position).getCircle_mcount(),
                datasBeans.get(position).getNew_informcount()));
        holder.informationTv.setText(datasBeans.get(position).getCircle_desc());
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
        return datasBeans == null ? 0 : datasBeans.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.icon)
        ImageView icon;
        @BindView(R2.id.title_tv)
        TextView titleTv;
        @BindView(R2.id.gotogether_btn)
        TextView gotogetherBtn;
        @BindView(R2.id.information_tv)
        TextView informationTv;
        @BindView(R2.id.more_information_tv)
        TextView moreInformationTv;
        @BindView(R2.id.father_layout)
        LinearLayout fatherLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
