package dev.sxxxi.mediastore

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Connection
import dev.sxxxi.mediastore.data.Services
import dev.sxxxi.mediastore.data.dto.Media
import dev.sxxxi.mediastore.service.MediaStoreService
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/*
Listen to a channel
send response to a queue name in delivery.properties.replyTo
 */
@Component
class MediaReceiver(
    private val mediaService: MediaStoreService,
    private val connection: () -> Connection,
    private val logger: Logger
) {
    @Value("\${rabbitmq.receivers.media.queue}")
    private lateinit var queueName: String

    @PostConstruct
    fun init() {
        try {
            logger.info("Creating MediaReceiver...")
            val channel = connection().createChannel()
            logger.info("Connection and channel created.")
            channel.queueDeclare(queueName, false, false, false, null)
            channel.basicConsume(queueName, true, { _, delivery ->
                val media = Media.from(delivery)
                val savedPath = mediaService.store(Services.PROJECTS, media)

                logger.info(delivery.properties.correlationId)

                channel.basicPublish("", delivery.properties.replyTo,
                    AMQP.BasicProperties.Builder()
                        .contentType("text/plain")
                        .correlationId(delivery.properties.correlationId)
                        .build(),
                    savedPath.toByteArray(Charsets.UTF_8))
            }) { _ -> }

        } catch (e: Exception) {
            logger.error("Hmmm + $e")
        }
    }
}