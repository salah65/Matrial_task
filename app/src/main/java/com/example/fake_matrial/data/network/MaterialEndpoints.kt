package com.example.fake_matrial.data.network

import com.example.fake_matrial.data.network.dto.responses.GetMaterialsResponse
import retrofit2.http.GET

interface MaterialEndpoints {
    @GET("movies")
    suspend fun getMaterials(): GetMaterialsResponse
}