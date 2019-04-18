package com.pek.testapp.ui.speciality.adapter

import android.view.View
import com.pek.testapp.data.database.model.Speciality
import com.pek.testapp.ui.base.adapter.BaseItem
import com.pek.testapp.ui.base.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_speciality.view.*
import java.lang.IllegalArgumentException

class SpecialityViewHolder(view: View) : BaseViewHolder(view) {

    private val tvSpecialityName = view.tvSpecialityName

    override fun bind(item: BaseItem) {
        if (item !is Speciality) {
            throw IllegalArgumentException("Item must be Speciality!")
        }

        tvSpecialityName.text = item.name
    }

}