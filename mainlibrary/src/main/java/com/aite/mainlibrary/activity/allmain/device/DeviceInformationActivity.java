package com.aite.mainlibrary.activity.allmain.device;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.lzy.basemodule.base.BaseGaoDeLocationViewActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.map.BaseGaodeAmap;
import com.lzy.basemodule.util.map.BaseGaodeAmapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceInformationActivity extends BaseActivity {
    @BindView(R2.id.love_ll)
    LinearLayout love_ll;
    @BindView(R2.id.foot_ll)
    LinearLayout footLl;
    @BindView(R2.id.sleep_ll)
    LinearLayout sleepLl;
    @BindView(R2.id.gaode_mapview)
    MapView gaodeMapview;
    @BindView(R2.id.look_amap_information)
    ImageView lookAmapInformation;
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
        BaseGaodeAmapView.setupLocationStyle(aMap,false);

    }

    @OnClick({R2.id.look_amap_information})
    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.iv_back) onBackPressed();
        if (v.getId() == R.id.love_ll) startActivity(LoveProgressBaractivity.class);
        if (v.getId() == R.id.foot_ll) startActivity(ProgressBaractivity.class);
        if (v.getId() == R.id.sleep_ll) startActivity(SleepCircleBarStepViewActivity.class);
        if (v.getId() == R.id.look_amap_information) {
            startActivityWithCls(
                    BaseGaoDeLocationViewActivity.class,
                    0,
                    new ContentValue("title", "设备定位"),
                    new ContentValue("latitude", 22.1467077800),
                    new ContentValue("longitude", 113.4887695300)
            );
        }
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
