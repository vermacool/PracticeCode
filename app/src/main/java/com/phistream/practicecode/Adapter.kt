package com.phistream.practicecode

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.tasklistitem.view.*

/**
 * Created by Manish verma on 1/23/2018.
 * Allright reserved by MMF.
 */
class Adapter(var sampleTask: List<Model>, private val itemClick: (Model) -> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindTasks(sampleTask[position])

    }

    override fun getItemCount(): Int {
        return sampleTask.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.tasklistitem, parent, false)
        return ViewHolder(view, itemClick)
    }

    class ViewHolder(itemview: View?, private val itemClick: (Model) -> Unit) : RecyclerView.ViewHolder(itemview) {
        fun bindTasks(mModel: Model) = with(mModel) {
            itemView.tvTitle.text = mModel.title
            itemView.tvDetails.text = mModel.details
            itemView.setOnClickListener { itemClick(this) }
        }
    }
}