package com.example.fake_matrial.domain.repository

import com.example.fake_matrial.data.network.ResponseWrapper
import com.example.fake_matrial.data.network.dto.responses.MaterialsResponseItem
import com.example.fake_matrial.domain.model.Material

interface MaterialRepository {
    suspend fun getAllMaterial(): ResponseWrapper<List<MaterialsResponseItem?>>
}