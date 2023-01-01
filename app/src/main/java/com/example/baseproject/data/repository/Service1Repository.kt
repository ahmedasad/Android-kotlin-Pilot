package com.example.baseproject.data.repository

import com.example.baseproject.data.localdb.dao.UserDAO
import com.example.baseproject.data.network.services.Service1
import javax.inject.Inject


class Service1Repository @Inject constructor(private val service1: Service1, private val dao: UserDAO) :
    BaseRepository() {
    val items =
//        getNetworkResult { service1.getUsers() }
        getRequestWithResult({ dao.getUsers() }, { service1.getUsers() }, { dao.insertUsers(it) })
}