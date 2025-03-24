package org.android.bbangzip.presentation.ui.onboarding.onboardingend

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.util.base.BaseContract

class OnboardingEndContract {
    @Parcelize
    data class OnboardingEndState(
        val state: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface OnboardingEndEvent : BaseContract.Event {
        data object OnBackBtnClick : OnboardingEndEvent

        data object OnCompleteBtnClick : OnboardingEndEvent
    }

    sealed interface OnboardingEndReduce : BaseContract.Reduce {
        data class UpdateState(val state: OnboardingEndState) : OnboardingEndReduce
    }

    sealed interface OnboardingEndSideEffect : BaseContract.SideEffect {
        data object NavigateToBack : OnboardingEndSideEffect

        data object NavigateToSubject : OnboardingEndSideEffect
    }
}
