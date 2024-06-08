package dev.sxxxi.mediastore.service

import dev.sxxxi.mediastore.data.Services
import dev.sxxxi.mediastore.data.dto.Media

interface MediaStoreService {
    fun store(serviceName: Services, media: Media): String
    fun get(key: String): String
    fun delete(key: String)
}