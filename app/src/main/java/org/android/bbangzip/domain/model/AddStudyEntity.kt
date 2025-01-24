package org.android.bbangzip.domain.model

import org.android.bbangzip.data.dto.request.RequestAddStudyDto

data class AddStudyEntity(
    val subjectId: Long,
    val examName: String,
    val examDate: String,
    val studyContents: String,
    val pieceList: List<Piece>,
) {
    data class Piece(
        val startPage: Int,
        val finishPage: Int,
        val deadline: String,
    ) {
        fun toPieceDto() =
            RequestAddStudyDto.Piece(
                startPage = startPage,
                finishPage = finishPage,
                deadline = deadline,
            )
    }

    fun toAddStudyDto() =
        RequestAddStudyDto(
            subjectId = subjectId,
            examName = examName,
            examDate = examDate,
            studyContents = studyContents,
            pieceList = pieceList.map { it.toPieceDto() },
        )
}
