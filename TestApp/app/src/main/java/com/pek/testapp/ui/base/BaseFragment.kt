package com.pek.testapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pek.testapp.R
import com.pek.testapp.data.exception.ApiException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutRes(), container, false)

    abstract fun getLayoutRes(): Int

    protected fun showError(exception: Exception) = when (exception) {
        is ConnectException -> showMessage(getString(R.string.error_connection_troubles))
        is UnknownHostException -> showMessage(getString(R.string.error_connection_troubles))
        is SocketTimeoutException -> showMessage(getString(R.string.error_connection_troubles))
        is ApiException -> showMessage(getString(R.string.error_server))
        else -> showMessage(getString(R.string.error_unknown))
    }


    private fun showMessage(msg: String) {
        context?.let {
            Toast.makeText(it, msg, Toast.LENGTH_LONG).show()
        }
    }

}