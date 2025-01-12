package org.android.bbangzip.presentation.type

sealed class BbangZipCardType{
    data object TODO : BbangZipCardType()
    data object SUBJECT : BbangZipCardType()
}