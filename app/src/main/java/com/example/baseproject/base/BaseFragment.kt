package com.example.baseproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<out VDB> : Fragment() {

    var progressBar: PopupWindow? = null
    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            handleArgument(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getViewLayout(), container, false)
        injectBinding(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onDestroy() {
        hideProgressBar()
        super.onDestroy()
    }

    protected fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes msg: Int) {
        showToast(getString(msg))
    }

    protected fun showSnack(msg: String) {
        view?.let{
            Snackbar.make(it, msg, Snackbar.LENGTH_SHORT).show()
        }
    }

    protected fun showSnack(@StringRes msg: Int) {
        showSnack(getString(msg))
    }

    protected fun showProgressBar(fullScreen: Boolean = false) {
        if (progressBar != null) return

    }

    protected fun hideProgressBar() {
        if (progressBar == null) return
        progressBar!!.dismiss()
        progressBar == null
    }

    open fun handleArgument(arguments: Bundle) {}

    /* Abstract Methods*/
    abstract fun getViewLayout(): Int
    abstract fun setView()
    abstract fun injectBinding(view: View): VDB
}