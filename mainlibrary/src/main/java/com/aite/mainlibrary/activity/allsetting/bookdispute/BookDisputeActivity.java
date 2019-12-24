package com.aite.mainlibrary.activity.allsetting.bookdispute;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.fragmentAdpter.LessBodyViewPagerApdapter;
import com.aite.mainlibrary.fragment.disputeChridren.disputebooklist.DisputeBookLIstFragment;
import com.aite.mainlibrary.fragment.lessBodyfragment.lessbodybookfragment.LessBodyBookFragment;
import com.aite.mainlibrary.fragment.lessBodyfragment.lessbodyoveredbooklist.LessbodyoveredbooklistFragment;
import com.aite.mainlibrary.fragment.lessBodyfragment.lessbodyunpaybooklist.LessbodyunpaybooklistFragment;
import com.google.android.material.tabs.TabLayout;
import com.lzy.basemodule.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BookDisputeActivity extends BaseActivity<BookDisputeContract.View, BookDisputePresenter> implements BookDisputeContract.View {
    @BindView(R2.id.tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    private View[] views;
    private LessBodyViewPagerApdapter lessBodyViewPagerApdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.book_dispute;
    }

    @Override
    protected void initView() {
        initToolbar("订单投诉");
        initFragment();
    }

    private void initFragment() {
        views = new View[3];
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        views[0] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[1] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[2] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DisputeBookLIstFragment());
        fragments.add(new DisputeBookLIstFragment());
        fragments.add(new DisputeBookLIstFragment());
        lessBodyViewPagerApdapter = new LessBodyViewPagerApdapter(this.getSupportFragmentManager(), fragments, "fsdfsd");
        //一次加载3个 防止销毁（解决懒加载的 只加载一次数据的问题） setOffscreenPageLimit
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPager.setAdapter(lessBodyViewPagerApdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

}