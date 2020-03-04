package com.aite.mainlibrary.basekotlin

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aite.mainlibrary.Mainbean.AirMainListBean
import com.aite.mainlibrary.R
import com.lzy.basemodule.activitylife.ActivityManager
import com.lzy.basemodule.logcat.LogUtils
import com.lzy.basemodule.view.StatusBarUtils
import com.lzy.okgo.model.HttpParams
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import java.lang.reflect.ParameterizedType
import java.util.ArrayList

/**

 * @Auther: liziyang

 * @datetime: 2020-01-19

 * @desc:

 */
abstract class BaseMVPListActivity<V : BaseView, T : BasePresenterImpl<V>> : AppCompatActivity(), BaseView {
    abstract fun getLayoutId(): Int
    abstract fun initViews()
    abstract fun initDatas()
    abstract fun getSmartDatas()
    abstract fun onDestroys()
    protected var back: ImageView? = null
    protected var toolbar_title: TextView? = null
    protected var smartRefreshLayout: SmartRefreshLayout? = null
    var mPresenter: T? = null
    protected var mContext: Context? = null
    protected var mCurrentPage: Int = 1


    override fun getContext(): Context {
        return this
    }

    /**
     *         布局id
     *         mvp
     *         初始化视图
     *         初始化数据处理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mPresenter = getInstance<T>(this, 1)
        mPresenter!!.attachView(this as V)
        mContext = this
        initViews()
        initDatas()
    }

    protected fun <G> clearData(list: ArrayList<G>) {
        list.clear()
    }

    /**
     * isRefresh 是否可以上拉加载数据
     */
    protected fun initSmartLayout(isRefresh: Boolean, mRefreshHeader: RefreshHeader?, mRefreshFooter: RefreshFooter?) {
        smartRefreshLayout = this.findViewById(R.id.smartlayout)
        smartRefreshLayout?.setEnableAutoLoadMore(isRefresh)
        if (mRefreshFooter != null)
            smartRefreshLayout?.setRefreshFooter(mRefreshFooter)
        else smartRefreshLayout?.setRefreshFooter(ClassicsFooter(this))
        if (mRefreshHeader != null) {
            smartRefreshLayout?.setRefreshHeader(mRefreshHeader)
        } else {
            smartRefreshLayout?.setRefreshHeader(WaterDropHeader(this))
        }
        mCurrentPage = 1
        smartRefreshLayout?.setOnRefreshListener { refreshLayout ->
            mCurrentPage = 1
            getSmartDatas()
            smartRefreshLayout?.finishRefresh(700)

        }

        smartRefreshLayout?.setOnLoadMoreListener { refreshLayout ->
            mCurrentPage++
            LogUtils.d("---------------------------------------------------------------------"+mCurrentPage+"---------------------------------------------------------------------")
            getSmartDatas()
            smartRefreshLayout?.finishLoadMore(700)

        }


    }


    protected fun initToolBar(title: CharSequence) {
        back = this.findViewById(R.id.iv_back)
        back!!.setOnClickListener { onBackPressed() }
        toolbar_title = this.findViewById(R.id.tv_title)
        toolbar_title!!.text = title
    }

    protected fun setStatusBar(isUse: Boolean) {
        if (isUse) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StatusBarUtils.setColor(this, getColor(R.color.white))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null)
            mPresenter!!.detachView()
        onDestroys()
    }

    fun <T> getInstance(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                    .newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }

    override fun showError(msg: String) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show()
        LogUtils.e(msg + this.localClassName)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        LogUtils.d(message + this.localClassName)
    }

    protected fun getScreenWidth(): Int {
        return mContext?.resources?.displayMetrics?.widthPixels!!
    }

    protected fun getScreenHeight(): Int {
        return mContext?.resources?.displayMetrics?.heightPixels!!
    }

    fun startActivity(mClz: Class<*>) {
        startActivity(Intent(this, mClz))
    }

    fun startActivity(mClz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, mClz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}