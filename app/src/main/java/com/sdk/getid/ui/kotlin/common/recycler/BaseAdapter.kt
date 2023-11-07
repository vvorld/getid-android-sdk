package com.sdk.getid.ui.kotlin.common.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemList: ArrayList<T> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        return CommonVH(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(holder, itemList[position])
    }

    abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, currentItem: T)
}
