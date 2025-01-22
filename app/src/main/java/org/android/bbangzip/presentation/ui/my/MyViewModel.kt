package org.android.bbangzip.presentation.ui.my

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.data.service.KakaoAuthService
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import org.android.bbangzip.domain.usecase.DeleteLogoutUseCase
import org.android.bbangzip.domain.usecase.DeleteWithdrawUseCase
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyViewModel
@Inject
constructor(
    private val userLocalRepository: UserLocalRepository,
    private val kakaoAuthService: KakaoAuthService,
    private val deleteLogoutUseCase: DeleteLogoutUseCase,
    private val deleteWithdrawUseCase: DeleteWithdrawUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<MyContract.MyEvent, MyContract.MyState, MyContract.MyReduce, MyContract.MySideEffect>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedState: Parcelable?): MyContract.MyState {
        return savedState as? MyContract.MyState ?: MyContract.MyState()
    }

    override fun handleEvent(event: MyContract.MyEvent) {
        when (event) {
            is MyContract.MyEvent.OnClickLogoutBtn -> {
                kakaoAuthService.logoutKakao(
                    logoutListener = { logout() },
                )
            }

            is MyContract.MyEvent.OnClickWithdrawBtn -> {
                withdraw()
            }
        }
    }

    override fun reduceState(
        state: MyContract.MyState,
        reduce: MyContract.MyReduce,
    ): MyContract.MyState {
        TODO("Not yet implemented")
    }

    private fun logout() {
        viewModelScope.launch {
            deleteLogoutUseCase().onSuccess {
                clearDataStore()
                Timber.d("[마이페이지] 서버 -> 로그아웃 성공 $error")
                setSideEffect(MyContract.MySideEffect.NavigateToLogin)
            }.onFailure {
                Timber.d("[마이페이지] 서버 -> 로그아웃 실패 $error")
            }
        }
    }

    private fun withdraw() {
        viewModelScope.launch {
            deleteWithdrawUseCase().onSuccess {
                kakaoAuthService.withdrawKakao()
                clearDataStore()
                Timber.d("[마이페이지] 서버 -> 회원탈퇴 성공 $error")
                setSideEffect(MyContract.MySideEffect.NavigateToLogin)
            }.onFailure {
                Timber.d("[마이페이지] 서버 -> 회원탈퇴 실패 $error")
            }
        }
    }

    private suspend fun clearDataStore() {
        with(userLocalRepository) {
            clearAccessToken()
            clearRefreshToken()
            clearOnboardingInfo()
            setIsLogin(false)
            setIsOnboardingCompleted(false)
        }
    }
}
