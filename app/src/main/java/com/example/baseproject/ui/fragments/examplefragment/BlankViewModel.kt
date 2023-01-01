package com.example.baseproject.ui.fragments.examplefragment

import android.app.Application
import com.example.baseproject.base.BaseViewModel
import com.example.baseproject.data.repository.Service1Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlankViewModel @Inject constructor(repo: Service1Repository, val app: Application) :
    BaseViewModel(app) {
        val item = repo.items
}