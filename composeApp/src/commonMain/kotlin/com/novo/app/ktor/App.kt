package com.novo.app.ktor

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import network.HttpClientFactory
import network.KtorApiClient
import org.jetbrains.compose.ui.tooling.preview.Preview
@Composable
@Preview
fun App() {
    MaterialTheme {
        Column {
            Text(text ="test")
        }
        val helper = SwiftHelper()  // Dependency manually created without KOIN
        Text(text = helper.callOther("first"," second"))
    }
}

object APICall {
    private val apiClient = KtorApiClient(
        baseUrl = "https://novo-api.novo-aws-dev.com/api?",
        httpClient = HttpClientFactory().create()
    )

    suspend fun requestLoadCountries() {
        val query = """
            query {
                me{first_name}
            }
        """.trimIndent()
        val headers = LinkedHashMap<String, String>()
        headers["Authorization"] = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJOb3ZvIiwic3ViIjoiMmM0MDUxMzA5ODg2MzBkZCIsImF1ZCI6ImFuZHJvaWQiLCJleHAiOjE3NDEzMzA4NDU2MjksImlhdCI6MTc0MTMzMDI0NTYyOSwianRpIjoiN2VjNzRhZDItZmIyMC0xMWVmLWEyZTUtNmY5MWExMTQ2M2I2Iiwic2VzIjoiN2VjNzRhZDAtZmIyMC0xMWVmLWEyZTUtNmY5MWExMTQ2M2I2IiwiaGFzaCI6ImU4N2NjZDVhNTM3NDZhMjdkYzZhODljM2NiN2FjNWU1MjhmMDY1NjdiOGU2MTVkOTE0NWRlMzM5Mjg5NzA5NWM1NWEzNGJjOWNlMWMxMzIzMzhmYjhmNWYyOTlkZGJlODI3MzVmYWRhOTdkZmRmYjE0YTdhNTc5MDExMmRiYjFhIiwicGVybWlzc2lvbnMiOnsiY2hlY2tpbmdfYWNjb3VudCI6eyI4YjMwY2Q1ZS00OGE4LTQxMjItOWYyZi05NWZiNzYzNzkyMzUiOnsiKiI6eyJhY3Rpb25zIjpbIioiXX19fSwibWNhIjp7ImQzMGFiYjk5LWU3YzktNGFhOC1hODViLWRjODI5ZjllYTNmMyI6eyIqIjp7ImFjdGlvbnMiOlsiKiJdfX19LCJjcmVkaXRfY2FyZF9hY2NvdW50Ijp7IjYyNTk4NTVkLTcyOGMtNDUwNC1iNTg4LTA5NWZhNzhkN2I2NCI6eyIqIjp7ImFjdGlvbnMiOlsiKiJdfX19fX0.owOLo7oWKSQD3lvaQOpn-Po-_e1fxMkYD8hbQsyRO2Z_alPKATYIMMfCvAoO83Upb2Rk1HLnA4yO-x8p62N6jg"
        headers["novo-uuid-token"] = "2c405130988630dd"
        headers["Content-Type"] = "application/json"
        headers["Accept"] = "application/json"
        headers["platform"] = "android"
        val response =
            apiClient.sendGraphQLRequest_<String>(query = query, additionalHeaders = headers)
        val len = response.length
        print(len.toString())
        print(response)

    }
}