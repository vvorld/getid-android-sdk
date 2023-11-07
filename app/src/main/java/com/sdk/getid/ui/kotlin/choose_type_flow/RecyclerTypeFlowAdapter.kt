package com.sdk.getid.ui.kotlin.choose_type_flow

import androidx.recyclerview.widget.RecyclerView
import com.sdk.getid.databinding.RecyclerTypeFlowItemBinding
import com.sdk.getid.model.app.flow.TypeFlow
import com.sdk.getid.ui.kotlin.common.recycler.BaseAdapter
import com.sdk.getid.ui.kotlin.common.recycler.CommonVH


class RecyclerTypeFlowAdapter :
    BaseAdapter<TypeFlow, RecyclerTypeFlowItemBinding>(RecyclerTypeFlowItemBinding::inflate) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, currentItem: TypeFlow) {
        val binding = (holder as? CommonVH)?.binding as? RecyclerTypeFlowItemBinding ?: return
        holder.itemView.apply {
            binding.imageView.setImageResource(currentItem.drawableRes)
            binding.textView.setText(currentItem.titleRes)
        }
    }
}
