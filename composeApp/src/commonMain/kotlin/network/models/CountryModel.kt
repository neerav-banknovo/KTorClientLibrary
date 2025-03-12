package network.models

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    val countries: List<Country>
)

@Serializable
data class Country(
    val code: String,
    val name: String,
    val emoji: String
)
@Serializable
data class Me(
    val first_name: String = "",
    val last_name: String = "",
)