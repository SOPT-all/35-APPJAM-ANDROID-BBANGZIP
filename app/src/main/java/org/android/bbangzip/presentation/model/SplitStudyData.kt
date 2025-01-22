package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.type.AddStudyViewType

@Serializable
@Parcelize
data class SplitStudyData(
    val subjectName: String,
    val pieceNumber: Int,
    val examDate: String,
    val studyContent: String,
    val startPage: String,
    val endPage: String,
    val startPageList: List<String>,
    val endPageList: List<String>,
    val deadLineList: List<String>,
    val addStudyViewType: AddStudyViewType,
) : Parcelable
