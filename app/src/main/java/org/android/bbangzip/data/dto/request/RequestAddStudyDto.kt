package org.android.bbangzip.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestAddStudyDto(
    @SerialName("subjectId")
    val subjectId: Long,
    @SerialName("examName")
    val examName: String,
    @SerialName("examDate")
    val examDate: String,
    @SerialName("studyContents")
    val studyContents: String,
    @SerialName("pieceList")
    val pieceList: List<Piece>
) {
    @Serializable
    data class Piece(
        @SerialName("startPage")
        val startPage: Int,
        @SerialName("finishPage")
        val finishPage: Int,
        @SerialName("deadline")
        val deadline: String
    )
}
