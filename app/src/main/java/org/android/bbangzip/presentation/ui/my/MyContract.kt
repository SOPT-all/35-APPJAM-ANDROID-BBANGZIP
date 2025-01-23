package org.android.bbangzip.presentation.ui.my

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.MyBbangZip
import org.android.bbangzip.presentation.util.base.BaseContract

class MyContract {
    @Parcelize
    data class MyState(
        val loading: Boolean = false,
        val myBbangZip: MyBbangZip? = MyBbangZip(),
        val currentBadge: Int = 0,
        val logoutBottomSheetState: Boolean = false,
        val withdrawBottomSheetState: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface MyEvent : BaseContract.Event {
        data object Initialize : MyEvent

        data object OnClickBbangZip : MyEvent

        data object OnClickLogoutBtn : MyEvent

        data object OnClickWithdrawBtn : MyEvent

        data object OnClickLogoutConfirmBtn : MyEvent

        data object OnClickLogoutCancelBtn : MyEvent

        data object OnClickWithdrawConfirmBtn : MyEvent

        data object OnClickWithdrawCancelBtn : MyEvent
    }

    sealed interface MyReduce : BaseContract.Reduce {
        data class UpdateMyBbangZip(val myBbangZip: MyBbangZip) : MyReduce

        data class UpdateMyCurrentBadge(val currentBadge: Int) : MyReduce

        data object UpdateLogoutBottomSheetState : MyReduce

        data object UpdateWithdrawBottomSheetState : MyReduce
    }

    sealed interface MySideEffect : BaseContract.SideEffect {
        data object NavigateToBbangZipDetail : MySideEffect

        data object NavigateToLogin : MySideEffect
    }
}
