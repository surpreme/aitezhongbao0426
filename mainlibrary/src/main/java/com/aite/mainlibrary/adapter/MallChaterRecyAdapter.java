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

import com.aite.mainlibrary.Mainbean.MallChaterBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MallChaterRecyAdapter extends RecyclerView.Adapter<MallChaterRecyAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<MallChaterBean.PlatformCallcenterBean> platformCallcenterBeans;

    public MallChaterRecyAdapter(Context context, List<MallChaterBean.PlatformCallcenterBean> platformCallcenterBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.platformCallcenterBeans = platformCallcenterBeans;
    }

    private OnClickLstenerInterface.OnItemRecyClickInterface onItemRecyClickInterface;

    public void setOnClickThingInterface(OnClickLstenerInterface.OnItemRecyClickInterface onItemRecyClickInterface) {
        this.onItemRecyClickInterface = onItemRecyClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_malltalker, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.tvMsg.setText(platformCallcenterBeans.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return platformCallcenterBeans == null ? 0 : platformCallcenterBeans.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
