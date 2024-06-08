package dev.sxxxi.mediastore.data.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("storage")
data class MediaStoreServiceProps(
    val bucket: String,
    val bucketRoot: String,
    val urlValidityMinutes: Long
)