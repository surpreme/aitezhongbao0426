package com.aite.aitezhongbao;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aite.a.HomeTabActivity;
import com.aite.mainlibrary.fragment.activityfragment.AroundBackgroundFragment;
import com.aite.mainlibrary.fragment.activityfragment.LoveFamilyFragment;
import com.aite.mainlibrary.fragment.activityfragment.ShopFragment;
import com.aite.mainlibrary.fragment.activityfragment.main.MainFragment;
import com.aite.mainlibrary.fragment.activityfragment.minefragment.MineFragment;
import com.blankj.rxbus.RxBus;
import com.example.event.ChangeRoleEvent;
import com.example.event.PayMessageEvent;
import com.example.ui.fagment.DoctorInfoFragment;
import com.example.ui.fagment.VolunteerFagment;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.view.StatusBarUtils;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.main_img)
    ImageView mainImg;
    @BindView(R.id.main_tv)
    TextView mainTv;
    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;
    @BindView(R.id.shop_img)
    ImageView shopImg;
    @BindView(R.id.shop_tv)
    TextView shopTv;
    @BindView(R.id.shop_layout)
    RelativeLayout shopLayout;
    @BindView(R.id.aroundbackground_img)
    ImageView aroundbackgroundImg;
    @BindView(R.id.aroundbackground_tv)
    TextView aroundbackgroundTv;
    @BindView(R.id.aroundbackground_layout)
    RelativeLayout aroundbackgroundLayout;
    @BindView(R.id.news_img)
    ImageView newsImg;
    @BindView(R.id.news_tv)
    TextView newsTv;
    @BindView(R.id.news_layout)
    RelativeLayout newsLayout;
    @BindView(R.id.my_img)
    ImageView myImg;
    @BindView(R.id.my_tv)
    TextView myTv;
    @BindView(R.id.my_layout)
    RelativeLayout myLayout;
    @BindView(R.id.tabAllLl)
    LinearLayout mTabAll;


    private static final String[] FRAGMENT_TAG = {"MainFragment", "ShopFragment", "AroundBackgroundFragment", "LoveFamilyFragment", "MineFragment"};
    protected String CODE_FRAGMENT_KEY = "fragment_tag";//key

    private static final String[] HOME_PAGE = {"DoctorInfoFragment", "mVolunteerFagment"};


    //会员首页
    private MainFragment mainFragment;

    //医生首页
    private DoctorInfoFragment mDoctorInfoFragment;

    //义工首页
    private VolunteerFagment mVolunteerFagment;

    private MineFragment mineFragment;
    private LoveFamilyFragment loveFamilyFragment;
    private ShopFragment shopFragment;
    private AroundBackgroundFragment aroundBackgroundFragment;
    private int mFragmentTag_index = 0;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        EventBus.getDefault().post(new ChangeRoleEvent());

        mainFragment = null;
        mDoctorInfoFragment = null;

        setTabSelection(0);


    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        switch (AppConstant.CURRENT_IDENTITY) {
            case 1:
                if (fragment instanceof MainFragment)
                    mainFragment = (MainFragment) fragment;
                break;
            case 2:
                if (fragment instanceof DoctorInfoFragment) {
                    mDoctorInfoFragment = (DoctorInfoFragment) fragment;
                }
                break;
            case 4:
                if (fragment instanceof VolunteerFagment) {
                    mVolunteerFagment = (VolunteerFagment) fragment;
                }
                break;
        }

        if (fragment instanceof MineFragment)
            mineFragment = (MineFragment) fragment;
        else if (fragment instanceof LoveFamilyFragment)
            loveFamilyFragment = (LoveFamilyFragment) fragment;
        else if (fragment instanceof ShopFragment)
            shopFragment = (ShopFragment) fragment;
        else if (fragment instanceof AroundBackgroundFragment)
            aroundBackgroundFragment = (AroundBackgroundFragment) fragment;

        super.onAttachFragment(fragment);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        if (getSavedInstanceState() != null) {
            switch (AppConstant.CURRENT_IDENTITY) {
                case 1:
                    if (getSavedInstanceState().getInt(CODE_FRAGMENT_KEY) == 0 && mainFragment == null)
                        mainFragment = (MainFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
                    break;
                case 2:
                    if (getSavedInstanceState().getInt(CODE_FRAGMENT_KEY) == 0 && mDoctorInfoFragment == null)
                        mDoctorInfoFragment = (DoctorInfoFragment) fragmentManager.findFragmentByTag(HOME_PAGE[0]);
                    break;
                case 4:
                    if (getSavedInstanceState().getInt(CODE_FRAGMENT_KEY) == 0 && mVolunteerFagment == null)
                        mVolunteerFagment = (VolunteerFagment) fragmentManager.findFragmentByTag(HOME_PAGE[1]);
                    break;
            }

//            if (getSavedInstanceState().getInt(CODE_FRAGMENT_KEY) == 1 && shopFragment == null)
//                shopFragment = (ShopFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);\


            if (getSavedInstanceState().getInt(CODE_FRAGMENT_KEY) == 1 && aroundBackgroundFragment == null)
                aroundBackgroundFragment = (AroundBackgroundFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
            if (getSavedInstanceState().getInt(CODE_FRAGMENT_KEY) == 2 && loveFamilyFragment == null)
                loveFamilyFragment = (LoveFamilyFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
            if (getSavedInstanceState().getInt(CODE_FRAGMENT_KEY) == 3 && mineFragment == null)
                mineFragment = (MineFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[3]);
            setTabSelection(getSavedInstanceState().getInt(CODE_FRAGMENT_KEY));
        } else {
            setTabSelection(0);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CODE_FRAGMENT_KEY, mFragmentTag_index);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected boolean isUseMvp() {
        return false;
    }


    @Override
    protected void initResume() {
        RxBus.getDefault().subscribe(MainActivity.this, "ISSHOWHIDETABLL", new RxBus.Callback<String>() {
            @Override
            public void onEvent(String o) {
                if (o.equals("hide")) {
                    mTabAll.setVisibility(View.GONE);
                } else {
                    mTabAll.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @Override
    protected void initReStart() {

    }

    private FragmentManager fragmentManager;


    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        mFragmentTag_index = index;
        switch (index) {
            case 0:
                mainImg.setImageResource(R.mipmap.mainimg);
                mainTv.setTextColor(getResources().getColor(R.color.blue));

                initHomePage(transaction);

                break;
//            case 1:
//                shopImg.setImageResource(R.drawable.shop);
//                shopTv.setTextColor(getResources().getColor(R.color.blue));
//                if (shopFragment == null) {
//                    shopFragment = new ShopFragment();
//                    transaction.add(R.id.content, shopFragment, FRAGMENT_TAG[index]);
//                } else {
//                    transaction.show(shopFragment);
//                }
//                break;
            case 1:
                aroundbackgroundImg.setImageResource(R.drawable.around);
                aroundbackgroundTv.setTextColor(getResources().getColor(R.color.blue));
                if (aroundBackgroundFragment == null) {
                    aroundBackgroundFragment = new AroundBackgroundFragment();
                    transaction.add(R.id.content, aroundBackgroundFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(aroundBackgroundFragment);
                }
                break;
            case 2:
                newsImg.setImageResource(R.drawable.app_news);
                newsTv.setTextColor(getResources().getColor(R.color.blue));
                if (loveFamilyFragment == null) {
                    loveFamilyFragment = new LoveFamilyFragment();
                    transaction.add(R.id.content, loveFamilyFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(loveFamilyFragment);
                }
                break;
            case 3:
                myImg.setImageResource(R.drawable.mine);
                myTv.setTextColor(getResources().getColor(R.color.blue));
                StatusBarUtils.setColor(context, getResources().getColor(R.color.blue));
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.content, mineFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(mineFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();

    }

    /***
     * 切换角色处理多首页
     */
    private void initHomePage(FragmentTransaction transaction) {
        switch (AppConstant.CURRENT_IDENTITY) {
            case 1: //会员首页
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    transaction.add(R.id.content, mainFragment, FRAGMENT_TAG[0]);
                } else {
                    transaction.show(mainFragment);
                }
                break;
            case 2:
                if (mDoctorInfoFragment == null) {
                    mDoctorInfoFragment = new DoctorInfoFragment();
                    transaction.add(R.id.content, mDoctorInfoFragment, HOME_PAGE[0]);
                } else {
                    transaction.show(mDoctorInfoFragment);
                }
                break;
            case 4:
                if (mVolunteerFagment == null) {
                    mVolunteerFagment = new VolunteerFagment();
                    transaction.add(R.id.content, mVolunteerFagment, HOME_PAGE[1]);
                } else {
                    transaction.show(mVolunteerFagment);
                }
                break;

        }

    }

    private void clearSelection() {
        StatusBarUtils.setColor(context, getResources().getColor(R.color.white));
        shopImg.setImageResource(R.mipmap.unshop);
        shopTv.setTextColor(Color.parseColor("#82858b"));
        mainImg.setImageResource(R.drawable.unmain);
        mainTv.setTextColor(Color.parseColor("#82858b"));
        aroundbackgroundImg.setImageResource(R.mipmap.unaroundbackground);
        aroundbackgroundTv.setTextColor(Color.parseColor("#82858b"));
        newsImg.setImageResource(R.mipmap.unnews);
        newsTv.setTextColor(Color.parseColor("#82858b"));
        myImg.setImageResource(R.mipmap.unmy);
        myTv.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 隐藏碎片 避免重叠
     * 爱家 unnews
     * 我的 unmy /// mine
     */
    private void hideFragment(FragmentTransaction transaction) {

        hideHomeFragment(transaction);


//        if (shopFragment != null) {
//            transaction.hide(shopFragment);
//        }
        if (aroundBackgroundFragment != null) {
            transaction.hide(aroundBackgroundFragment);
        }
        if (loveFamilyFragment != null) {
            transaction.hide(loveFamilyFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }

    }

    /**
     * 处理多首页 切换
     *
     * @param transaction
     */
    private void hideHomeFragment(FragmentTransaction transaction) {
        switch (AppConstant.CURRENT_IDENTITY) {
            case 1:
                if (mainFragment != null) {
                    transaction.hide(mainFragment);
                }
                break;
            case 2:
                if (mDoctorInfoFragment != null) {
                    transaction.hide(mDoctorInfoFragment);
                }
                break;
            case 4:
                if (mVolunteerFagment != null) {
                    transaction.hide(mVolunteerFagment);
                }
                break;
        }
    }


    @OnClick({R.id.main_layout, R.id.shop_layout, R.id.aroundbackground_layout, R.id.news_layout, R.id.my_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_layout:
                setTabSelection(0);
                break;
            case R.id.shop_layout:
                //       setTabSelection(1);
                Intent intent = new Intent(/*getContext(), HomeTabActivity.class*/);
                intent.setClass(getContext(), HomeTabActivity.class);
                intent.putExtra("userKey", AppConstant.KEY);
//                //getContext(),com.aite.a.activity.MainActivity.class
//                intent.setAction("com.aite.zhongbao.shop.MainActivity");
                startActivity(intent);

                break;
            case R.id.aroundbackground_layout:
                setTabSelection(1);
//                Intent information = new Intent(/*getContext(), InformationActivity.class*/);
//                information.setClass(getContext(), InformationActivity.class);
//                startActivity(information);
                break;
            case R.id.news_layout:
//                Intent intent1 = new Intent(context,
//                        InformationActivity.class);
//                startActivity(intent1);
//                Intent person_in = new Intent(/*getContext(), InformationActivity.class*/);
//                person_in.setClassName(getContext(), "com.aite.a.activity.InformationActivity");
//                person_in.putExtra("person_in", "2");
//                startActivity(person_in);
                setTabSelection(2);
                break;
            case R.id.my_layout:
                setTabSelection(3);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mFragmentTag_index == 1) {
            if (aroundBackgroundFragment.WebonBackPressed()) {
                super.onBackPressed();
            } else {
                return;
            }
        } else if (mFragmentTag_index == 2) {
            if (loveFamilyFragment.WebonBackPressed()) {
                super.onBackPressed();
            } else {
                return;
            }
        } else {
            super.onBackPressed();
        }
    }
}