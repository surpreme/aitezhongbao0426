package com.aite.mainlibrary.activity.allsetting.minerural;


import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.adapter.MineRuralPushRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.base.Listener.OnClickRecyclerViewListener;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MineRuralActivity extends BaseActivity<MineRuralContract.View, MineRuralPresenter> implements MineRuralContract.View {
    private MineRuralPushRecyAdapter mineRuralPushRecyAdapter;
    private List<MineRuralPushListBean.ListBean> mMineRuralPushListBean = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.mine_collectage;
    }

    @Override
    protected void initView() {
        initToolbar("我的社区");
        initRecy();
        initSmartLayout(true, false);
        initImgNodata();
        mBaserecyclerView.setAdapter(mineRuralPushRecyAdapter = new MineRuralPushRecyAdapter(context));
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mineRuralPushRecyAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra("title", mMineRuralPushListBean.get(position).getTheme_name());
                intent.putExtra("webViewUrl", String.format("%scomefrom=app", mMineRuralPushListBean.get(position).getH5_url()));
                intent.putExtra("isHideToolBar", "true");
                intent.setAction("com.lzy.base.BaseWebViewActivity");
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }


    @Override
    protected void initDatas() {
        mPresenter.GetMineList(initParams());

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        mineRuralPushRecyAdapter.emptyData();
        mMineRuralPushListBean.clear();
        mCurrentPage = 1;
        initDatas();
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        initDatas();
    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("curpage", mCurrentPage);
        return httpParams;
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    /***
     *
     *          * 返回字段	类型	说明
     *          * datas->list_total	整型	总页数
     *          * datas->is_nextpage	整型	是否有下一页
     *          * datas->list[]	数组	服务列表
     *          * datas->list[]->theme_id	整型	话题ID
     *          * datas->list[]->theme_name	字符串	话题名称
     *          * datas->list[]->theme_content	字符串	话题内容
     *          * datas->list[]->member_avatar	字符串	头像
     *          * datas->list[]->member_name	字符串	发布人姓名
     *          * datas->list[]->theme_browsecount	字符串	浏览数量
     *          * datas->list[]->theme_addtime	字符串	发布时间
     *          * datas->list[]->h5_url	字符串	跳转到h5的链接
     *          * datas->list[]->if_like	字符串	是否可以点赞 1是
     *          * error	字符串	错误信息 code=200 正确 其他编码错误
     *          *
     */
    @Override
    public void onGetMineListSuccess(MineRuralPushListBean mineRuralPushListBean) {
        hasMore = mineRuralPushListBean.getList().isEmpty();
        if (mCurrentPage == 1 && mineRuralPushListBean.getList().isEmpty()) {
            showImgNoData();
        } else {
            stopImgNodata();
        }
        mMineRuralPushListBean.addAll(mineRuralPushListBean.getList());
        mineRuralPushRecyAdapter.appendData(mineRuralPushListBean.getList());

    }
}
