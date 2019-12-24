package com.aite.mainlibrary.activity.allmain.device;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allanimation.LoveProgressBaractivity;
import com.aite.mainlibrary.activity.allanimation.ProgressBaractivity;
import com.aite.mainlibrary.activity.allanimation.SleepCircleBarStepViewActivity;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.map.BaseGaodeAmap;
import com.lzy.basemodule.util.map.BaseGaodeAmapView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceInformationActivity extends BaseActivity {
    @BindView(R2.id.love_ll)
    LinearLayout love_ll;
    @BindView(R2.id.foot_ll)
    LinearLayout footLl;
    @BindView(R2.id.sleep_ll)
    LinearLayout sleepLl;
    @BindView(R2.id.gaode_mapview)
    MapView gaodeMapview;
    /**
     * 单次定位
     */
    private AMapLocationClient locationClientSingle = null;
    private AMap aMap;


    @Override
    protected int getLayoutResId() {
        return R.layout.device_information;
    }

    @Override
    protected void initView() {
        initToolbar("小米手环");
        love_ll.setOnClickListener(this);
        footLl.setOnClickListener(this);
        sleepLl.setOnClickListener(this);
        applyLocationpermission();
        BaseGaodeAmap.startSingleLocation(locationClientSingle, context);
        initGaoDeAmapView();


    }

    @Override
    protected void applyperssionbody() {

    }

    private void initGaoDeAmapView() {
        gaodeMapview.onCreate(getSavedInstanceState());// 此方法必须重写
        aMap = gaodeMapview.getMap();
        BaseGaodeAmapView.setupMapView(aMap, context);
        BaseGaodeAmapView.setupLocationStyle(aMap);

    }


    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.iv_back) onBackPressed();
        if (v.getId() == R.id.love_ll) startActivity(LoveProgressBaractivity.class);
        if (v.getId() == R.id.foot_ll) startActivity(ProgressBaractivity.class);
        if (v.getId() == R.id.sleep_ll) startActivity(SleepCircleBarStepViewActivity.class);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseGaodeAmap.destoryOnce(locationClientSingle);

    }
}
