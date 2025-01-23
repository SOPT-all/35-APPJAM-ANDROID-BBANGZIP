package org.android.bbangzip.presentation.model

import org.android.bbangzip.presentation.model.card.SubjectCardModel

data class SubjectInfo(
    val semester: String,
    val subjectList: List<SubjectCardModel>,
    val year: Int,
)
