package com.pek.testapp.ui.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pek.testapp.R

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val root: View? = view.findViewById(R.id.root)

    fun setOnClickListener(onItemClick: (() -> Unit)?) {
        root?.setOnClickListener {
            onItemClick?.invoke()
        }
    }

    abstract fun bind(item: BaseItem)

    fun context() = itemView.context

}