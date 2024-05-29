package dev.sxxxi.mediastore.data

import com.rabbitmq.client.Delivery

data class Media (
    val contentType: String,
    val body: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Media

        if (contentType != other.contentType) return false
        if (!body.contentEquals(other.body)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = contentType.hashCode()
        result = 31 * result + body.contentHashCode()
        return result
    }

    companion object {
        fun from(delivery: Delivery): Media {
            return Media(
                contentType = delivery.properties.contentType,
                body = delivery.body
            )
        }
    }
}
