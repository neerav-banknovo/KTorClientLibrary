package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import network.graphql.GraphQLRequest
import network.graphql.GraphQLResponse

class KtorApiClient(
    val baseUrl: String,
    private val httpClient: HttpClient,
    private val headers: Map<String, String> = emptyMap()
) {
    var currentJob: Job? = null

    val client = httpClient.config {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object: Logger {
                override fun log(message: String) {
                    print(message)
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }


        headers.forEach { (key, value) ->
            headers {
                append(key, value)
            }
        }
    }
    suspend inline fun <reified T> sendGraphQLRequest_(
        query: String,
        variables: Map<String, Any>? = null,
        operationName: String? = null,
        additionalHeaders: Map<String, String> = emptyMap()
    ): String = withContext(Dispatchers.Default) {
        try {
            currentJob = coroutineContext[Job]
            val request = GraphQLRequest(
                query = query,
                variables = variables?.let { Json.encodeToJsonElement(it).jsonObject },
                operationName = operationName
            )
            val response = this@KtorApiClient.client.post(this@KtorApiClient.baseUrl) {
                contentType(ContentType.Application.Json)
                additionalHeaders.forEach { (key, value) ->
                    headers.append(key, value)
                }
                setBody(request)
            }.body<String>()
            return@withContext response

        } catch (e: Exception) {
         return@withContext "Error: ${e.message}"
        }
    }
    suspend inline fun <reified T> sendGraphQLRequest(
        query: String,
        variables: Map<String, Any>? = null,
        operationName: String? = null,
        additionalHeaders: Map<String, String> = emptyMap()
    ): NetworkResponse<T> = withContext(Dispatchers.Default) {
        try {
            currentJob = coroutineContext[Job]
            val request = GraphQLRequest(
                query = query,
                variables = variables?.let { Json.encodeToJsonElement(it).jsonObject },
                operationName = operationName
            )
            val response = this@KtorApiClient.client.post(this@KtorApiClient.baseUrl) {
                contentType(ContentType.Application.Json)
                additionalHeaders.forEach { (key, value) ->
                    headers.append(key, value)
                }
                setBody(request)
            }.body<GraphQLResponse<T>>()

            response.data?.let {
                NetworkResponse.Success(it)
            } ?: NetworkResponse.Error(message = response.errors?.firstOrNull()?.message)

        } catch (e: Exception) {
            NetworkResponse.Error(message = e.message, exception = e)
        }
    }
    fun cancelCurrentRequest() {
        currentJob?.cancel()
    }
}