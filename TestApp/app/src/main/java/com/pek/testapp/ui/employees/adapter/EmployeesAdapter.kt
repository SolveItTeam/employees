package com.pek.testapp.ui.employees.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pek.testapp.R
import com.pek.testapp.ui.base.adapter.BaseItem
import com.pek.testapp.ui.base.adapter.BaseViewHolder
import com.pek.testapp.utils.inflate

class EmployeesAdapter(
    items: List<BaseItem>,
    var onItemClick: ((BaseItem) -> Unit)? = null
) : RecyclerView.Adapter<BaseViewHolder>() {

    var items: List<BaseItem> = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        EmployeeViewHolder(parent.inflate(R.layout.item_employee))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

}