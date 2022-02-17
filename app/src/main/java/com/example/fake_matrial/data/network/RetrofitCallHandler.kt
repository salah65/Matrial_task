package com.example.fake_matrial.data.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher=Dispatchers.IO, apiCall: suspend () -> T): ResponseWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResponseWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResponseWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    ResponseWrapper.GenericError(code, throwable.localizedMessage)
                }
                else -> {
                    ResponseWrapper.GenericError(null, null)
                }
            }
        }
    }
}