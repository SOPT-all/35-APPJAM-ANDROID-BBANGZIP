package org.android.bbangzip.presentation.type

sealed class StudyCardType{
    data object TODO : StudyCardType()
    data object SUBJECT : StudyCardType()
}