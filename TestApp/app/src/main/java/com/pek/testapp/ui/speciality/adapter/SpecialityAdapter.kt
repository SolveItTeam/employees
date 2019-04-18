package com.pek.testapp.ui.speciality.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pek.testapp.R
import com.pek.testapp.ui.base.adapter.BaseItem
import com.pek.testapp.ui.base.adapter.BaseViewHolder
import com.pek.testapp.utils.inflate

class SpecialityAdapter(
    items: List<BaseItem>,
    var onItemClick: ((BaseItem) -> Unit)? = null
) : RecyclerView.Adapter<BaseViewHolder>() {

    var items: List<BaseItem> = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        SpecialityViewHolder(parent.inflate(R.layout.item_speciality))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

}