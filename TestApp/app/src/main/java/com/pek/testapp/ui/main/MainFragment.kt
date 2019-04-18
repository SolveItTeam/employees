package com.pek.testapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.pek.testapp.R
import com.pek.testapp.ui.Router
import com.pek.testapp.ui.base.BaseFragment
import com.pek.testapp.ui.employees.EmployeesFragment
import com.pek.testapp.ui.speciality.SpecialitiesFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel by viewModel<MainViewModel>()

    override fun getLayoutRes(): Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModel()
        bindListeners()

        mainViewModel.loadEmployees()
    }

    private fun observeViewModel() {
        mainViewModel.loadingStateLiveData.observe(this, Observer {
            updateLoadingState(it)
        })

        mainViewModel.errorLiveData.observe(this, Observer {
            showError(it)
        })
    }

    private fun bindListeners() {
        btnBySpeciality.setOnClickListener {
            (activity as? Router)?.replace(SpecialitiesFragment.newInstance())
        }

        btnShowAllEmployees.setOnClickListener {
            (activity as? Router)?.replace(EmployeesFragment.newInstance())
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        if (isLoading) {
            content.visibility = View.GONE
            progress.visibility = View.VISIBLE
        } else {
            content.visibility = View.VISIBLE
            progress.visibility = View.GONE
        }
    }

}