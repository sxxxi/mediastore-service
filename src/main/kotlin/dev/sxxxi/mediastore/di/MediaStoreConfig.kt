package dev.sxxxi.mediastore.di

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.Connection
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client


@Configuration
class MediaStoreConfig {
    private val logger = LoggerFactory.getLogger(this::class.java)
    @Bean
    fun s3Client(): S3Client {
        return S3Client.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .forcePathStyle(true)
            .build()
    }

    // RabbitMQ Connection
    @Bean
    @Lazy
    fun rabbitConnection(factory: ConnectionFactory): Connection {
        return factory.createConnection()
    }
}