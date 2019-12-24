package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.base.BaseRecyclerViewAdapter;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 创建时间 2019/12/16 14:36
 * 描述:
 */
public class QuestionAdapter extends BaseRecyclerViewAdapter<String> {

    private Context mContext;

    public QuestionAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment_question, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.user_icon)
        CircleImageView mUserIcon;
        @BindView(R2.id.tv_name)
        TextView mTvName;
        @BindView(R2.id.tv_reply)
        TextView mTvReply;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void bindView(String s) {


            Glide.with(mContext).load(mContext.getResources().getDrawable(R.mipmap.orange_else)).into(mUserIcon);

        }
    }
}
