package com.aite.mainlibrary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.minerural.MineRuralPushListBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.basemodule.base.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/16 14:36
 * 描述:
 */
public class MineRuralPushRecyAdapter extends BaseRecyclerViewAdapter<MineRuralPushListBean.ListBean> {
    private Context mContext;

    public MineRuralPushRecyAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recy_mine_collectage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }


    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.user_icon)
        ImageView userIcon;
        @BindView(R2.id.user_name_tv)
        TextView userNameTv;
        @BindView(R2.id.title_tv)
        TextView titleTv;
        @BindView(R2.id.information_tv)
        TextView informationTv;
        @BindView(R2.id.look_tv)
        TextView lookTv;
        @BindView(R2.id.sendothers_tv)
        TextView sendothersTv;
        @BindView(R2.id.talk_tv)
        TextView talkTv;
        @BindView(R2.id.nice_tv)
        TextView niceTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         * 返回字段	类型	说明
         * datas->list_total	整型	总页数
         * datas->is_nextpage	整型	是否有下一页
         * datas->list[]	数组	服务列表
         * datas->list[]->theme_id	整型	话题ID
         * datas->list[]->theme_name	字符串	话题名称
         * datas->list[]->theme_content	字符串	话题内容
         * datas->list[]->member_avatar	字符串	头像
         * datas->list[]->member_name	字符串	发布人姓名
         * datas->list[]->theme_browsecount	字符串	浏览数量
         * datas->list[]->theme_addtime	字符串	发布时间
         * datas->list[]->h5_url	字符串	跳转到h5的链接
         * datas->list[]->if_like	字符串	是否可以点赞 1是
         * error	字符串	错误信息 code=200 正确 其他编码错误
         *
         * @param mineRuralPuList
         */
        @SuppressLint("ResourceAsColor")
        @Override
        protected void bindView(MineRuralPushListBean.ListBean mineRuralPuList) {
            userNameTv.setText(mineRuralPuList.getMember_name());
            titleTv.setText(mineRuralPuList.getTheme_name());
            informationTv.setText(mineRuralPuList.getTheme_content());
            lookTv.setText(String.format("浏览量 %s", mineRuralPuList.getTheme_browsecount()));
            niceTv.setTextColor(mineRuralPuList.getIf_like() == 1 ? R.color.noyelllow : R.color.black);
            Glide.with(mContext).load(mineRuralPuList.getMember_avatar()).apply(RequestOptions.circleCropTransform()).into(userIcon);
        }

    }
}
