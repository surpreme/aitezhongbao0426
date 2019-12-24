package com.aite.mainlibrary.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.base.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/23 18:07
 * 描述:
 */
public class AddSiteAdapter extends BaseRecyclerViewAdapter<String> {

    private Context mContext;

    public AddSiteAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_add_site, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mDataList.get(position));
    }

    class ViewHolder extends BaseRvHolder {
        @BindView(R2.id.et_add_site)
        EditText mEtAddSite;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mEtAddSite.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String s1 = s.toString();
                    mDataList.set(getLayoutPosition(),s1);
                }
            });

        }

        @Override
        protected void bindView(String s) {
            mEtAddSite.setText(s);
        }

    }
}
