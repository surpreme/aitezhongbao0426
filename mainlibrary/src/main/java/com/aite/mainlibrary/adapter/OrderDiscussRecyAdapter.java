package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.basemodule.base.BaseRecyclerViewAdapter;
import com.lzy.basemodule.bean.OrderDiscussBean;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/16 14:36
 * 描述:
 */
public class OrderDiscussRecyAdapter extends BaseRecyclerViewAdapter<OrderDiscussBean.GoodsevallistBean> {
    private Context mContext;

    public OrderDiscussRecyAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_oder_disuss_recy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.talk_user_icon)
        ImageView talkUserIcon;
        @BindView(R2.id.talk_name_tv)
        TextView talkNameTv;
        @BindView(R2.id.talk_state_tv)
        TextView talkStateTv;
        @BindView(R2.id.talk_information_tv)
        TextView talkInformationTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         * goodsevallist	数组	评论信息数组
         * goodsevallist.geval_scores	整型	评分
         * goodsevallist.geval_scores_text	字符串	评分文字
         * goodsevallist.geval_content	字符串	评论内容
         * goodsevallist.geval_frommembername	字符串	评论会员名称
         * goodsevallist.geval_member_avatar	字符串	评论会员头像
         *
         * @param goodsevallistBean
         */
        @Override
        protected void bindView(OrderDiscussBean.GoodsevallistBean goodsevallistBean) {
            Glide.with(mContext).load(goodsevallistBean.getGeval_frommemberavatar()).apply(RequestOptions.circleCropTransform()).into(talkUserIcon);
            talkNameTv.setText(goodsevallistBean.getGeval_frommembername());
            talkInformationTv.setText(goodsevallistBean.getGeval_content());
            talkStateTv.setText(goodsevallistBean.getGeval_addtime());
        }

        /**
         * 获取mipmap包下的图片Id
         *
         * @param imageNumber
         * @return
         */
        public int getImageResId(String imageNumber) {
            return mContext.getResources().getIdentifier(imageNumber, "mipmap", mContext.getPackageName());
        }

    }
}
