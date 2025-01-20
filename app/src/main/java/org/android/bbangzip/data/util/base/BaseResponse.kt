package org.android.bbangzip.data.util.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("code")
    val code: String? = null,
    @SerialName("data")
    val data: T? = null,
    @SerialName("message")
    val message: String? = null
)