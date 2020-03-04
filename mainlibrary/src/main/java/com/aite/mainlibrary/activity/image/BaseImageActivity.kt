package com.aite.mainlibrary.activity.image

import android.os.Build
import com.aite.mainlibrary.R
import com.aite.mainlibrary.basekotlin.BaseActivity
import com.bumptech.glide.Glide
import com.lzy.basemodule.view.StatusBarUtils
import kotlinx.android.synthetic.main.activity_image.*

/**
 * @Auther: liziyang
 * @datetime: 2020-01-20
 * @desc:
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class BaseImageActivity : BaseActivity() {
    override fun initViews() {
        initToolBar("")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtils.setColor(this, getColor(R.color.black))
        }
    }

    override fun initDatas() {
        val imageUrl: String = intent.getStringExtra("imageUrl")
        Glide.with(this).load(imageUrl).into(image_iv)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_image
    }
}
