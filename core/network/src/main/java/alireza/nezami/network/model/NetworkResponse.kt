package alireza.nezami.network.model

import kotlinx.serialization.Serializable

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)