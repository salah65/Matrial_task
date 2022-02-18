package com.example.fake_matrial.domain.model

data class Material(
    val id: Int? = null,
    val type: MaterialType? = null,
    val name: String? = null,
    val url: String? = null,
    var DownloadStatus: DownloadStatus = com.example.fake_matrial.domain.model.DownloadStatus.NotDownloaded,
)

enum class MaterialType {
    VIDEO, PDF
}

sealed class DownloadStatus {
    data class Downloading(var progress: Int) : DownloadStatus()
    object Downloaded : DownloadStatus()
    object NotDownloaded : DownloadStatus()
}