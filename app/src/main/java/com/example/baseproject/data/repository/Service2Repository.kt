package com.example.baseproject.data.repository

import com.example.baseproject.data.network.services.Service2

class Service2Repository(private val service2: Service2) :
    BaseRepository() {
    val items = getNetworkResult { service2.getUsers() }
}