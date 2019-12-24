package com.aite.mainlibrary.activity.allsetting;


import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.fragmentAdpter.ThingBookPagerApdapter;
import com.aite.mainlibrary.fragment.choiceEatbook.chirendchoiceeat.ChirendChoiceEatFragment;
import com.google.android.material.tabs.TabLayout;
import com.lzy.basemodule.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChiendChoiceThingsbookActivity extends BaseActivity {
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
        initToolbar("菜品订单");
        initFragment();
    }

    /**
     * <!--    状态 0全部 1待付款 2待核销 3已完成 4评价 5已取消-->
     */

    private void initFragment() {
        views = new View[5];
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        views[0] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[1] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[2] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[3] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[4] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ChirendChoiceEatFragment());
        fragments.add(new ChirendChoiceEatFragment());
        fragments.add(new ChirendChoiceEatFragment());
        fragments.add(new ChirendChoiceEatFragment());
        fragments.add(new ChirendChoiceEatFragment());

        thingBookPagerApdapter = new ThingBookPagerApdapter(this.getSupportFragmentManager(), fragments, "sdsd");
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
