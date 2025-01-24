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
import org.android.bbangzip.domain.usecase.FetchBbangZipUseCase
import org.android.bbangzip.presentation.model.MyBbangZip
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
        private val fetchBbangZipUseCase: FetchBbangZipUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<MyContract.MyEvent, MyContract.MyState, MyContract.MyReduce, MyContract.MySideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): MyContract.MyState {
            return savedState as? MyContract.MyState ?: MyContract.MyState()
        }

        init {
            setEvent(MyContract.MyEvent.Initialize)
        }

        override fun handleEvent(event: MyContract.MyEvent) {
            when (event) {
                is MyContract.MyEvent.Initialize ->
                    launch {
                        Timber.tag("[마이페이지] -> ").d("initialize 작동")
                        initDataLoad()
                    }
                is MyContract.MyEvent.OnClickBbangZip -> setSideEffect(MyContract.MySideEffect.NavigateToBbangZipDetail)
                is MyContract.MyEvent.OnClickLogoutBtn -> {
                    updateState(MyContract.MyReduce.UpdateLogoutBottomSheetState)
                }

                is MyContract.MyEvent.OnClickWithdrawBtn -> {
                    updateState(MyContract.MyReduce.UpdateWithdrawBottomSheetState)
                }

                is MyContract.MyEvent.OnClickLogoutCancelBtn -> {
                    updateState(MyContract.MyReduce.UpdateLogoutBottomSheetState)
                }

                is MyContract.MyEvent.OnClickLogoutConfirmBtn -> {
                    kakaoAuthService.logoutKakao(
                        logoutListener = { logout() },
                    )
                }

                is MyContract.MyEvent.OnClickWithdrawCancelBtn -> {
                    updateState(MyContract.MyReduce.UpdateWithdrawBottomSheetState)
                }

                is MyContract.MyEvent.OnClickWithdrawConfirmBtn -> {
                    kakaoAuthService.withdrawKakao(
                        withdrawListener = { withdraw() },
                    )
                }

                is MyContract.MyEvent.OnClickMyBadgeCount -> {
                    setSideEffect(MyContract.MySideEffect.NavigateToBadgeDetail)
                }
            }
        }

        override fun reduceState(
            state: MyContract.MyState,
            reduce: MyContract.MyReduce,
        ): MyContract.MyState {
            return when (reduce) {
                is MyContract.MyReduce.UpdateMyBbangZip -> state.copy(myBbangZip = reduce.myBbangZip)
                is MyContract.MyReduce.UpdateMyCurrentBadge -> state.copy(currentBadge = reduce.currentBadge)
                is MyContract.MyReduce.UpdateLogoutBottomSheetState -> {
                    state.copy(logoutBottomSheetState = !currentUiState.logoutBottomSheetState)
                }

                is MyContract.MyReduce.UpdateWithdrawBottomSheetState -> {
                    state.copy(withdrawBottomSheetState = !currentUiState.withdrawBottomSheetState)
                }
            }
        }

        private suspend fun initDataLoad() {
            fetchBbangZipUseCase()
                .onSuccess { data ->
                    updateState(
                        MyContract.MyReduce.UpdateMyBbangZip(
                            myBbangZip =
                                MyBbangZip(
                                    bbangZipName = data.levelDetails[data.level - 1].levelName,
                                    bbangZipLevel = data.level,
                                    reward = data.reward,
                                    maxReward = data.maxReward,
                                    bbangZipImgUrl = data.levelDetails[data.level - 1].levelImage,
                                    badgeCount = data.badgeCounts
                                ),
                        ),
                    )
                    updateState(MyContract.MyReduce.UpdateMyCurrentBadge(currentBadge = data.badgeCounts))
                }.onFailure {
                    Timber.d("[마이페이지] fetch 실패 -> $error")
                }
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
