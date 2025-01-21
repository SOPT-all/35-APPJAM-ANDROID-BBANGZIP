package org.android.bbangzip.presentation.ui.login

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.data.service.KakaoAuthService
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import org.android.bbangzip.domain.repository.remote.UserRepository
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val kakaoAuthService: KakaoAuthService,
    private val userLocalRepository: UserLocalRepository,
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<LoginContract.LoginEvent, LoginContract.LoginState, LoginContract.LoginReduce, LoginContract.LoginSideEffect>(
    savedStateHandle = savedStateHandle,
) {
    val userPreferencesFlow: Flow<UserPreferences> = userLocalRepository.userPreferenceFlow

    override fun createInitialState(savedState: Parcelable?): LoginContract.LoginState {
        return savedState as? LoginContract.LoginState ?: LoginContract.LoginState()
    }

    override fun handleEvent(event: LoginContract.LoginEvent) {
        when (event) {
            is LoginContract.LoginEvent.OnClickKakaoLoginBtn -> {
                if (currentUiState.isOnboardingCompleted) {
                    setSideEffect(LoginContract.LoginSideEffect.NavigateToSubject)
                } else {
                    Timber.d("[카카오 로그인] -> 버튼 누름")
                    kakaoAuthService.loginKakao(
                        context = event.context,
                        loginListener = { accessToken ->
                            Timber.d("[카카오 로그인] -> 카카오에서 받은 액세스 토큰 $accessToken")
                            login(accessToken)
                        },
                    )
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
            is LoginContract.LoginReduce.UpdateLoginSuccess -> state.copy(loginState = !currentUiState.loginState)
        }
    }

    private fun login(accessToken: String) {
        viewModelScope.launch {
            userRepository.login(accessToken)
                .onSuccess { userEntity ->
                    updateState(
                        LoginContract.LoginReduce.UpdateState(
                            currentUiState.copy(
                                loginState = true,
                                isOnboardingCompleted = userEntity.isOnboardingComplete
                            )
                        )
                    )
                    saveUserInfoInLocal(
                        accessToken = BEARER + userEntity.accessToken,
                        refreshToken = BEARER + userEntity.refreshToken,
                        isLogin = true
                    )
                    if (userEntity.isOnboardingComplete) {
                        setSideEffect(LoginContract.LoginSideEffect.NavigateToSubject)
                    } else {
                        setSideEffect(LoginContract.LoginSideEffect.NavigateToOnboarding)
                    }
                }.onFailure {
                    updateState(LoginContract.LoginReduce.UpdateLoginSuccess(success = false))
                    Timber.d("[카카오 로그인] -> 서버에서 LoginViewModel login 실패")
                }
        }
    }

    private suspend fun saveUserInfoInLocal(
        accessToken: String,
        refreshToken: String,
        isLogin: Boolean
    ) {
        with(userLocalRepository) {
            setAccessToken(accessToken = accessToken)
            setRefreshToken(refreshToken = refreshToken)
            setIsLogin(isLogin = isLogin)
        }
    }

    companion object {
        const val BEARER = "Bearer "
    }
}
