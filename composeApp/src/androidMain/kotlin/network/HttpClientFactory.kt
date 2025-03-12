package network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual class HttpClientFactory {
    actual fun create(): HttpClient = HttpClient(OkHttp) {
        // Configure client
    }
} 