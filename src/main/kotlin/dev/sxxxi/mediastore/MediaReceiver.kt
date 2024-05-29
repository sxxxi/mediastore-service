package dev.sxxxi.mediastore

import com.rabbitmq.client.AMQP
import dev.sxxxi.mediastore.data.Media
import dev.sxxxi.mediastore.data.Services
import dev.sxxxi.mediastore.service.MediaStoreService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.Connection
import org.springframework.stereotype.Component

/*
Listen to a channel
send response to a queue name in delivery.properties.replyTo
 */
@Component
class MediaReceiver(
    connection: Connection,
    private val mediaService: MediaStoreService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    init {
        val channel = connection.createChannel(false)

        channel.queueDeclare(QUEUE_NAME, false, false, false, null)
        channel.basicConsume(QUEUE_NAME, true, { _, delivery ->
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
    }

    companion object {
        const val QUEUE_NAME = "q_media"
    }
}