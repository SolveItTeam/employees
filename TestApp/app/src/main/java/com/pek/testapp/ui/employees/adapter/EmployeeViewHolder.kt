package com.pek.testapp.ui.employees.adapter

import android.view.View
import com.pek.testapp.R
import com.pek.testapp.data.database.model.Employee
import com.pek.testapp.ui.base.adapter.BaseItem
import com.pek.testapp.ui.base.adapter.BaseViewHolder
import com.pek.testapp.utils.DateUtils
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeeViewHolder(view: View) : BaseViewHolder(view) {

    private val tvName = view.tvName
    private val tvBirthday = view.tvBirthday
    private val tvAge = view.tvAge

    override fun bind(item: BaseItem) {
        if (item !is Employee) {
            throw IllegalArgumentException("Item must be Employee!")
        }

        tvName.text = "${item.firstName} ${item.lastName}"

        tvBirthday.text = item.birthday?.let {
            context().getString(R.string.birthday_s, DateUtils.dateToFormattedString(it))
        } ?: "---"

        tvAge.text = item.birthday?.let {
            context().getString(R.string.age_d, DateUtils.getAge(it))
        } ?: "---"

    }

}