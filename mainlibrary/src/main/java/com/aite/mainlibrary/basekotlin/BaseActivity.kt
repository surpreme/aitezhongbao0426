package com.aite.mainlibrary.basekotlin

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aite.mainlibrary.R
import com.lzy.basemodule.view.StatusBarUtils
import kotlinx.android.synthetic.main.activity_feedback.*

/**Lqayou

 * @Auther: liziyang

 * @datetime: 2020-01-18

 * @desc:
 * ?.意思是这个参数可以为空,并且程序继续运行下去

 * !!.的意思是这个参数如果为空,就抛出异常

 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutId(): Int
    abstract fun initViews()
    abstract fun initDatas()
    protected var back: ImageView? = null
    protected var toolbar_title: TextView? = null
    protected var mContext: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtils.setColor(this, getColor(R.color.white))
        }
        initViews()
        initDatas()
    }

    protected fun initToolBar(title: CharSequence) {
        back = this.findViewById(R.id.iv_back)
        back?.setOnClickListener { onBackPressed() }
        toolbar_title = this.findViewById(R.id.tv_title)
        toolbar_title?.text = title
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }

    fun startActivity(mClz: Class<*>) {
        startActivity(Intent(this, mClz))
    }

    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}