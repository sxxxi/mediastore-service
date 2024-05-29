package dev.sxxxi.mediastore

import dev.sxxxi.mediastore.data.dto.ImageUrlGetResponse
import dev.sxxxi.mediastore.service.MediaStoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ContentStoreController(
    private val storageService: MediaStoreService
) {
    @GetMapping(
        value = ["/mediastore"],
        produces = ["application/json"]
    )
    fun getSignedUrlToPath(
        @RequestParam(name = "path", required = true) path: String
    ): ResponseEntity<ImageUrlGetResponse> {
        return ResponseEntity.ok(ImageUrlGetResponse(storageService.get(path)))
    }
}






















