package org.android.bbangzip.presentation.ui.onboarding.onboardingstart

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.util.base.BaseContract

class OnboardingStartContract {
    @Parcelize
    data class OnboardingStartState(
        val state: Boolean = true
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface OnboardingStartEvent : BaseContract.Event {
        data object OnNextBtnClick : OnboardingStartEvent
    }

    sealed interface OnboardingStartReduce : BaseContract.Reduce {
        data class UpdateState(val state: OnboardingStartState) : OnboardingStartReduce
    }

    sealed interface OnboardingStartSideEffect : BaseContract.SideEffect {
        data object NavigateToOnboarding : OnboardingStartSideEffect
    }
}
