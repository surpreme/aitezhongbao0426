package com.aite.mainlibrary.activity.allsetting.thingsbook;


import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allshopcard.timebank.TimeBankContract;
import com.aite.mainlibrary.activity.allshopcard.timebank.TimeBankPresenter;
import com.aite.mainlibrary.adapter.fragmentAdpter.BackgroundViewPagerApdapter;
import com.aite.mainlibrary.adapter.fragmentAdpter.LessBodyViewPagerApdapter;
import com.aite.mainlibrary.adapter.fragmentAdpter.LoveFamilyViewPagerApdapter;
import com.aite.mainlibrary.adapter.fragmentAdpter.ThingBookPagerApdapter;
import com.aite.mainlibrary.fragment.daybookchridren.daybooklist.DaybooklistFragment;
import com.aite.mainlibrary.fragment.daybookchridren.overedbooklist.OveredbooklistFragment;
import com.aite.mainlibrary.fragment.daybookchridren.ungeteatbooklist.UnGetEatbooklistFragment;
import com.aite.mainlibrary.fragment.daybookchridren.unpaybooklist.UnPaybooklistFragment;
import com.aite.mainlibrary.fragment.lessBodyfragment.lessbodybookfragment.LessBodyBookFragment;
import com.aite.mainlibrary.fragment.lessBodyfragment.lessbodyoveredbooklist.LessbodyoveredbooklistFragment;
import com.aite.mainlibrary.fragment.lessBodyfragment.lessbodyunpaybooklist.LessbodyunpaybooklistFragment;
import com.aite.mainlibrary.fragment.lovefamilychridren.chridrenfirst.ChridrenFirstFragment;
import com.google.android.material.tabs.TabLayout;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.mvp.MVPBaseActivity;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChiendThingsbookActivity extends BaseActivity<ThingsbookContract.View, ThingsbookPresenter> implements ThingsbookContract.View {
    private ThingBookPagerApdapter thingBookPagerApdapter;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    @BindView(R2.id.thingsfix_tabMode)
    TabLayout tabLayout;
    private View[] views;


    @Override
    protected int getLayoutResId() {
        return R.layout.thingbook_layout;
    }

    @Override
    protected void initView() {
        initToolbar("助餐订单");
        initFragment();
    }

    /**
     * <!--    状态 0全部 1待付款 2待核销 3已完成 4评价 5已取消-->
     */

    private void initFragment() {
        views = new View[4];
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        views[0] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[1] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[2] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[3] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DaybooklistFragment());
        fragments.add(new UnPaybooklistFragment());
        fragments.add(new UnGetEatbooklistFragment());
        fragments.add(new OveredbooklistFragment());
        fragments.add(new DaybooklistFragment());
        fragments.add(new DaybooklistFragment());
        thingBookPagerApdapter = new ThingBookPagerApdapter(this.getSupportFragmentManager(), fragments, getIntent().getStringExtra("type"));
        //一次加载3个 防止销毁（解决懒加载的 只加载一次数据的问题） setOffscreenPageLimit
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPager.setAdapter(thingBookPagerApdapter);
        //滑动绑定
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //点击tablayout选中绑定
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
    protected boolean isUseMvp() {
        return false;
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
