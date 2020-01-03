package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.SystemMsgBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.base.BaseRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/16 14:36
 * 描述:
 */
public class SyStemMsgAdapter extends BaseRecyclerViewAdapter<SystemMsgBean.MessageArrayBean> {

    private Context mContext;

    public SyStemMsgAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_system_recy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.title_tv)
        TextView titleTv;
        @BindView(R2.id.time_tv)
        TextView timeTv;
        @BindView(R2.id.information_tv)
        TextView informationTv;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         * /**
         * * message_id : 178
         * * message_parent_id : 0
         * * from_member_id : 0
         * * to_member_id : 7
         * * message_title : null
         * * message_body : 你的账户于 2019-12-26 09:51:53 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 950630669113310007，可用金额变化 ：-1.00元，冻结金额变化：1.00元。
         * * message_time : 1577325113
         * * message_update_time : 1577325113
         * * message_open : 0
         * * message_state : 0
         * * message_type : 1
         * * read_member_id :
         * * del_member_id :
         * * message_ismore : 0
         * * from_member_name : 系统消息
         * * to_member_name :
         * * message_time_text : 2019-12-26
         *
         * @param messageArrayBean
         */

        @Override
        protected void bindView(SystemMsgBean.MessageArrayBean messageArrayBean) {
            titleTv.setText(messageArrayBean.getFrom_member_name());
            informationTv.setText(messageArrayBean.getMessage_body());
            timeTv.setText(messageArrayBean.getMessage_time_text());
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
