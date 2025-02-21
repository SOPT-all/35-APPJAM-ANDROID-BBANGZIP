package org.android.bbangzip.presentation.ui.onboarding.onboardingstart

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingStartViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<OnboardingStartContract.OnboardingStartEvent, OnboardingStartContract.OnboardingStartState, OnboardingStartContract.OnboardingStartReduce, OnboardingStartContract.OnboardingStartSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): OnboardingStartContract.OnboardingStartState {
            return savedState as? OnboardingStartContract.OnboardingStartState
                ?: OnboardingStartContract.OnboardingStartState()
        }

        override fun handleEvent(event: OnboardingStartContract.OnboardingStartEvent) {
            when (event) {
                is OnboardingStartContract.OnboardingStartEvent.OnNextBtnClick -> setSideEffect(OnboardingStartContract.OnboardingStartSideEffect.NavigateToOnboarding)
            }
        }

        override fun reduceState(
            state: OnboardingStartContract.OnboardingStartState,
            reduce: OnboardingStartContract.OnboardingStartReduce,
        ): OnboardingStartContract.OnboardingStartState {
            return when (reduce) {
                is OnboardingStartContract.OnboardingStartReduce.UpdateState -> reduce.state
            }
        }
    }
