package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dummy(
    val dummyA: String? = null,
    val dummyB: String? = null
) : Parcelable
