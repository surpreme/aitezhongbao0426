package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.BookLessBodyFamilyBean;
import com.aite.mainlibrary.Mainbean.DisputeBookLIstBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import chat.utils.TimeUtil;


public class DisputebookListRecyAdapter extends RecyclerView.Adapter<DisputebookListRecyAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<DisputeBookLIstBean.DatasBean.ListBean> disputeBookLIstBean;

    public DisputebookListRecyAdapter(Context context, List<DisputeBookLIstBean.DatasBean.ListBean> disputeBookLIstBean) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.disputeBookLIstBean = disputeBookLIstBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_dispute_thingbook_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private OnClickLstenerInterface.OnRecyClickInterface clickInterface;

    public OnClickLstenerInterface.OnRecyClickInterface getClickInterface() {
        return clickInterface;
    }

    public interface OnStartEatcodeInterface {
        void getStartqrPosition(int position);
    }

    private OnStartEatcodeInterface onStartEatcodeInterface;

    public OnStartEatcodeInterface getOnStartEatcodeInterface() {
        return onStartEatcodeInterface;
    }

    public void setOnStartEatcodeInterface(OnStartEatcodeInterface onStartEatcodeInterface) {
        this.onStartEatcodeInterface = onStartEatcodeInterface;
    }

    public void setClickInterface(OnClickLstenerInterface.OnRecyClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    /**
     * * accuser_name : 18614079738
     * * accused_id : 2
     * * accused_name : 艾特技术
     * * complain_subject_content : 态度不好
     * * complain_subject_id : 5
     * * complain_content : uikyukyhujk
     * * complain_pic1 :
     * * complain_pic2 :
     * * complain_pic3 :
     * * complain_datetime : 1576642411
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Glide.with(context).load(disputeBookLIstBean.get(position).getGoods_array().getGoods_image()).into(holder.iconIv);
            holder.titleTv.setText(String.format("投诉: %s", disputeBookLIstBean.get(position).getGoods_array().getGoods_name()));
        } catch (Exception e) {
            LogUtils.d(e);
        }
        holder.timeTv.setText(String.format("投诉时间： %s", TimeUtils.stampToDatemm2(Long.parseLong(disputeBookLIstBean.get(position).getComplain_datetime()))));
        holder.storeNameTv.setText(String.format("店铺名字: %s", disputeBookLIstBean.get(position).getAccused_name()));
        holder.informationTv.setText(String.format("投诉内容: %s%s", disputeBookLIstBean.get(position).getComplain_subject_content(), disputeBookLIstBean.get(position).getComplain_content()));
        holder.itemView.setOnClickListener(v -> {
            if (clickInterface != null)
                clickInterface.getPosition(position);
        });


    }

    @Override
    public int getItemCount() {
        return disputeBookLIstBean == null ? 0 : disputeBookLIstBean.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.icon_iv)
        ImageView iconIv;
        @BindView(R2.id.title_tv)
        TextView titleTv;
        @BindView(R2.id.store_name_tv)
        TextView storeNameTv;
        @BindView(R2.id.information_tv)
        TextView informationTv;
        @BindView(R2.id.time_tv)
        TextView timeTv;
        @BindView(R2.id.state_tv)
        TextView stateTv;
        @BindView(R2.id.look_information_tv)
        TextView lookInformationTv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
