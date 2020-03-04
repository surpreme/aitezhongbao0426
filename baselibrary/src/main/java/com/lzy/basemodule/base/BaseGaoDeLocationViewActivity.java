package com.lzy.basemodule.base;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.RotateAnimation;
import com.lzy.basemodule.R;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.util.toast.ToastUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-09
 * @desc: 高德地图根据经纬度定位到指定位置
 */

public class BaseGaoDeLocationViewActivity extends BaseActivity {
    protected MapView base_gaode_mapview;
    protected ImageView cut_screen_iv;
    protected AMap aMap;
    protected LatLng latLng;

    @Override
    protected int getLayoutResId() {
        return R.layout.base_location_gaode_view_activity;
    }

    @Override
    protected void initView() {
        if (getIntent().getStringExtra("title") != null) {
            initToolbar(getIntent().getStringExtra("title"));
        } else {
            initToolbar("定位");
        }
        applyLocationpermission();
        base_gaode_mapview = findViewById(R.id.base_gaode_mapview);
        cut_screen_iv = findViewById(R.id.cut_screen_iv);
        initGaoDeAmapView();
        cut_screen_iv.setOnClickListener(v -> {
            cutAmapScreen();
        });

    }

    @Override
    protected void applyperssionbody() {
        super.applyperssionbody();

    }


    private void initGaoDeAmapView() {
        base_gaode_mapview.onCreate(getSavedInstanceState());// 此方法必须重写  new ContentValue("latitude",location[0]),
//        new ContentValue("longitude", location[1])
        aMap = base_gaode_mapview.getMap();
        if (getIntent().getStringExtra("latitude") != null &&
                getIntent().getStringExtra("longitude") != null) {
            latLng = new LatLng(Double.valueOf(getIntent().getStringExtra("latitude")),
                    Double.valueOf(getIntent().getStringExtra("longitude")));

        } else {
            latLng = new LatLng(0, 0);

        }
        initAMap(latLng, aMap);

    }

    /**
     * 移动到指定经纬度
     */
    protected void initAMap(LatLng latLng, AMap aMap) {
        CameraPosition cameraPosition = new CameraPosition(latLng, 15, 0, 30);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        aMap.moveCamera(cameraUpdate);
        showCircleLocation(aMap);
//        List<LatLng> latLngs = new ArrayList<LatLng>();
//        latLngs.add(new LatLng(22.1467077800, 113.4887695300));
//        latLngs.add(new LatLng(22.1387577300, 113.4794998200));
//        latLngs.add(new LatLng(22.1015455400, 113.4757232700));
//        latLngs.add(new LatLng(22.1149048900, 113.5011291500));
//        latLngs.add(new LatLng(22.0767319600, 113.5553741500));
//        showLineLocation(aMap, latLngs);
        drawMarkers(aMap, latLng);
    }

    /**
     * 绘制面
     * 地图上的面分为圆形和多边形两种。 如下：官网地址：
     * https://lbs.amap.com/api/android-sdk/guide/draw-on-map/draw-plane
     *
     * @param aMap
     */
    protected void showCircleLocation(AMap aMap) {
        aMap.addCircle(new CircleOptions().
                center(latLng).
                radius(400).
                fillColor(Color.parseColor("#663A7CFF")).
                strokeColor(Color.parseColor("#663A7CFF")).
                strokeWidth(2));
    }

    /**
     * 绘制轨迹线
     * 官网
     * https://lbs.amap.com/api/android-sdk/guide/draw-on-map/draw-polyline
     * 测试url
     * http://www.gpsspg.com/maps.htm
     * List<LatLng> latLngs = new ArrayList<LatLng>();
     * latLngs.add(new LatLng(39.999391,116.135972));
     *
     * @param aMap
     */

    protected void showLineLocation(AMap aMap, List<LatLng> latLngs) {
        aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(2).color(Color.argb(255, 1, 1, 1)));
    }

    //画定位标记图
    protected void drawMarkers(AMap aMap, LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location))
                .draggable(true);
        Marker marker = aMap.addMarker(markerOptions);
        marker.showInfoWindow();
        showAnimation(marker, false);

    }

    /**
     * 定位标记图动画
     */
    protected void showAnimation(Marker marker, Boolean isShowAnimation) {
        if (isShowAnimation) {
            Animation animation = new RotateAnimation(marker.getRotateAngle(), marker.getRotateAngle() + 180, 0, 0, 0);
            long duration = 1000L;
            animation.setDuration(duration);
            animation.setInterpolator(new LinearInterpolator());

            marker.setAnimation(animation);
            marker.startAnimation();
        }

    }

    /**
     * 对地图进行截屏
     */
    protected void cutAmapScreen() {
        aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {
                PopwindowUtils.getmInstance().showLookImagePopupWindow(context, bitmap);
            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int status) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                if (null == bitmap) {
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/zhongBaoYi"
                                    + sdf.format(new Date()) + ".png");
                    boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    try {
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    StringBuffer buffer = new StringBuffer();
                    if (b)
                        buffer.append("截屏成功 ");
                    else {
                        buffer.append("截屏失败 ");
                    }
                    if (status != 0)
                        buffer.append("地图渲染完成，截屏无网格");
                    else {
                        buffer.append("地图未渲染完成，截屏有网格");
                    }
                    ToastUtils.showToast(getApplicationContext(), buffer.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 其他坐标系转到高德坐标系
     * 支持GPS/Mapbar/Baidu等多种类型坐标在高德地图上使用。参见类CoordinateConverter。
     * BAIDU,
     * MAPBAR,
     * GPS,
     * MAPABC,
     * SOSOMAP,
     * ALIYUN,
     * GOOGLE;
     * CoordType.GPS 待转换坐标类型
     * sourceLatLng待转换坐标点 LatLng类型
     * https://lbs.amap.com/api/android-sdk/guide/computing-equipment/coordinate-transformation
     *
     * @param sourceLatLng
     * @return
     */
    protected LatLng ConvertLatitudeAndLongitude(LatLng sourceLatLng) {
        CoordinateConverter converter = new CoordinateConverter(context);
        converter.from(CoordinateConverter.CoordType.GPS);
        converter.coord(sourceLatLng);
        return converter.convert();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        base_gaode_mapview.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        base_gaode_mapview.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        base_gaode_mapview.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        base_gaode_mapview.onSaveInstanceState(outState);
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
    protected boolean isUseMvp() {
        return false;
    }
}
