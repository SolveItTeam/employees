package com.pek.testapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pek.testapp.R
import com.pek.testapp.ui.base.BaseFragment
import com.pek.testapp.ui.main.MainFragment

class MainActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replace(MainFragment.newInstance(), false)
    }

    override fun replace(fragment: BaseFragment, addToStack: Boolean) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .apply {
                if (addToStack) {
                    addToBackStack(null)
                }
            }
            .commit()
    }

}
