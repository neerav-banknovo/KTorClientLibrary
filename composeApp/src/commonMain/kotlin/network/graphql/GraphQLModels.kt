package network.graphql

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class GraphQLRequest(
    val query: String,
    val variables: JsonObject? = null,
    val operationName: String? = null
)

@Serializable
data class GraphQLResponse<T>(
    val data: T? = null,
    val errors: List<GraphQLError>? = null
)

@Serializable
data class GraphQLError(
    val message: String,
    val locations: List<GraphQLErrorLocation>? = null,
    val path: List<String>? = null
)

@Serializable
data class GraphQLErrorLocation(
    val line: Int,
    val column: Int
) 