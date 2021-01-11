package com.sdk.getid.ui.kotlin.common.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Pavlo Kuchirka on 18-Oct-19.
 */
abstract class BaseAdapter<T>(private val layoutItemRes: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemList: ArrayList<T> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, currentItem: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CommonVH(
            LayoutInflater.from(parent.context).inflate(layoutItemRes, parent, false)
        )

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(holder, itemList[position])
    }
}
