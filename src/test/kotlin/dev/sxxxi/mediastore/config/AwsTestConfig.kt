package dev.sxxxi.mediastore.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@TestConfiguration
class AwsTestConfig {
    @Bean
    fun s3Client(): S3Client {
        return S3Client.builder()
            .region(Region.US_EAST_1)
            .forcePathStyle(true)
            .build()
    }
}