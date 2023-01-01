package com.example.baseproject

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.baseproject.base.BaseActivity
import com.example.baseproject.base.BaseBottomSheetDialogFragment
import com.example.baseproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = injectBinding(this, getViewLayout())
        setView()
    }

    override fun setView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

    }


    override fun getViewLayout(): Int = R.layout.activity_main
    override fun getActivity(): Activity = this
    override fun injectBinding(activity: Activity, view: Int): ActivityMainBinding =
        DataBindingUtil.setContentView(this, view)
}