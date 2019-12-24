package com.aite.a.activity.li.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.li.bean.ZhongbaoCollectBean;
import com.aiteshangcheng.a.R;
import com.aiteshangcheng.a.R2;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhongBaoCollectServiceAdapter extends RecyclerView.Adapter<ZhongBaoCollectServiceAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<ZhongbaoCollectBean.DatasBean.FavoritesListBean> mDatas;

    public ZhongBaoCollectServiceAdapter(Context context, List<ZhongbaoCollectBean.DatasBean.FavoritesListBean> datas) {
        //这里适配器是写给主活动互相调用的方法
        this.context = context;
        this.mDatas = datas;
        this.inflater = LayoutInflater.from(context);

    }

    public OnclickInterface getOnclickInterface() {
        return onclickInterface;
    }

    public void setOnclickInterface(OnclickInterface onclickInterface) {
        this.onclickInterface = onclickInterface;
    }

    private OnclickInterface onclickInterface;

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.zhongbao_item_collet, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        String currentStrLetter = mDatas.get(position).getBrand_name().charAt(0) + "";
        Glide.with(context).load(mDatas.get(position).getGoods_image_url()).into(holder.guseeLikeImg);
        holder.goodsNameTv.setText(mDatas.get(position).getGoods_name());
        holder.goodInformationTv.setText(String.format("￥%s", mDatas.get(position).getGoods_price()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onclickInterface.getPostion(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.gusee_like_img)
        ImageView guseeLikeImg;
        @BindView(R2.id.goods_name_tv)
        TextView goodsNameTv;
        @BindView(R2.id.good_information_tv)
        TextView goodInformationTv;
        @BindView(R2.id.time_tv)
        TextView timeTv;
        @BindView(R2.id.father_layout)
        LinearLayout fatherLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public interface OnclickInterface {
        void getPostion(int postion);
    }

}
