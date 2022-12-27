package com.example.baseproject.data.network.services

import com.example.baseproject.data.network.Urls
import com.example.baseproject.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface Service1 {

    @GET(Urls.USERS_URL)
    suspend fun getUsers(): Response<ArrayList<User>>
}