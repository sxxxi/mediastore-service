package dev.sxxxi.mediastore

import dev.sxxxi.mediastore.data.properties.AmqpProps
import dev.sxxxi.mediastore.data.properties.MediaStoreServiceProps
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AmqpProps::class, MediaStoreServiceProps::class)
class MediaStoreServiceApplication

fun main(args: Array<String>) {
	runApplication<MediaStoreServiceApplication>(*args)
}
