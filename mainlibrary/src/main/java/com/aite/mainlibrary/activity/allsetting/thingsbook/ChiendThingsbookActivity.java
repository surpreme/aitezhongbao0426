package com.aite.mainlibrary.activity.allsetting.thingsbook;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.aite.mainlibrary.Mainbean.ChoiceOrderBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allqr.qrcode.QrCodeActivity;
import com.aite.mainlibrary.adapter.RadioGroupRecyAdapter;
import com.aite.mainlibrary.adapter.fragmentAdpter.ThingBookPagerApdapter;
import com.aite.mainlibrary.fragment.daybookchridren.daybooklist.DaybooklistFragment;
import com.aite.mainlibrary.fragment.daybookchridren.overedbooklist.OveredbooklistFragment;
import com.aite.mainlibrary.fragment.daybookchridren.ungeteatbooklist.UnGetEatbooklistFragment;
import com.aite.mainlibrary.fragment.daybookchridren.unpaybooklist.UnPaybooklistFragment;
import com.blankj.rxbus.RxBus;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.util.FileUtils;
import com.lzy.basemodule.view.StatusBarUtils;

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
        initToolbar("助餐订单", "核销", v -> {
            startActivity(QrCodeActivity.class, "type", "unfactbook");

        });
        initFragment();

    }

    /**
     * <!--    状态 0全部 1待付款 2待核销 3已完成 4评价 5已取消-->
     */

    private void initFragment() {
        views = new View[6];
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        views[0] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[1] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[2] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[3] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[4] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
        views[5] = layoutInflater.inflate(R.layout.smartlayout_recy_layout, null);
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
                if (tab.getPosition() == 0) {
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
/**
 *  String eat_choice_json = FileUtils.getFromAssets(context, "choiceeatpop.json");
 *                     Gson gson = new Gson();
 *                     choiceOrderBeans = gson.fromJson(eat_choice_json, new TypeToken<List<ChoiceOrderBean>>() {
 *                     }.getType());
 *                     choiceOrderBeans.get(0).setChecked(true);
 *                     radioGroupRecyAdapter = new RadioGroupRecyAdapter(context, choiceOrderBeans);
 *                     radioGroupRecyAdapter.setClickInterface(position -> {
 *                         RxBus.getDefault().post(position, "CHOICEEAT");
 *                     });
 *                     LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
 *                     PopwindowUtils.getmInstance().showRecyPopupWindow(
 *                             context,
 *                             getScreenHeight() - tabLayout.getBottom() - StatusBarUtils.getHeight(context),
 *                             radioGroupRecyAdapter,
 *                             manager,
 *                             tabLayout,
 *                             new PopupWindow.OnDismissListener() {
 *                                 @Override
 *                                 public void onDismiss() {
 *
 *                                 }
 *                             });
 *
 * //                    PopwindowUtils.getmInstance().showRecyPopupWindow(context, radioGroupRecyAdapter = new RadioGroupRecyAdapter(context, choiceOrderBeans), manager);
 *
 */

}
