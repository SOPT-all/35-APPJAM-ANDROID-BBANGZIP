package org.android.bbangzip.presentation.util.base

import android.os.Parcelable

sealed interface BaseContract {
    interface State : BaseContract {
        fun toParcelable(): Parcelable? = null
    }

    interface Event : BaseContract

    interface Reduce : BaseContract

    interface SideEffect : BaseContract
}
