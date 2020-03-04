package com.aite.amaplibrary.fragment

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.aite.amaplibrary.R
import com.amap.api.location.AMapLocationClient
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.MapView
import com.amap.api.maps.model.animation.RotateAnimation
import com.lzy.basemodule.util.map.BaseGaodeAmap
import com.lzy.basemodule.util.map.BaseGaodeAmapView
import com.amap.api.maps.model.*
import com.amap.api.maps.model.CameraPosition
import java.util.ArrayList


/**
 * @Auther: liziyang
 * @datetime: 2020-02-11 amaplibrary 定位选点
 * @desc:, LocationSource, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener, PoiSearch.OnPoiSearchListener {
 */
open class BaseAmapSelectionFragment : BaseFragment() {


    private var mMapView: MapView? = null
    /**
     * 单次定位
     */
    private val locationClientSingle: AMapLocationClient? = null
    private var aMap: AMap? = null
    private var locationMarker: Marker? = null


    override fun getLayoutResId(): Int {
        return R.layout.selection_map_fragment

    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        mMapView = view.findViewById(R.id.base_gaode_mapview)
        mMapView?.onCreate(savedInstanceState)
    }

    override fun initDatas() {
        BaseGaodeAmap.startSingleLocation(locationClientSingle, mContext)
        aMap = mMapView?.map
        BaseGaodeAmapView.setupMapView(aMap, context)
//        BaseGaodeAmapView.setupLocationStyle(aMap, false)

        showCircleLocation(aMap)
        drawMarkers(aMap)
        initSelection(aMap)

    }

    /**
     * 定位中心居中
     */
    fun initSelection(aMap: AMap?) {
        aMap?.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
            override fun onCameraChange(cameraPosition: CameraPosition) {
//                if (cameraPosition.isAbroad)

            }

            override fun onCameraChangeFinish(cameraPosition: CameraPosition) {
//                val curLatlng = LatLng(cameraPosition.target.latitude, cameraPosition.target.latitude)
//                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLatlng, 16f))
//                locationMarker?.position = cameraPosition.target
//                    moveAMap(getMapCenterPoint(), aMap)

            }
        })
    }

    /**
     * 移动到指定经纬度
     */
    protected fun moveAMap(latLng: LatLng, aMap: AMap) {
        val cameraPosition = CameraPosition(latLng, 15f, 0f, 30f)
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        aMap.moveCamera(cameraUpdate)
        showCircleLocation(aMap)

    }

    /**
     * func:获取屏幕中心的经纬度坐标
     * @return
     */
    fun getMapCenterPoint(): LatLng {
        val left = mMapView?.getLeft()
        val top = mMapView?.getTop()
        val right = mMapView?.getRight()
        val bottom = mMapView?.getBottom()
        // 获得屏幕点击的位置
        val x = (mMapView?.x?.plus(right!! - left!!)?.div(2))?.toInt()
        val y = (mMapView?.y?.plus((bottom!! - top!!).div(2)))?.toInt()
        val projection = aMap?.projection

        return projection!!.fromScreenLocation(Point(x!!, y!!))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMapView?.onCreate(outState)// 此方法必须重写
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()

    }

    override fun onPause() {
        super.onPause()
        mMapView?.onPause()

    }

    override fun onDetach() {
        super.onDetach()
        BaseGaodeAmap.destoryOnce(locationClientSingle)

    }

    /**
     * 绘制面
     * 地图上的面分为圆形和多边形两种。 如下：官网地址：
     * https://lbs.amap.com/api/android-sdk/guide/draw-on-map/draw-plane
     *
     * @param aMap
     */
    protected fun showCircleLocation(aMap: AMap?) {
        aMap?.addCircle(CircleOptions().radius(400.0).fillColor(Color.parseColor("#663A7CFF")).strokeColor(Color.parseColor("#663A7CFF")).strokeWidth(2f))
    }

    //画定位标记图
    protected fun drawMarkers(aMap: AMap?) {
        val markerOptions = MarkerOptions()
                .draggable(true)
        val marker = aMap?.addMarker(markerOptions)
        marker?.showInfoWindow()
        showAnimation(marker, false)

    }

    /**
     * 定位标记图动画
     */
    protected fun showAnimation(marker: Marker?, isShowAnimation: Boolean) {
        if (isShowAnimation) {
            val animation = RotateAnimation(marker!!.rotateAngle, marker.rotateAngle + 180, 0f, 0f, 0f)
            val duration = 1000L
            animation.setDuration(duration)
            animation.setInterpolator(LinearInterpolator())
            marker.setAnimation(animation)
            marker.startAnimation()
        }

    }


}
