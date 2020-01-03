package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.MoneyBookBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-04
 * @desc:
 */
public class SoundAdiveInformationRecyAdapter extends RecyclerView.Adapter<SoundAdiveInformationRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<MoneyBookBean.ListLogBean> datasBeans;

    public SoundAdiveInformationRecyAdapter(Context context, List<MoneyBookBean.ListLogBean> datasBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.datasBeans = datasBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_sound_advice_book_recy, parent, false);
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


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTv.setText(datasBeans.get(position).getLg_desc());
        try {
            holder.timeTv.setText(TimeUtils.stampToDatemm2(Long.parseLong(datasBeans.get(position).getLg_add_time())));
        } catch (Exception e) {
            LogUtils.d(e);
        }
        holder.informationTv.setText(datasBeans.get(position).getLg_av_amount());
        holder.itemView.setOnClickListener(v -> clickInterface.getPosition(position));

    }

    @Override
    public int getItemCount() {
        return datasBeans == null ? 0 : datasBeans.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.title_tv)
        TextView titleTv;
        @BindView(R2.id.time_tv)
        TextView timeTv;
        @BindView(R2.id.information_tv)
        TextView informationTv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
