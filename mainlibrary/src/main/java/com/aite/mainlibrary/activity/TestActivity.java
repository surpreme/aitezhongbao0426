package com.aite.mainlibrary.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.adpter.BaseOneRecyAdapter;
import com.lzy.basemodule.adpter.BaseTextViewRecyAdapter;
import com.lzy.basemodule.adpter.BaseTwoRecyAdapter;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.util.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-14
 * @desc: 二级选择
 */
public class TestActivity extends BaseActivity {
    @BindView(R2.id.year_recy)
    RecyclerView yearRecy;
    @BindView(R2.id.monday_recy)
    RecyclerView mondayRecy;
    private BaseOneRecyAdapter baseOneRecyAdapter;
    private BaseTwoRecyAdapter baseTwoRecyAdapter;
    private List<String> yearList = new ArrayList<>();
    private List<String> mondayList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.choice_three_recy2;
    }

    @Override
    protected void initView() {
        Date date = new Date(System.currentTimeMillis());
        int year = TimeUtils.getYearByDate(date);
        for (int i = 2015; i <= year; i++) {
            yearList.add(String.valueOf(i));
            LogUtils.e(i);
        }
        initYearList();
        yearRecy.setAdapter(baseOneRecyAdapter = new BaseOneRecyAdapter(context, yearList));
        yearRecy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        baseOneRecyAdapter.notifyDataSetChanged();
        baseOneRecyAdapter.setClickInterface(position -> {
            baseTwoRecyAdapter.clearDatas();
            baseOneRecyAdapter.refreshSelected(position);
            baseTwoRecyAdapter.refreshSelected(0);
            if (position % 2 != 0) {
                if (clearYearList()) {
                    initYearList2();
                    baseTwoRecyAdapter.notifyDataSetChanged();
                }
            } else {
                if (clearYearList()) {
                    initYearList();
                    baseTwoRecyAdapter.notifyDataSetChanged();
                }
            }

        });
        mondayRecy.setAdapter(baseTwoRecyAdapter = new BaseTwoRecyAdapter(context, mondayList));
        mondayRecy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        baseTwoRecyAdapter.notifyDataSetChanged();
        baseTwoRecyAdapter.setClickInterface(position -> {
            baseTwoRecyAdapter.refreshSelected(position);
        });


    }

    private int initYearList() {
        for (int i = 1; i <= 12; i++) {
            mondayList.add(String.valueOf(i));
        }
        return mondayList.size();

    }

    private int initYearList2() {
        for (int i = 5; i <= 50; i++) {
            mondayList.add(String.valueOf(i));
        }
        return mondayList.size();
    }

    private boolean clearYearList() {
        if (mondayList != null) {
            if (!mondayList.isEmpty()) mondayList.clear();
        } else mondayList = new ArrayList<>();
        return mondayList.isEmpty();
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
