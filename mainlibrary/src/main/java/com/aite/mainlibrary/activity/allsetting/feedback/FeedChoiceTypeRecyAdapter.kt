package com.aite.mainlibrary.activity.allsetting.feedback

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.aite.mainlibrary.Mainbean.IBaseBean
import com.aite.mainlibrary.R
import com.aite.mainlibrary.R2
import com.lzy.basemodule.OnClickLstenerInterface

import butterknife.BindView
import butterknife.ButterKnife
import com.aite.a.zxing.ViewfinderView
import com.aite.mainlibrary.activity.allsetting.feedback.FeedChoiceTypeRecyAdapter.ViewHolder

class FeedChoiceTypeRecyAdapter : RecyclerView.Adapter<FeedChoiceTypeRecyAdapter.ViewHolder> {
    /*TestBean testBean*/
    private val context: Context
    private val listBean: MutableList<FeedListBean>?

    constructor(context: Context, listBean:MutableList<FeedListBean>?) : super() {
        this.context = context
        this.listBean = listBean
        this.inflater = LayoutInflater.from(context)
    }

    private val inflater: LayoutInflater

    var clickInterface: OnClickLstenerInterface.OnRecyClickInterface? = null
    var setClickInterfaceAndString: OnClickLstenerInterface.OnRecyClickInterfaceAndString? = null

    fun clearData() {
        if (!listBean!!.isEmpty()) {
            listBean.clear()
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.radiogroup_recy_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle!!.text = listBean!![position].type_name
        holder.ivImg!!.visibility = if (listBean[position].selected!!) View.VISIBLE else View.GONE
        holder.tvTitle!!.setTextColor(context.resources.getColor(if (listBean[position].selected!!) R.color.agreen else R.color.black))
        holder.itemView.setOnClickListener {
            listBean[position].selected=true
            for (i in listBean.indices) {
                listBean[i].selected = i == position
            }
            notifyDataSetChanged()
            if (clickInterface != null)
                clickInterface!!.getPosition(Integer.valueOf(listBean[position].id!!))
        }

    }

    override fun getItemCount(): Int {
        return listBean?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null
        var ivImg: ImageView? = null
        var father_layout: LinearLayout? = null


        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            ivImg=itemView.findViewById(R.id.iv_img)
            father_layout=itemView.findViewById(R.id.father_layout)

        }
    }
}
