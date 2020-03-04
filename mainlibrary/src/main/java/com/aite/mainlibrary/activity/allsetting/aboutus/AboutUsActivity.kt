package com.aite.mainlibrary.activity.allsetting.aboutus


import android.annotation.SuppressLint
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import com.aite.mainlibrary.R
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.lzy.okgo.model.HttpParams
import com.lzy.basemodule.BaseConstant.AppConstant
import com.lzy.basemodule.BaseConstant.BaseConstant
import com.lzy.basemodule.logcat.LogUtils
import com.lzy.basemodule.util.VersionUtils
import com.vector.update_app.UpdateAppBean
import com.vector.update_app.UpdateAppManager
import com.vector.update_app.listener.ExceptionHandler
import com.vector.update_app_kotlin.check
import kotlinx.android.synthetic.main.activity_about_us.*
import org.json.JSONObject
import java.lang.Exception


/**

 * @Auther: liziyang

 * @datetime: 2020-01-18

 * @desc:

 */
open class AboutUsActivity : BaseMVPActivity<AboutUsContract.View, AboutUsPresenter>(), AboutUsContract.View {


    override fun getLayoutId(): Int {
        return R.layout.activity_about_us
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        initToolBar("关于我们")
        setStatusBar(true)
        if (VersionUtils.getAppVersionName(mContext) != null)
            version_tv.text = "版本号${VersionUtils.getAppVersionName(mContext)}"

    }

    override fun initDatas() {
        mPresenter?.getUpdateVersion(initHttpParam())
    }

    private fun initHttpParam(): HttpParams {
        val httpParams = HttpParams()
        httpParams.put("key", AppConstant.KEY)
        return httpParams
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onGetUpDateVersionSuccess(msg: UpDateVersionBean) {
        LogUtils.d(msg.getVersion())
        LogUtils.d(msg.getUrl())
        if (intent != null) {
            if (intent.getStringExtra("isShowUpdate") != null) {
                if (intent.getStringExtra("isShowUpdate").equals("true")) {
                    updateVersion(AppConstant.GET_UPDATE_VERSION_URL)
                }
            }
        }

    }

    /**
     * 默认是在下载到sd卡下/Download/1.0.0/test.apk
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateVersion(mUpdateUrl: String) {
        UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(UpdateAppHttpUtil())
                .setUpdateUrl(mUpdateUrl)
                //全局异常捕获
                .handleException(object : ExceptionHandler {
                    override fun onException(it: Exception) {
                        LogUtils.e("${it.javaClass}${it.message}${it.cause} ")

                    }
                })
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
//                .setTopPic(R.mipmap.lib_update_app_top_bg)
                //为按钮，进度条设置颜色，默认从顶部图片自动识别。
                .setThemeColor(getColor(R.color.agreen))
                .setOnlyWifi()
                .build()
                //检测是否有新版本
                .check {
                    parseJson {
                        val jsonObject = JSONObject(it)
                        val datasObject = jsonObject.optJSONObject("datas")
                        val isUpdate: String = VersionUtils.getAppVersionCode(mContext)
                        val olderVersion: Int = isUpdate.toInt()
                        val newVersion: Int = datasObject!!.optString("version").toInt()
                        val switchUpdate: Boolean = olderVersion < newVersion
                        UpdateAppBean()
                                .setUpdate(if (switchUpdate) "Yes" else "No")
                                //（必须）新版本号，
                                .setNewVersion("${datasObject.optString("version")}.0.0")
                                //（必须）下载地址
                                .setApkFileUrl(datasObject.optString("url"))
                                //（必须）更新内容
                                .setUpdateLog("\n" +
                                        "最新版本${datasObject.optString("version")}.0.0已经上线啦~\n" +
                                        " 立即更新获得更好的体验！\n")
                                //是否强制更新，可以不设置
                                .setConstraint(false)

                    }
                }

    }


    override fun onDestroys() {
    }

}