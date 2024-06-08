package dev.sxxxi.mediastore.di

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Configuration
class CoreConfig {
    @Bean
    @Lazy
    fun logger(): Logger {
        return LoggerFactory.getLogger(this::class.java)
    }
}