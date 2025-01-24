package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class AddStudyData(
    val subjectId: Int,
    val subjectName: String,
    val pieceNumber: Int,
    val examDate: String,
    val examName: String,
    val studyContent: String,
    val startPage: String,
    val endPage: String,
    val startPageList: List<String>,
    val endPageList: List<String>,
) : Parcelable
