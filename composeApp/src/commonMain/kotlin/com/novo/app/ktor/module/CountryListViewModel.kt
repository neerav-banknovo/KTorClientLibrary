package com.novo.app.ktor.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import network.HttpClientFactory
import network.KtorApiClient
import network.NetworkResponse
import network.models.CountryResponse
import network.models.Me

class CountryListViewModel : ViewModel() {
    private val apiClient = KtorApiClient(
        baseUrl = "https://novo-api.novo-aws-dev.com/api?", httpClient = HttpClientFactory().create()
    )

    private fun requestLoadCountries() {
        val query = """
            query {
                me{first_name}
            }
        """.trimIndent()

        viewModelScope.launch {
            when (val response =
                apiClient.sendGraphQLRequest<NetworkResponse<String>>(query = query)) {
                is NetworkResponse.Success -> {

                }

                is NetworkResponse.Error -> {
                    println("Error: ${response.message}")
                }
            }
        }

    }
}
