package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.BookInfprmationMorningNoonEatBean;
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
public class EatShopBookRecyAdapter extends RecyclerView.Adapter<EatShopBookRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<BookInfprmationMorningNoonEatBean.GoodsListBean> goodsListBeans;

    public EatShopBookRecyAdapter(Context context, List<BookInfprmationMorningNoonEatBean.GoodsListBean> goodsListBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.goodsListBeans = goodsListBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.eat_shop_book_item, parent, false);
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

    /**
     * cart_id : 56
     * buyer_id : 7
     * store_id : 2
     * store_name : 艾特技术
     * goods_id : 13
     * goods_name : 助餐商品
     * goods_price : 0.01
     * goods_num : 10
     * goods_image : 2019/11/22/2_06277498241227640.jpg
     * bl_id : 0
     * cart_type : 1
     * state : true
     * storage_state : true
     * goods_commonid : 13
     * gc_id : 16
     * goods_shipping_fee : 0.00
     * goods_costprice : 0.00
     * transport_id : 0
     * goods_freight : 0.00
     * is_vat : 0
     * goods_vat : 0
     * goods_storage : 100
     * goods_storage_alarm : 0
     * is_fcode : 0
     * have_gift : 0
     * is_more_discount : 0
     * groupbuy_info : null
     * xianshi_info : null
     * miaosha_info : []
     * wholesale_price :
     * goods_points_offset : 0
     * is_visit_comm : 0
     * is_Independent_comm : 0
     * comm_rule : null
     * goods_total : 0.10
     * goods_image_url : http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/22/2_06277498241227640_240.jpg
     * goods_costtotal : 0.00
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.storeNameTv.setText(goodsListBeans.get(position).getStore_name());
        holder.informationTv.setText("服务器无数据");
        holder.titleThingTv.setText(String.format("%sx%s", goodsListBeans.get(position).getGoods_name(), goodsListBeans.get(position).getGoods_num()));
        Glide.with(context).load(goodsListBeans.get(position).getGoods_image_url()).into(holder.icon);
        holder.thingPriceTv.setText(String.format("￥ %s", goodsListBeans.get(position).getGoods_price()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.getPosition(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goodsListBeans == null ? 0 : goodsListBeans.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.icon)
        ImageView icon;
        @BindView(R2.id.store_name_tv)
        TextView storeNameTv;
        @BindView(R2.id.title_thing_tv)
        TextView titleThingTv;
        @BindView(R2.id.information_tv)
        TextView informationTv;
        @BindView(R2.id.thing_price_tv)
        TextView thingPriceTv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
