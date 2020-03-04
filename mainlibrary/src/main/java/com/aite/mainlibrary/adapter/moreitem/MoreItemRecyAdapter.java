package com.aite.mainlibrary.adapter.moreitem;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.ChioceEatBookListBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.TimeUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreItemRecyAdapter extends RecyclerView.Adapter<MoreItemRecyAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    public static final int ITEM_TYPE = 100;
    private List<ChioceEatBookListBean.DatasBean.OrderGroupListBean.OrderListBean> mDatas;

    public MoreItemRecyAdapter(Context context, List<ChioceEatBookListBean.DatasBean.OrderGroupListBean.OrderListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE;
    }


    public void setOnclickInterface(OnclickInterface onclickInterface) {
        this.onclickInterface = onclickInterface;
    }

    private OnClickLstenerInterface.OnThingClickInterface onThingClickInterface;

    public void setOnThingClickInterface(OnClickLstenerInterface.OnThingClickInterface onThingClickInterface) {
        this.onThingClickInterface = onThingClickInterface;
    }

    private OnclickInterface onclickInterface;

    public void setOnPayclickInterface(OnPayclickInterface onPayclickInterface) {
        this.onPayclickInterface = onPayclickInterface;
    }

    private OnPayclickInterface onPayclickInterface;

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recy_choiceinforamtion, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 返回字段	类型	说明
     * order_group_list[].add_time	整型	下单时间
     * order_group_list[].pay_sn	整型	支付单号
     * order_list[].order_id	整型	订单ID
     * order_list[].order_sn	字符串	订单号
     * order_list[].pay_sn	字符串	支付单号
     * order_list[].store_id	整型	店铺编号
     * order_list[].store_name	字符串	店铺名称
     * order_list[].buyer_id	整型	买家会员编号
     * order_list[].buyer_name	字符串	买家会员名
     * order_list[].buyer_email	字符串	买家邮箱
     * order_list[].add_time	整型	下单时间
     * order_list[].payment_code	字符串	支付方式
     * order_list[].payment_time	整型	支付时间
     * order_list[].finnshed_time	整型	完成时间
     * order_list[].goods_amount	浮点型	商品总额
     * order_list[].order_amount	浮点型	订单总额
     * order_list[].shipping_fee	整型	运费
     * order_list[].order_state	整型	订单状态
     * order_list[].state_desc	整型	订单状态描述
     * order_list[].payment_name	整型	支付描述
     * order_list[].if_cancel	整型	显示取消订单
     * order_list[].if_refund_cancel	整型	显示退款取消订单
     * order_list[].if_complain	整型	显示投诉
     * order_list[].if_receive	整型	显示收货
     * order_list[].if_lock	整型	显示锁定中
     * order_list[].if_deliver	整型	显示物流跟踪
     * order_list[].if_evaluation	整型	显示评价
     * order_list[].if_delete	整型	显示删除订单(放入回收站)
     * order_list[].if_drop	整型	显示永久删除
     * order_list[].if_restore	整型	显示还原订单
     * order_list[].if_address	整型	显示修改地址
     * extend_order_goods[].order_lock	整型	订单锁定类型:1为不用锁定,2为需要锁定,默认为1
     * order_list[].goods_state	整型	物流状态:1为待发货,2为待收货,3为未收到,4为已收货,默认为1
     * order_list[].pic_list	数组	凭证图片列表
     * order_list[].goods_image	字符串	商品图片
     * hasmore	bool	是否有下一页
     * page_total	整型	总页数
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.booknumber_tv.setText(String.format("订单号:%s", mDatas.get(position).getOrder_sn()));
        holder.timeTv.setText(TimeUtils.stampToDatemm2(Long.parseLong(mDatas.get(position).getAdd_time())));
        holder.cancelTv.setVisibility(mDatas.get(position).isIf_cancel() ? View.VISIBLE : View.GONE);
        holder.cancelTv.setOnClickListener(v -> {
            if (onThingClickInterface != null)
                onThingClickInterface.getString(mDatas.get(position).getOrder_id());

        });
        holder.payTv.setOnClickListener(v -> {
            if (onPayclickInterface != null) onPayclickInterface.pay(position);
        });
        try {
            int opsds = 0;
            double price = 0.00;
            for (int i = 0; i < mDatas.get(position).getExtend_order_goods().size(); i++) {
                opsds += Integer.parseInt(mDatas.get(position).getExtend_order_goods().get(i).getGoods_num());
                price += Double.valueOf(mDatas.get(position).getExtend_order_goods().get(i).getGoods_price());
            }
            holder.informationTv.setText(String.format("共计%d件商品", opsds));
            holder.priceTv.setText(String.format("￥ %s", haveTwoDouble(price)));
        } catch (Exception e) {
            LogUtils.e(e + "转换出错");
        }

        holder.stateTv.setText(mDatas.get(position).getState_desc());
        holder.list.clear();
        holder.list.addAll(mDatas.get(position).getExtend_order_goods());
        holder.sencondChridenRecyAdapter = new SencondChridenRecyAdapter(context, holder.list);
        LinearLayoutManager layoutManage = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.bookListRecy.setLayoutManager(layoutManage);
        holder.bookListRecy.setAdapter(holder.sencondChridenRecyAdapter);
        holder.itemView.setOnClickListener(v -> {
            if (onclickInterface != null)
                onclickInterface.getPostion(position);
        });

    }

    /**
     * 转换字符为小数后两位
     * 格式化，区小数后两位
     */
    private String haveTwoDouble(double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            return df.format(d);
        } catch (Exception e) {
            LogUtils.e(d);
            return "";
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.booknumber_tv)
        TextView booknumber_tv;
        @BindView(R2.id.state_tv)
        TextView stateTv;
        @BindView(R2.id.time_tv)
        TextView timeTv;
        @BindView(R2.id.book_list_recy)
        RecyclerView bookListRecy;
        @BindView(R2.id.information_tv)
        TextView informationTv;
        @BindView(R2.id.price_tv)
        TextView priceTv;
        @BindView(R2.id.look_information_tv)
        TextView lookInformationTv;
        @BindView(R2.id.talk_tv)
        TextView talkTv;
        @BindView(R2.id.cancel_tv)
        TextView cancelTv;
        @BindView(R2.id.pay_tv)
        TextView payTv;
        @BindView(R2.id.start_eat_tv)
        TextView startEatTv;
        private List<ChioceEatBookListBean.DatasBean.OrderGroupListBean.OrderListBean.ExtendOrderGoodsBean> list = new ArrayList<>();
        private SencondChridenRecyAdapter sencondChridenRecyAdapter;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnclickInterface {
        void getPostion(int postion);
    }

    public interface OnPayclickInterface {
        void pay(int postion);
    }

    public class SencondChridenRecyAdapter extends RecyclerView.Adapter<SencondChridenRecyAdapter.ViewHolder> {

        private Context context;
        private List<ChioceEatBookListBean.DatasBean.OrderGroupListBean.OrderListBean.ExtendOrderGoodsBean> orderListBeans;


        public SencondChridenRecyAdapter(Context context, List<ChioceEatBookListBean.DatasBean.OrderGroupListBean.OrderListBean.ExtendOrderGoodsBean> orderListBeans) {
            this.context = context;
            this.orderListBeans = orderListBeans;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chreniditem_thingbook_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            Glide.with(context).load(orderListBeans.get(position).getGoods_image_url()).into(holder.iconIv);
            holder.titleTv.setText(String.format("%sx%s", orderListBeans.get(position).getGoods_name(), orderListBeans.get(position).getGoods_num()));
            holder.priceTv.setText(String.format("￥ %s", orderListBeans.get(position).getGoods_price()));
            holder.informationTv.setText("");


        }

        @Override
        public int getItemCount() {
            return orderListBeans == null ? 0 : orderListBeans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R2.id.icon_iv)
            ImageView iconIv;
            @BindView(R2.id.title_tv)
            TextView titleTv;
            @BindView(R2.id.information_tv)
            TextView informationTv;
            @BindView(R2.id.price_tv)
            TextView priceTv;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }


}
