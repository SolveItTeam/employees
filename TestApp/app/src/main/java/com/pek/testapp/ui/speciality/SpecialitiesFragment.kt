package com.pek.testapp.ui.speciality

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pek.testapp.R
import com.pek.testapp.data.database.model.Speciality
import com.pek.testapp.ui.Router
import com.pek.testapp.ui.base.BaseFragment
import com.pek.testapp.ui.employees.EmployeesFragment
import com.pek.testapp.ui.speciality.adapter.SpecialityAdapter
import kotlinx.android.synthetic.main.fragment_specialities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpecialitiesFragment : BaseFragment() {

    companion object {
        fun newInstance() = SpecialitiesFragment()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_specialities

    private lateinit var specialitiesAdapter: SpecialityAdapter

    private val specialitiesViewModel by viewModel<SpecialitiesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSpecialitiesRv()
        observeViewModel()
    }

    private fun initSpecialitiesRv() {
        specialitiesAdapter = SpecialityAdapter(listOf(), specialitiesViewModel::onSpecialityClick)
        rvSpecialities.layoutManager = LinearLayoutManager(context)
        rvSpecialities.adapter = specialitiesAdapter
    }

    private fun observeViewModel() {
        specialitiesViewModel.specialitiesLiveData.observe(this, Observer {
            updateSpecialities(it)
        })

        specialitiesViewModel.openEmployeesBySpecialityEvent.observe(this, Observer {
            if (it.isNotConsumed()) {
                (activity as? Router)?.replace(EmployeesFragment.newInstance(it.data ?: EmployeesFragment.NO_SPECIALITY))
            }
        })
    }

    private fun updateSpecialities(specialities: List<Speciality>) {
        specialitiesAdapter.items = specialities
    }

}