package com.pek.testapp.ui

import com.pek.testapp.ui.base.BaseFragment

interface Router {

    fun replace(fragment: BaseFragment, addToStack: Boolean = true)

}