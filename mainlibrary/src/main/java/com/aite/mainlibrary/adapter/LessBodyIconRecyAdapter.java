package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.MainUiDataBean;
import com.aite.mainlibrary.R;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.OnClickLstenerInterface;

import java.util.List;


public class LessBodyIconRecyAdapter extends RecyclerView.Adapter<LessBodyIconRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<MainUiDataBean.DisAdvsBean> disAdvsBeans;

    public LessBodyIconRecyAdapter(Context context, List<MainUiDataBean.DisAdvsBean> disAdvsBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.disAdvsBeans = disAdvsBeans;
    }

    @NonNull
    @Override
    public LessBodyIconRecyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.help_elder, parent, false);
        LessBodyIconRecyAdapter.ViewHolder viewHolder = new LessBodyIconRecyAdapter.ViewHolder(view);
        return viewHolder;
    }

    public OnClickLstenerInterface.OnRecyClickInterface getLstenerInterface() {
        return lstenerInterface;
    }

    public void setLstenerInterface(OnClickLstenerInterface.OnRecyClickInterface lstenerInterface) {
        this.lstenerInterface = lstenerInterface;
    }

    private OnClickLstenerInterface.OnRecyClickInterface lstenerInterface;

    /**
     * adv_content : {"adv_pic":"http://zhongbyi.aitecc.com/data/upload/shop/adv/06269595991492265.png","adv_pic_url":""}
     * adv_title : 助餐
     * adv_desc :
     * page_pension : 1
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(disAdvsBeans.get(position).getAdv_title());
        Glide.with(context).
                load(disAdvsBeans.get(position).getAdv_content().getAdv_pic())
                .into(holder.backgroundimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstenerInterface.getPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return disAdvsBeans == null ? 0 : disAdvsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView backgroundimg;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundimg = itemView.findViewById(R.id.background_img);
            title = itemView.findViewById(R.id.text);

        }
    }
}
