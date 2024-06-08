package dev.sxxxi.mediastore.di

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import dev.sxxxi.mediastore.data.properties.AmqpProps
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@Configuration
class MediaStoreConfig {

    @Autowired
    lateinit var props: AmqpProps

    @Bean
    @Lazy
    fun s3Client(): S3Client {
        return S3Client.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .region(Region.US_EAST_1)
            .forcePathStyle(true)
            .build()
    }

    @Bean
    @Lazy
    fun connectionProvider(): () -> Connection {
        return {
            ConnectionFactory().apply {
                host = props.host
                port = props.port
                username = props.username
                password = props.password
                virtualHost = props.vHost
            }.newConnection()
        }
    }
}