package org.android.bbangzip.presentation.ui.login

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<LoginContract.LoginEvent, LoginContract.LoginState, LoginContract.LoginReduce, LoginContract.LoginSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): LoginContract.LoginState {
            return savedState as? LoginContract.LoginState ?: LoginContract.LoginState()
        }

        override fun handleEvent(event: LoginContract.LoginEvent) {
            when (event) {
                is LoginContract.LoginEvent.OnClickKakaoLoginBtn -> {
                    if (currentUiState.isOnboardingCompleted) {
                        setSideEffect(LoginContract.LoginSideEffect.NavigateToSubject)
                    } else {
                        setSideEffect(LoginContract.LoginSideEffect.NavigateToOnboarding)
                    }
                }
            }
        }

        override fun reduceState(
            state: LoginContract.LoginState,
            reduce: LoginContract.LoginReduce,
        ): LoginContract.LoginState {
            return when (reduce) {
                is LoginContract.LoginReduce.UpdateState -> reduce.state
            }
        }
    }
