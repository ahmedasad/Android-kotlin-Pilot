package com.example.baseproject.base

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<out VDB> :
    AppCompatActivity() {

    /* Abstract Methods*/
    abstract fun getViewLayout(): Int
    abstract fun getActivity(): Activity
    abstract fun setView()

    abstract fun injectBinding(activity: Activity, view: Int): VDB
}