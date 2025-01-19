package org.android.bbangzip.data.util.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: T,

)
