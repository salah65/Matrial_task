package com.example.fake_matrial.data.network


sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T) : ResponseWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null) : ResponseWrapper<Nothing>()
    object NetworkError : ResponseWrapper<Nothing>()
    object Loading : ResponseWrapper<Nothing>()
}