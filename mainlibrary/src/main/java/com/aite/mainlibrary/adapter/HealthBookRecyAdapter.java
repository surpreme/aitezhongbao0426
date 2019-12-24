package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.HealthListBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.OnClickLstenerInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-04
 * @desc:
 */
public class HealthBookRecyAdapter extends RecyclerView.Adapter<HealthBookRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<HealthListBean.DatasBean> datasBeans;

    public HealthBookRecyAdapter(Context context, List<HealthListBean.DatasBean> datasBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.datasBeans = datasBeans;
    }

    public interface OnClickThingInterface {
        void onDelete(String position);

        void onedit(String position);
    }

    private AdrressFixRecyAdapter.OnClickThingInterface onClickThingInterface;

    public void setOnClickThingInterface(AdrressFixRecyAdapter.OnClickThingInterface onClickThingInterface) {
        this.onClickThingInterface = onClickThingInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_health_book_recy, parent, false);
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
        holder.titleTv.setText(datasBeans.get(position).getName());
        holder.timeTv.setText(datasBeans.get(position).getTime());
        holder.informationTv.setText(datasBeans.get(position).getDescription());

        holder.tvDelete.setOnClickListener(v -> {
            if (onClickThingInterface != null)
                onClickThingInterface.onDelete(String.valueOf(position));
        });
        holder.tvEdit.setOnClickListener(v -> {
            if (onClickThingInterface != null)
                onClickThingInterface.onedit(String.valueOf(position));
        });
        holder.fatherLl.setOnClickListener(v -> clickInterface.getPosition(position));

    }

    @Override
    public int getItemCount() {
        return datasBeans == null ? 0 : datasBeans.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.title_tv)
        TextView titleTv;
        @BindView(R2.id.information_tv)
        TextView informationTv;
        @BindView(R2.id.time_tv)
        TextView timeTv;
        @BindView(R2.id.information_iv)
        ImageView informationIv;
        @BindView(R2.id.father_ll)
        LinearLayoutCompat fatherLl;
        @BindView(R2.id.tv_edit)
        TextView tvEdit;
        @BindView(R2.id.tv_delete)
        TextView tvDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
