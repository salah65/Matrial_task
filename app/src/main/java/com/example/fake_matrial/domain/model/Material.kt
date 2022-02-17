package com.example.fake_matrial.domain.model

data class Material(
    val id: Int? = null,
    val type: MaterialType? = null,
    val name: String? = null,
    val url: String? = null,
)

enum class MaterialType {
    VIDEO, PDF
}