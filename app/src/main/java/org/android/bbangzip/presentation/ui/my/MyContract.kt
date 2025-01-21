package org.android.bbangzip.presentation.ui.my

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.util.base.BaseContract

class MyContract {
    @Parcelize
    data class MyState(
        val state: Boolean = false
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface MyEvent : BaseContract.Event {
        data object OnClickLogoutBtn : MyEvent
        data object OnClickWithdrawBtn : MyEvent
    }

    sealed interface MyReduce : BaseContract.Reduce {

    }

    sealed interface MySideEffect : BaseContract.SideEffect {

    }
}
