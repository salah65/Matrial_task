package com.example.fake_matrial.data.network.dto.responses

import com.google.gson.annotations.SerializedName

data class GetMaterialsResponse(

	@field:SerializedName("GetMaterialsResponse")
	val materialsResponse: List<MaterialsResponseItem?>
)

data class MaterialsResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
