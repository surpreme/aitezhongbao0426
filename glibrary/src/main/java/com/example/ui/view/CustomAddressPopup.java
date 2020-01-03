package com.example.ui.view;

import android.content.Context;

import com.example.bean.ListBean;
import com.example.base.OnClickRecyclerViewListener;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.example.ui.adapter.LeftDataAdater;
import com.example.ui.adapter.RightDataAdapter;
import com.lxj.xpopup.impl.PartShadowPopupView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/30 15:37
 * 描述: 地址
 */
public class CustomAddressPopup extends PartShadowPopupView {
    @BindView(R2.id.recy_view_left)
    RecyclerView mRecyViewLeft;
    @BindView(R2.id.recy_view_right)
    RecyclerView mRecyViewRight;
    private Context mContext;

    private LeftDataAdater mLeftDataAdater;

    private RightDataAdapter mRightAdapter;
    private List<ListBean> mData;

    private int leftIndex = 0;

    private int rightIndex = 0;

    private OnCompleteListener mOnCompleteListener;

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        mOnCompleteListener = onCompleteListener;
    }

    public CustomAddressPopup(@NonNull Context context, List<ListBean> data) {
        super(context);
        mContext = context;
        mData = data;
    }

//    public void setData() {
//        mData = data;
//    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_address;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    //todo  数据设置问题
    private void initView() {
        mLeftDataAdater = new LeftDataAdater(mContext);
        mRecyViewLeft.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mLeftDataAdater.updateData(mData, leftIndex);
        mRecyViewLeft.setAdapter(mLeftDataAdater);

        mRightAdapter = new RightDataAdapter(mContext);
        mRecyViewRight.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRightAdapter.updateData(mData.get(leftIndex).getCitylist());
        mRecyViewRight.setAdapter(mRightAdapter);
    }

    private void initListener() {
        mLeftDataAdater.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                leftIndex = position;
                for (int i = 0; i < mData.size(); i++) {
                    mData.get(i).setChecked(position == i);
                }
                mLeftDataAdater.updateData(mData, leftIndex);
                mRightAdapter.updateData(mData.get(position).getCitylist());
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        mRightAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                rightIndex = position;

                for (int i = 0; i < mData.get(leftIndex).getCitylist().size(); i++) {
                    mData.get(leftIndex).getCitylist().get(i).setChecked(position == i);
                }


                mRightAdapter.updateData(mData.get(leftIndex).getCitylist());
                if (mOnCompleteListener != null) {
                    mOnCompleteListener.getData(mData, leftIndex, rightIndex);
                }

            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

    }


    public interface OnCompleteListener {
        void getData(List<ListBean> mData, int leftIndex, int rightIndex);
    }


    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }

}
