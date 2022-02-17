package com.example.fake_matrial.data.gateways

import com.example.fake_matrial.data.network.MaterialEndpoints
import com.example.fake_matrial.data.network.ResponseWrapper
import com.example.fake_matrial.data.network.dto.responses.MaterialsResponseItem
import com.example.fake_matrial.data.network.safeApiCall
import retrofit2.Retrofit
import javax.inject.Inject


class ServerGatewayImplementer @Inject constructor(private val api: Retrofit) : ServerGateway {
    override suspend fun getMaterials(): ResponseWrapper<List<MaterialsResponseItem?>> {
        return safeApiCall{
            api.create(MaterialEndpoints::class.java).getMaterials().materialsResponse
        }
    }


}

interface ServerGateway {
    suspend fun getMaterials(): ResponseWrapper<List<MaterialsResponseItem?>>
}