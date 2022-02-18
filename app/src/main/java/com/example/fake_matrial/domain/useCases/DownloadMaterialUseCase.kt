package com.example.fake_matrial.domain.useCases

import com.example.fake_matrial.data.network.ResponseWrapper
import com.example.fake_matrial.data.network.dto.responses.MaterialsResponseItem
import com.example.fake_matrial.domain.repository.MaterialRepository
import javax.inject.Inject

class DownloadMaterialUseCase @Inject constructor(
    private val materialRepository: MaterialRepository
) {
    suspend fun invoke():ResponseWrapper<List<MaterialsResponseItem?>> {
     return materialRepository.getAllMaterial()
    }
}