package network

sealed class NetworkResponse<out T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error(
        val code: Int? = null, val message: String? = null, val exception: Throwable? = null
    ) : NetworkResponse<Nothing>()
} 