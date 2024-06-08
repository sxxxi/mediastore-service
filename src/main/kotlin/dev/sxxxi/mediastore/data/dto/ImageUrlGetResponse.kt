package dev.sxxxi.mediastore.data.dto

interface ImageUrlGetResponse {
    data class Success(val url: String) : ImageUrlGetResponse
    data class Error(val code: Int, val message: String): ImageUrlGetResponse
}
