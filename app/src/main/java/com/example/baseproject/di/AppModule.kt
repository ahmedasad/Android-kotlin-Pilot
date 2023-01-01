package com.example.baseproject.di

import android.app.Application
import com.example.baseproject.data.localdb.AppDatabase
import com.example.baseproject.data.localdb.dao.UserDAO
import com.example.baseproject.data.network.NetworkClient
import com.example.baseproject.data.network.services.Service1
import com.example.baseproject.data.network.services.Service2
import com.example.baseproject.data.repository.Service1Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getService1():Service1 = NetworkClient().createApiEndPoint(Service1::class.java)

    @Provides
    @Singleton
    fun getService2():Service2 = NetworkClient().createApiEndPoint(Service2::class.java)

    @Provides
    @Singleton
    fun getUserDao(app: Application):UserDAO = AppDatabase.getDataBase(app).userDao()

    @Provides
    @Singleton
    fun getService1Repo(service:Service1, dao: UserDAO):Service1Repository = Service1Repository(service,dao)

}