package com.aite.mainlibrary.activity.im.activity

import com.aite.mainlibrary.R
import com.aite.mainlibrary.basekotlin.BaseActivity

/**

 * @Auther: liziyang

 * @datetime: 2020-01-20

 * @desc: 请不要删除此文件

 */
class MessageActivity : BaseActivity(){
    override fun initViews() {

    }

    override fun initDatas() {
        initToolBar("咨询列表")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_message
    }

}