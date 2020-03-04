package com.example.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Utils.TextUtil;
import com.example.base.BaseRecyclerViewAdapter;
import com.example.bean.DoctorListBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 创建时间 2019/12/11 10:27
 * 描述: 医生列表
 */
public class DoctorListAdapter extends BaseRecyclerViewAdapter<DoctorListBean.DatasBean> {

    private Context mContext;

    public DoctorListAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_doctor, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R2.id.tv_name)
        TextView mTvName;
        @BindView(R2.id.tv_doctor_type)
        TextView mTvDoctorType;
        @BindView(R2.id.tv_hospital)
        TextView mTvHospital;
        @BindView(R2.id.tv_site)
        TextView mTvSite;
        @BindView(R2.id.tv_good_at)
        TextView mTvGoodAt;
        @BindView(R2.id.tv_grade)
        TextView mTvGrade;
        @BindView(R2.id.tv_subscribe_num)
        TextView mTvSubscribeNum;
        @BindView(R2.id.tv_consult_num)
        TextView mTvConsultNum;
        @BindView(R2.id.tv_reply_num)
        TextView mTvReplyNum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void bindView(DoctorListBean.DatasBean datasBean) {

            String member_avatar = datasBean.getMember_avatar();
            Glide.with(mContext).load(member_avatar)
                    .placeholder(R.drawable.head)//图片加载出来前，显示的图片
                    .error(R.drawable.head)//图片加载失败后，显示的图片
                    .into(mIvIcon);

            mTvName.setText(datasBean.getMember_truename());

            // professional_types
            mTvDoctorType.setText(datasBean.getProfessional_types().equals("1") ?
                    mContext.getResources().getString(R.string.TCM) : mContext.getResources().getString(R.string.western_medicine));

            //地址
            if (datasBean.getWork_address().size() > 0) {
                mTvHospital.setText(datasBean.getWork_address().get(0));
            } else {
                mTvHospital.setVisibility(View.GONE);
            }
            //坐诊医院 todo
            mTvSite.setText("");

            //擅长
            if (datasBean.getAdept().size() > 0) {
                mTvGoodAt.setText("擅长：" + datasBean.getAdept().get(0) + "(" + datasBean.getIntroduce() + ")");
            } else {
                mTvGoodAt.setText(datasBean.getIntroduce());
            }

            //评价  evaluate_score
            mTvGrade.setText(TextUtil.highlight(mContext, "评分" + datasBean.getEvaluate_score(), datasBean.getEvaluate_score(), "#FFB400", 0, 0));
            //咨询 consult_count
            mTvConsultNum.setText(TextUtil.highlight(mContext, "咨询量" + datasBean.getConsult_count(), datasBean.getConsult_count(), "#FFB400", 0, 0));

            //咨询量
            mTvSubscribeNum.setText(TextUtil.highlight(mContext, "预约量 99+", " 99+", "#FFB400", 0, 0));

            //回复
            mTvReplyNum.setText(TextUtil.highlight(mContext, "回复速度 9.9", " 9.9", "#FFB400", 0, 0));
        }

    }
}
