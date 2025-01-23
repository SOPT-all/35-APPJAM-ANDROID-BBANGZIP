package org.android.bbangzip.presentation.ui.splash

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.util.base.BaseContract

class SplashContract {
    @Parcelize
    data class SplashState(
        val loading: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SplashEvent : BaseContract.Event {
        data object Initialize : SplashEvent
    }

    sealed interface SplashReduce : BaseContract.Reduce {
        data class UpdateState(val state: SplashState) : SplashReduce
    }

    sealed interface SplashSideEffect : BaseContract.SideEffect {
        data object NavigateToLogin : SplashSideEffect

        data object NavigateToOnboardingStart : SplashSideEffect

        data object NavigateToSubject : SplashSideEffect
    }
}
