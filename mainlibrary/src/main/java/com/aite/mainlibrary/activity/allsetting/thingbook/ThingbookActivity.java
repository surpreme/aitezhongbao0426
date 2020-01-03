package com.aite.mainlibrary.activity.allsetting.thingbook;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Constant.MainUIConstant;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.ChiendChoiceThingsbookActivity;
import com.aite.mainlibrary.activity.allsetting.helpeatorderbook.HelpEatOrderBookActivity;
import com.aite.mainlibrary.activity.allsetting.thingsbook.ChiendThingsbookActivity;
import com.aite.mainlibrary.adapter.IconHormationRecyAdapter;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;

import butterknife.BindView;


/**
 *
 */

public class ThingbookActivity extends BaseActivity<ThingbookContract.View, ThingbookPresenter> implements ThingbookContract.View {

    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    private IconHormationRecyAdapter iconHormationRecyAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.recy_toolbar;
    }

    /**
     * if (postion == 2)
     * startActivity(ChiendThingsbookActivity.class, "type", "noon");
     * if (postion == 3)
     * startActivity(ChiendChoiceThingsbookActivity.class, "type", "choice");
     */
    @Override
    protected void initView() {
        initToolbar("助餐订单");
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(iconHormationRecyAdapter = new IconHormationRecyAdapter(context, MainUIConstant.MirningNoonbookConstant.settingTv, MainUIConstant.MirningNoonbookConstant.settingImg));
        iconHormationRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                if (postion == 0)
                    startActivity(HelpEatOrderBookActivity.class);
                if (postion == 1)
                    startActivity(ChiendChoiceThingsbookActivity.class, "type", "choice");

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