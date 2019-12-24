package com.aite.mainlibrary.adapter.fragmentAdpter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aite.mainlibrary.fragment.daybookchridren.daybooklist.DaybooklistFragment;
import com.aite.mainlibrary.fragment.daybookchridren.overedbooklist.OveredbooklistFragment;
import com.aite.mainlibrary.fragment.daybookchridren.unpaybooklist.UnPaybooklistFragment;
import com.aite.mainlibrary.fragment.lovefamilychridren.ChridrenFragmentFour;

import java.util.ArrayList;

/**
 * @Auther: liziyang
 * @datetime: 2019-11-26
 * @desc:
 */
public class ThingBookPagerApdapter extends FragmentPagerAdapter {
    private String buytype = "";
    private ArrayList<Fragment> fragments;

    public ThingBookPagerApdapter(FragmentManager fm, ArrayList<Fragment> fragments, String buytype) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
        this.buytype = buytype;
    }

    /**
     * * <!--    状态 0全部 1待付款 2待核销 3已完成 4评价 5已取消-->
     *
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("state", String.valueOf(position));
        bundle.putString("type",buytype);
        assert fragment != null;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
