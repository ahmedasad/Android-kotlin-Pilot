package com.example.baseproject.di

import com.example.baseproject.data.localdb.dao.UserDAO
import com.example.baseproject.data.network.services.Service1
import com.example.baseproject.data.repository.Service1Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Repository {

}