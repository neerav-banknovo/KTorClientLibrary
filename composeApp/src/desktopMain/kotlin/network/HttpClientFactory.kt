package network

import io.ktor.client.*
import io.ktor.client.engine.java.*

actual class HttpClientFactory {
    actual fun create(): HttpClient = HttpClient(Java) {
        // Configure client
    }
} 