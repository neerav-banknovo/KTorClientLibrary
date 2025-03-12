package network

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual class HttpClientFactory {
    actual fun create(): HttpClient = HttpClient(Darwin) {
        // Configure client
    }
} 