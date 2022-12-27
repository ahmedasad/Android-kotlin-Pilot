package com.example.baseproject.base

import android.app.Application
import com.example.baseproject.data.localdb.AppDatabase
import com.example.baseproject.data.network.NetworkClient
import com.example.baseproject.data.network.services.Service1
import com.example.baseproject.data.network.services.Service2
import com.example.baseproject.data.repository.Service1Repository
import com.example.baseproject.data.repository.Service2Repository
import com.example.baseproject.ui.fragments.examplefragment.BlankViewModel
import com.example.baseproject.utils.SharedPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPreferences.with(this)

        val appModule = module {
            single { Application() }
        }

        val networkModule = module {
            single { NetworkClient() }
        }

        val appAPIInterfaceModule = module {
            single { (get() as NetworkClient).createApiEndPoint(Service1::class.java) }
            single { (get() as NetworkClient).createApiEndPoint(Service2::class.java) }
        }

        val dbModule = module {
            single { AppDatabase.getDataBase(applicationContext) }
            single { (get() as AppDatabase).userDao() }
        }


        val repositoryModule = module {
            single { Service1Repository(get(),get()) }
            single { Service2Repository(get()) }
        }

        val viewModelModule = module {
            viewModel { BlankViewModel(get(), get()) }
        }

        startKoin {
            modules(
                appModule,
                networkModule,
                appAPIInterfaceModule,
                dbModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}