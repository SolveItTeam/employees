package com.pek.testapp.ui.employees

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pek.testapp.R
import com.pek.testapp.data.database.model.Employee
import com.pek.testapp.ui.Router
import com.pek.testapp.ui.base.BaseFragment
import com.pek.testapp.ui.employees.adapter.EmployeesAdapter
import com.pek.testapp.ui.employees.details.EmployeeDetailsFragment
import kotlinx.android.synthetic.main.fragment_employee.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmployeesFragment : BaseFragment() {

    companion object {

        private const val ARG_SPECIALITY_ID = "ARG_SPECIALITY_ID"
        const val NO_SPECIALITY = -1L

        fun newInstance(specialityId: Long = NO_SPECIALITY) = EmployeesFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_SPECIALITY_ID, specialityId)
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_employee

    private val employeesViewModel by viewModel<EmployeesViewModel>()

    private lateinit var employeesAdapter: EmployeesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadArguments()
        initRv()
        observeViewModel()

        employeesViewModel.loadEmployees()
    }

    private fun loadArguments() {
        arguments?.let {
            val specialityId = it.getLong(ARG_SPECIALITY_ID, NO_SPECIALITY)
            if (specialityId != NO_SPECIALITY) {
                employeesViewModel.updateSpecialityId(specialityId)
            }
        }
    }

    private fun initRv() {
        employeesAdapter = EmployeesAdapter(listOf(), employeesViewModel::onEmployeeClick)
        rvEmployees.layoutManager = LinearLayoutManager(context)
        rvEmployees.adapter = employeesAdapter
    }

    private fun observeViewModel() {
        employeesViewModel.employeesLiveData.observe(this, Observer {
            updateEmployees(it)
        })

        employeesViewModel.openEmployeesLiveData.observe(this, Observer {
            if (it.isNotConsumed()) {
                (activity as? Router)?.replace(EmployeeDetailsFragment.newInstance(it.data!!))
            }
        })
    }

    private fun updateEmployees(items: List<Employee>) {
        employeesAdapter.items = items
    }

}