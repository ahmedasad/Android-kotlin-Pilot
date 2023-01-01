package com.example.baseproject.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.baseproject.data.network.NetworkClient
import com.example.baseproject.data.network.Result
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import retrofit2.Response

abstract class BaseRepository() {

    // for network operation result
    protected fun <T> getNetworkResult(
        call: suspend () -> Response<T>
    ): LiveData<Result<T>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            val response = call.invoke()
            emit(gatherResult(response))
        }

    private fun <T> getError(response: Response<T>): String = "${response.code()} ${
        JSONObject(response.errorBody()?.string())["message"] ?: "no specific error"
    }"

    private fun <T> gatherResult(response: Response<T>): Result<T> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) Result.Success(body)
            else Result.Error("No data Found")
        } else {
            Result.Error(
                getError(response)
            )
        }
    }


    fun <Query, Model> getRequestWithResult(
        dbGetDataQuery: () -> LiveData<Query>,
        networkCall: suspend () -> Response<Model>,
        insertIntoDb: suspend (Model) -> Unit
    ): LiveData<Result<Query>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)

            val data = dbGetDataQuery.invoke().map {
                Result.Success(it) as Result<Query>
            }

            emitSource(data)

            val responseStatus = networkCall.invoke()

            when (val res = gatherResult(responseStatus)) {
                is Result.Success -> {
                    insertIntoDb.invoke(res.data)
                }
                else -> {}
            }
        }
}