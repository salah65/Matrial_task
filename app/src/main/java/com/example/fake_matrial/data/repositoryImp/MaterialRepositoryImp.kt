package com.example.fake_matrial.data.repositoryImp

import com.example.fake_matrial.data.gateways.ServerGateway
import com.example.fake_matrial.data.network.ResponseWrapper
import com.example.fake_matrial.data.network.dto.responses.MaterialsResponseItem
import com.example.fake_matrial.domain.model.Material
import com.example.fake_matrial.domain.repository.MaterialRepository
import javax.inject.Inject

class MaterialRepositoryImp @Inject constructor(
    private val serverGateway: ServerGateway
) : MaterialRepository {
    override suspend fun getAllMaterial(): ResponseWrapper<List<MaterialsResponseItem?>> {
        return serverGateway.getMaterials()
    }
}