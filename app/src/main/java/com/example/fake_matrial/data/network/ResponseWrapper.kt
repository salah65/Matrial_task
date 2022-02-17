package com.example.fake_matrial.data.network


sealed class ResponseWrapper {
    object Loading : ResponseWrapper()
    data class Success(val data: Any? = null) : ResponseWrapper()
    data class Error(val message: String? = null) : ResponseWrapper()
}