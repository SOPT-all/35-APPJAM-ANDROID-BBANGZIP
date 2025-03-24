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

        data object OnBbangZipClick : MyEvent

        data object OnMyBadgeCountClick : MyEvent

        data object OnLogoutBtnClick : MyEvent

        data object OnWithdrawBtnClick : MyEvent

        data object OnLogoutConfirmBtnClick : MyEvent

        data object OnLogoutCancelBtnClick : MyEvent

        data object OnWithdrawConfirmBtnClick : MyEvent

        data object OnWithdrawCancelBtnClick : MyEvent
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

        data object NavigateToBadgeDetail : MySideEffect
    }
}
