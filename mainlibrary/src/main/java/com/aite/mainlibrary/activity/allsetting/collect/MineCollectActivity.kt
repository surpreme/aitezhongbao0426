package com.aite.mainlibrary.activity.allsetting.collect

import android.content.Intent
import android.widget.Toast

import com.aite.mainlibrary.R
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.aite.mainlibrary.basekotlin.BaseMVPListActivity
import com.lzy.basemodule.base.BaseActivity

class MineCollectActivity : BaseMVPListActivity<MineCollectContract.View, MineCollectPresenter>(), MineCollectContract.View {
    override fun onGetCollectServiceSuccess(mCollectListBean: CollectListBean) {
    }



    override fun getSmartDatas() {

    }

    override fun getLayoutId(): Int {
        return R.layout.mine_collect
    }

    override fun initViews() {
        initToolBar("我的收藏")
        initSmartLayout(true, null, null)
    }

    override fun initDatas() {

    }

    override fun onDestroys() {
    }

}
