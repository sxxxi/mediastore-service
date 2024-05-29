package dev.sxxxi.mediastore.service

import dev.sxxxi.mediastore.data.Media
import dev.sxxxi.mediastore.data.Services

interface MediaStoreService {
    fun store(serviceName: Services, media: Media): String
    fun get(key: String): String
    fun delete(key: String)
}