package com.example.fake_matrial.data.mapper

import com.example.fake_matrial.data.network.dto.responses.MaterialsResponseItem
import com.example.fake_matrial.domain.model.Material
import com.example.fake_matrial.domain.model.MaterialType

fun List<MaterialsResponseItem>.mapToListOfMaterial(): List<Material> {
    return this.map {
        Material(
            name = it.name,
            type = if (it.type.equals("VIDEO")) MaterialType.VIDEO else MaterialType.PDF,
            id = it.id,
            url = it.url
        )
    }
}