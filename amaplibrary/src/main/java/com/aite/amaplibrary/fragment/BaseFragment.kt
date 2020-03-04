package com.aite.amaplibrary.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

/**Lqayou

 * @Auther: liziyang

 * @datetime: 2020-01-18

 * @desc:
 * ?.意思是这个参数可以为空,并且程序继续运行下去

 * !!.的意思是这个参数如果为空,就抛出异常

 */
abstract class BaseFragment : Fragment() {
    abstract fun getLayoutResId(): Int
    abstract fun initViews(view: View,savedInstanceState: Bundle?)
    abstract fun initDatas()
    protected var mContext: Context? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    protected fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view,savedInstanceState)
        initDatas()
    }

    fun startActivity(mClz: Class<*>) {
        startActivity(Intent(activity, mClz))
    }



    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(activity!!, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }


}