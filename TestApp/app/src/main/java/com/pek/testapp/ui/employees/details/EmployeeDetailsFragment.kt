package com.pek.testapp.ui.employees.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.pek.testapp.R
import com.pek.testapp.data.network.model.EmployeeData
import com.pek.testapp.ui.base.BaseFragment
import com.pek.testapp.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_employee_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException

class EmployeeDetailsFragment : BaseFragment() {

    companion object {

        private const val ARG_EMPLOYEE_ID = "ARG_EMPLOYEE_ID"
        private const val NO_EMPLOYEE_ID = -1L

        fun newInstance(employeeId: Long) = EmployeeDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_EMPLOYEE_ID, employeeId)
            }
        }
    }

    private val employeeDetailsViewModel by viewModel<EmployeeDetailsViewModel>()

    override fun getLayoutRes(): Int = R.layout.fragment_employee_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadArguments()
        observeViewModel()

        employeeDetailsViewModel.loadEmployee()
    }

    private fun loadArguments() {
        arguments?.let {
            val employeeId = it.getLong(ARG_EMPLOYEE_ID, NO_EMPLOYEE_ID)
            if (employeeId == NO_EMPLOYEE_ID) {
                throw IllegalArgumentException("Arguments must have employeeId!")
            }
            employeeDetailsViewModel.updateEmployeeId(employeeId)
        }
    }

    private fun observeViewModel() {
        employeeDetailsViewModel.employeeDataLiveData.observe(this, Observer {
            updateEmployeeData(it)
        })
    }

    private fun updateEmployeeData(employeeData: EmployeeData) {
        tvName.text = "${employeeData.firstName} ${employeeData.lastName}"

        tvBirthday.text = employeeData.birthday?.let {
            getString(R.string.birthday_s, DateUtils.dateToFormattedString(it))
        } ?: "---"

        tvAge.text = employeeData.birthday?.let {
            getString(R.string.age_d, DateUtils.getAge(it))
        } ?: "---"

        val specialities = StringBuilder()
        employeeData.specialities?.forEach {
            specialities.append("\n")
            specialities.append(it.name)
        }

        tvSpecialities.text = getString(R.string.specialities_s, specialities.toString())
    }

}