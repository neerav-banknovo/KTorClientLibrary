package network.samples

import network.HttpClientFactory
import network.KtorApiClient
import network.NetworkResponse
import network.models.CountryResponse

class DesktopGraphQLSample {
    private val apiClient = KtorApiClient(
        baseUrl = "https://countries.trevorblades.com",
        httpClient = HttpClientFactory().create()
    )

    suspend fun fetchCountries(): NetworkResponse<CountryResponse> {
        val query = """
            query {
                countries {
                    code
                    name
                    emoji
                }
            }
        """.trimIndent()

        return apiClient.sendGraphQLRequest(query)
    }

    // Usage example in a Desktop application:
    suspend fun demonstrateUsage() {
        when (val response = fetchCountries()) {
            is NetworkResponse.Success -> {
                val countries = response.data.countries
                println("Desktop - Fetched ${countries.size} countries")
                countries.take(5).forEach { country ->
                    println("${country.emoji} ${country.name} (${country.code})")
                }
            }
            is NetworkResponse.Error -> {
                println("Desktop - Error: ${response.message}")
            }
        }
    }
} 