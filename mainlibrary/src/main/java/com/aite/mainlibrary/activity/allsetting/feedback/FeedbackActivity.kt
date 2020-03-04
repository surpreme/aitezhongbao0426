package com.aite.mainlibrary.activity.allsetting.feedback

import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.mainlibrary.Mainbean.MainUiDataBean
import com.aite.mainlibrary.R
import com.aite.mainlibrary.adapter.AirServiceRecyAdapter
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.google.gson.Gson
import com.lzy.basemodule.BaseConstant.AppConstant
import com.lzy.basemodule.bean.ErrorBean
import com.lzy.basemodule.logcat.LogUtils
import kotlinx.android.synthetic.main.activity_feedback.*
import org.json.JSONArray
import java.util.ArrayList
import com.lzy.basemodule.OnClickLstenerInterface
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import com.lzy.okgo.model.HttpParams


/**

 * @Auther: liziyang

 * @datetime: 2020-01-18

 * @desc:

 */
open class FeedbackActivity : BaseMVPActivity<FeedbackContract.View, FeedBackPresenter>(), FeedbackContract.View {


    private var feedListBean = ArrayList<FeedListBean>()
    private var feedChoiceTypeRecyAdapter: FeedChoiceTypeRecyAdapter? = null
    private var mFeedIndex: Int = 1


    override fun getLayoutId(): Int {
        return R.layout.activity_feedback
    }

    override fun initViews() {
        initToolBar("意见反馈")
        setStatusBar(true)
        submit_btn.setOnClickListener { v ->
            mPresenter!!.commitInformation(AppConstant.KEY, opinion_edit.text.toString(), mFeedIndex.toString(), "1")
        }
    }

    override fun initDatas() {
        mPresenter!!.getComplaintList(AppConstant.KEY)

    }

    override fun onGetComplaintSuccess(msg: JSONArray) {
        for (i in 0 until msg.length()) {
            val bean: FeedListBean = Gson().fromJson<FeedListBean>(msg.get(i).toString(), FeedListBean::class.java)
            feedListBean.add(bean)
        }
        if (!feedListBean.isNullOrEmpty()) {
            if (feedChoiceTypeRecyAdapter == null) {
                feedChoiceTypeRecyAdapter = FeedChoiceTypeRecyAdapter(mContext!!, feedListBean)
                feedChoiceTypeRecyAdapter!!.clickInterface = OnClickLstenerInterface.OnRecyClickInterface { position ->
                    mFeedIndex = position
                    LogUtils.e(mFeedIndex)

                }
                val manager = LinearLayoutManager(mContext!!, LinearLayoutManager.VERTICAL, false)
                if (feedListBean.size > 0)
                    feedListBean[0].selected = true
                grade_recy.adapter = feedChoiceTypeRecyAdapter
                grade_recy.layoutManager = manager
            }
        }
    }


    override fun onDestroys() {
    }

    override fun commitInformationSuccess(msg: String) {
        if (msg == "Success") {
            showToast("提交成功 感谢您的反馈！")
            onBackPressed()
        }
    }


}