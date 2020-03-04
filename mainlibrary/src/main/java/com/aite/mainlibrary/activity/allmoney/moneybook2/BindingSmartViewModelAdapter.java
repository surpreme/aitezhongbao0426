package com.aite.mainlibrary.activity.allmoney.moneybook2;


import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseModel;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-11
 * @desc:
 */
public final class BindingSmartViewModelAdapter extends BaseModel {
    /**
     * 设置中划线
     * textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
     *
     * @param textView
     * @param paintType
     */
    @BindingAdapter(value = {"flags"}, requireAll = false)
    public static void setTextViewFlags(TextView textView, int paintType) {
        if (paintType != 0)
            textView.getPaint().setFlags(paintType);


    }

    /**
     * tablayout添加tab
     * user activity
     * viewModel.tabList.add("宝贝");
     * viewModel.tabList.add("收藏");
     * user vm
     * public ArrayList<String> tabList = new ArrayList<>();
     * user xml
     * <android.support.design.widget.TabLayout
     * android:id="@+id/tab_layout"
     * android:layout_width="match_parent"
     * android:layout_height="wrap_content"
     * binding:tab="@{viewModel.tabList}"/>
     *
     * @param tabLayout
     * @param list
     */
    @BindingAdapter(value = {"tab"}, requireAll = false)
    public static void addTab(TabLayout tabLayout, ArrayList<String> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(list.get(i)));

            }

        }


    }

    /**
     * glide自定义
     *
     * @param imageView
     * @param url            image url
     * @param placeholderRes 正在加载中的res
     * @param isCircle       是否为圆 （你可以自定义传方法进去）
     */
    @BindingAdapter(value = {"imageUrl", "imagePlaceholderRes", "isCircle"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes, boolean isCircle) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            if (isCircle) {
                Glide.with(imageView.getContext())
                        .load(url)
                        .apply(new RequestOptions().placeholder(placeholderRes))
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);
            } else {
                Glide.with(imageView.getContext())
                        .load(url)
                        .apply(new RequestOptions().placeholder(placeholderRes))
                        .into(imageView);
            }

        }
    }

    /**
     * 状态绑定，控制停止刷新
     * 建议不要使用如下 refreshing  moreLoading
     * 会跟下拉刷新的监听冲突 refreshing会一直为一个值 监听不到就不会执行
     * hasMore是判断是否有下一页的 主要是处理动画的
     * ****************xml使用如下
     * * binding:refreshHeader="@{viewModel.waterDropHeader}"
     * * binding:refreshFooter="@{viewModel.classicsFooter}"
     * * binding:hasMore="@{viewModel.isHasMore}"
     * * binding:autoRefresh="@{viewModel.autoRefresh}"
     * * binding:onLoadMoreListener="@{viewModel.onLoadMoreListener}"
     * * binding:onRefreshListener="@{viewModel.onRefreshListener}"
     *
     * @param smartLayout
     * @param refreshing
     * @param moreLoading
     * @param hasMore
     */
    @BindingAdapter(value = {"refreshing", "moreLoading", "hasMore"}, requireAll = false)
    public static void bindSmartRefreshLayout(
            SmartRefreshLayout smartLayout,
            ObservableBoolean refreshing,
            ObservableBoolean moreLoading,
            ObservableBoolean hasMore) {
        if (refreshing != null) if (!refreshing.get()) smartLayout.finishRefresh();
        if (moreLoading != null) if (!moreLoading.get()) smartLayout.finishLoadMore();
        if (hasMore != null) {
            if (!hasMore.get())
                smartLayout.finishLoadMoreWithNoMoreData();
            else if (hasMore.get())
                smartLayout.setNoMoreData(false);
        }


    }

    /**
     * 控制自动刷新
     *
     * @param smartLayout
     * @param autoRefresh
     */
    @BindingAdapter(value = {"autoRefresh"})
    public static void bindSmartRefreshLayout(
            SmartRefreshLayout smartLayout,
            ObservableBoolean autoRefresh) {
        if (autoRefresh != null)
            if (autoRefresh.get()) smartLayout.autoRefresh();


    }

    /**
     * 上下拉刷新的监听
     *
     * @param smartLayout
     * @param onRefreshListener
     */
    @BindingAdapter(value = {"onRefreshListener"}, requireAll = false)
    public static void setRefreshListener(
            SmartRefreshLayout smartLayout,
            OnRefreshListener onRefreshListener) {
        if (onRefreshListener != null)
            smartLayout.setOnRefreshListener(onRefreshListener);

    }

    @BindingAdapter(value = {"onLoadMoreListener"}, requireAll = false)
    public static void setLoadMoreListener(
            SmartRefreshLayout smartLayout,
            OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null)
            smartLayout.setOnLoadMoreListener(loadMoreListener);

    }

    /**
     * 头部和底部样式的自定义
     *
     * @param smartLayout
     * @param refreshHeader
     * @param refreshFooter
     */
    @BindingAdapter(value = {"refreshHeader", "refreshFooter"}, requireAll = false)
    public static void setRefreshHeader(
            SmartRefreshLayout smartLayout,
            RefreshHeader refreshHeader, RefreshFooter refreshFooter) {
        if (refreshHeader != null)
            smartLayout.setRefreshHeader(refreshHeader);
        if (refreshFooter != null)
            smartLayout.setRefreshFooter(refreshFooter);

    }


}
