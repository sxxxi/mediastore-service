package dev.sxxxi.mediastore.data.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("rabbitmq.config")
data class AmqpProps (
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val vHost: String
)