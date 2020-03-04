package com.aite.mainlibrary.activity.allsetting.aboutus

import com.lzy.basemodule.bean.ErrorBean
import java.io.Serializable

/**
 * @Auther: liziyang
 * @datetime: 2020-01-19
 * @desc:
 */
class UpDateVersionBean : ErrorBean(), Serializable {
    /**
     * version : 1.0
     * url : null
     */

    private var version: String? = null
    private var url: Any? = null

    fun getVersion(): String? {
        return version
    }

    fun setVersion(version: String) {
        this.version = version
    }

    fun getUrl(): Any? {
        return url
    }

    fun setUrl(url: Any) {
        this.url = url
    }
}
