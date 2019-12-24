package com.aite.a.activity.li.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.li.adapter.ZhongBaoCollectServiceAdapter;
import com.aite.a.activity.li.bean.ZhongbaoCollectBean;
import com.aite.a.parse.NetRun;
import com.aiteshangcheng.a.R;
import com.aiteshangcheng.a.R2;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.view.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ZhongbaoServiceFragment extends BaseFragment {
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    private ZhongBaoCollectServiceAdapter zhongBaoCollectServiceAdapter;
    private List<ZhongbaoCollectBean.DatasBean.FavoritesListBean> mDatas = new ArrayList<>();

    /*
    datas.favorites_list[].goods_id	整型	商品id
    datas.favorites_list[].page_type	整型	页面跳转类型 1日托 2培训 3就业 4助残活动 5其他服务 8助餐
     */
    @Override
    protected void initModel() {
        NetRun netRun = new NetRun(context);
        netRun.zhongbaocollect(AppConstant.COLLECTSERVICEBOOKURL, AppConstant.KEY, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtils.d(responseInfo.result);
                Gson gson = new Gson();
                ZhongbaoCollectBean zhongbaoCollectBean = gson.fromJson(responseInfo.result, ZhongbaoCollectBean.class);
                mDatas.addAll(zhongbaoCollectBean.getDatas().getFavorites_list());
                zhongBaoCollectServiceAdapter.notifyDataSetChanged();
                zhongBaoCollectServiceAdapter.setOnclickInterface(new ZhongBaoCollectServiceAdapter.OnclickInterface() {
                    @Override
                    public void getPostion(int postion) {
                        Intent intent = new Intent();
                        intent.setClassName(getContext(), "com.aite.mainlibrary.activity.allshopcard.dayinformation.DayInformationActivity");
                        intent.putExtra("goods_id", mDatas.get(postion).getGoods_id());
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

    @Override
    protected void initViews() {
        StatusBarUtils.setColor(this.getActivity(), getResources().getColor(com.lzy.basemodule.R.color.white));
        zhongBaoCollectServiceAdapter = new ZhongBaoCollectServiceAdapter(context, mDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(zhongBaoCollectServiceAdapter);
        initAction();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.recy_layout;
    }

    private void initAction() {

    }


    @Override
    public void onClick(View v) {

    }

}

