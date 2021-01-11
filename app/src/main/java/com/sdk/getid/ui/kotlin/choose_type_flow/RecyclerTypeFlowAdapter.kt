package com.sdk.getid.ui.kotlin.choose_type_flow

import androidx.recyclerview.widget.RecyclerView
import com.sdk.getid.R
import com.sdk.getid.model.app.flow.TypeFlow
import com.sdk.getid.ui.kotlin.common.recycler.BaseAdapter
import kotlinx.android.synthetic.main.recycler_type_flow_item.view.*


/**
 * Created by Pavlo Kuchirka on 01-Nov-19.
 */
class RecyclerTypeFlowAdapter : BaseAdapter<TypeFlow>(R.layout.recycler_type_flow_item) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, currentItem: TypeFlow) {
        holder.itemView.apply {
            imageView.setImageResource(currentItem.drawableRes)
            textView.setText(currentItem.titleRes)
        }
    }
}
