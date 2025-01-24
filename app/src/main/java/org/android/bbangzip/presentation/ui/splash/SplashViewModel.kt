package org.android.bbangzip.presentation.ui.splash

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        userLocalRepository: UserLocalRepository,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<SplashContract.SplashEvent, SplashContract.SplashState, SplashContract.SplashReduce, SplashContract.SplashSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        private val userPreferencesFlow: Flow<UserPreferences> = userLocalRepository.userPreferenceFlow

        override fun createInitialState(savedState: Parcelable?): SplashContract.SplashState {
            return savedState as? SplashContract.SplashState ?: SplashContract.SplashState()
        }

        init {
            setEvent(SplashContract.SplashEvent.Initialize)
        }

        override fun handleEvent(event: SplashContract.SplashEvent) {
            when (event) {
                is SplashContract.SplashEvent.Initialize -> launch { initScreen() }
            }
        }

        override fun reduceState(
            state: SplashContract.SplashState,
            reduce: SplashContract.SplashReduce,
        ): SplashContract.SplashState {
            return when (reduce) {
                is SplashContract.SplashReduce.UpdateState -> reduce.state
            }
        }

        private fun initScreen() {
            viewModelScope.launch {
                val isLogin = getInitialIsLoginPreferences()
                val isOnboardingCompleted = getInitialInOnboardingPreferences()

                delay(20L)

                if (isLogin) {
                    if (isOnboardingCompleted) {
                        setSideEffect(SplashContract.SplashSideEffect.NavigateToSubject)
                    } else {
                        setSideEffect(SplashContract.SplashSideEffect.NavigateToOnboardingStart)
                    }
                } else {
                    setSideEffect(SplashContract.SplashSideEffect.NavigateToLogin)
                }
            }
        }

        private suspend fun getInitialIsLoginPreferences() = userPreferencesFlow.first().isLogin

        private suspend fun getInitialInOnboardingPreferences() = userPreferencesFlow.first().isOnboardingCompleted
    }
