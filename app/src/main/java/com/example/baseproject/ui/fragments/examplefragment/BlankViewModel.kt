package com.example.baseproject.ui.fragments.examplefragment

import android.app.Application
import com.example.baseproject.base.BaseViewModel
import com.example.baseproject.data.repository.Service1Repository

class BlankViewModel(repo: Service1Repository, val app: Application) :
    BaseViewModel(app) {
        val item = repo.items
}