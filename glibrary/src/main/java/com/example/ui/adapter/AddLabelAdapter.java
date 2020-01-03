package com.example.ui.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.aite.mainlibrary.R;
//import com.aite.mainlibrary.R2;
//import com.aite.mainlibrary.base.BaseRecyclerViewAdapter;
import com.example.base.BaseRecyclerViewAdapter;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.google.android.material.internal.FlowLayout;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/24 10:49
 * 描述:
 */
public class AddLabelAdapter extends BaseRecyclerViewAdapter<String> {


    private Context mContext;

    public AddLabelAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_add_label, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.flow_Layout)
        FlowLayout mFlowLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void bindView(String s) {
            TextView textView = new TextView(mContext);
            textView.setText(s);
            textView.setBackgroundResource(R.drawable.search_bg_item);
            textView.setGravity(Gravity.CENTER);
            int padding2 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_2);
            int padding8 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_8);
            textView.setPadding(padding8, padding2, padding8, padding2);
            textView.setTextColor(mContext.getResources().getColor(R.color.text1));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            mFlowLayout.addView(textView);
        }
    }
}
