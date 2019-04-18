package com.pek.testapp.di

import com.pek.testapp.data.database.EmployeesDatabase
import com.pek.testapp.data.network.RetrofitFactory
import com.pek.testapp.data.repository.EmployeeRepository
import com.pek.testapp.data.repository.EmployeeRepositoryImpl
import com.pek.testapp.ui.employees.EmployeesViewModel
import com.pek.testapp.ui.employees.details.EmployeeDetailsViewModel
import com.pek.testapp.ui.main.MainViewModel
import com.pek.testapp.ui.speciality.SpecialitiesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    single { EmployeeRepositoryImpl(get(), get()) as EmployeeRepository }

    factory { EmployeesDatabase.getInstance(get()) }
    factory { RetrofitFactory.buildRetrofitApi() }

    viewModel { MainViewModel(get(), get()) }
    viewModel { EmployeesViewModel(get(), get()) }
    viewModel { SpecialitiesViewModel(get(), get()) }
    viewModel { EmployeeDetailsViewModel(get(), get()) }
}