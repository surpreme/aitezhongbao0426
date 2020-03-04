package com.example.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.BaseRecyclerViewAdapter;
import com.example.bean.OrderListBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.ui.activity.ConsultActivity;
import com.example.ui.activity.EvaluateActivity;
import com.example.Utils.TextUtil;
import com.example.ui.activity.OrdersInfoActivity;
import com.lzy.basemodule.BaseConstant.AppConstant;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/20 14:57
 * 描述:
 */
public class OrderListsAdapter extends BaseRecyclerViewAdapter<OrderListBean.DatasBean> {
    private Context mContext;

    public OrderListsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));

    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.tv_name)
        TextView mTvName;
        @BindView(R2.id.tv_type)
        TextView mTvType;
        @BindView(R2.id.tv_price)
        TextView mTvPrice;
        @BindView(R2.id.tv_total)
        TextView mTvTotal;
        @BindView(R2.id.tv_type1)
        TextView mTvType1;
        @BindView(R2.id.tv_type2)
        TextView mTvType2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
//            mTvType1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

            mTvType2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, EvaluateActivity.class);
                    mContext.startActivity(mIntent);
                }
            });
        }

        @Override
        protected void bindView(OrderListBean.DatasBean datasBean) {
            mTvType1.setOnClickListener(v -> {
                //订单详情  c_order_id
                String cOrderId = datasBean.getC_order_id();
                Intent intent = new Intent(mContext, OrdersInfoActivity.class);
                intent.putExtra( "OrderId", cOrderId);
                mContext.startActivity(intent);
            });
            //名字
            mTvName.setText(datasBean.getGoods_name());

            //类型 order_state 订单状态,0为已取消,1为未支付,2为已支付,3为已完成
            String text = "";
            String order_state = datasBean.getOrder_state();
            switch (order_state) {
                case "0":
                    mTvType.setText("已取消");
                    break;
                case "1":
                    mTvType.setText("未支付");
                    text = "咨询未开始";
                    break;
                case "2":
                    mTvType.setText("已支付");

                    text = "咨询未开始";
                    break;
                case "3":
                    mTvType.setText("已完成");
                    String total = "共咨询" + datasBean.getConsult_time() + "分钟 合计:" + datasBean.getOrder_price() + "元";
                    text = TextUtil.highlight(mContext, total, datasBean.getOrder_price() + "元", "#F4EA2A", 0, 0).toString();
                    break;
            }

            mTvTotal.setText(text);

            //价格
            String price = datasBean.getOrder_price() + "元/" + datasBean.getConsult_time() + "分钟";
            mTvPrice.setText("￥" + price);

            //描述 tv_total
            //   mTvReplyNum.setText(TextUtil.highlight(mContext, "回复速度null", "null", "#F4EA2A", 0, 0));

            // todo 医生功能键只有查询订单
            mTvType1.setVisibility(View.VISIBLE);
            mTvType1.setText("查看详情");

//            if (AppConstant.CURRENT_IDENTITY == 2) {
//
//            } else if (AppConstant.CURRENT_IDENTITY == 1) {
//                switch (order_state){
//                    case "1":
//                        //未支付
//                        mTvType1.setVisibility(View.VISIBLE);
//                        mTvType1.setText("查看详情");
//
//
//
//                        break;
//                }
//            }
        }
    }
}
