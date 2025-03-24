package org.android.bbangzip.presentation.ui.onboarding.onboardingend

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.domain.model.OnboardingEntity
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import org.android.bbangzip.domain.usecase.PostOnboardingUseCase
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingEndViewModel
    @Inject
    constructor(
        private val userLocalRepository: UserLocalRepository,
        private val postOnboardingUseCase: PostOnboardingUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<OnboardingEndContract.OnboardingEndEvent, OnboardingEndContract.OnboardingEndState, OnboardingEndContract.OnboardingEndReduce, OnboardingEndContract.OnboardingEndSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        private val userPreferencesFlow: Flow<UserPreferences> = userLocalRepository.userPreferenceFlow

        override fun createInitialState(savedState: Parcelable?): OnboardingEndContract.OnboardingEndState {
            return savedState as? OnboardingEndContract.OnboardingEndState
                ?: OnboardingEndContract.OnboardingEndState()
        }

        override fun handleEvent(event: OnboardingEndContract.OnboardingEndEvent) {
            when (event) {
                is OnboardingEndContract.OnboardingEndEvent.OnBackBtnClick -> setSideEffect(OnboardingEndContract.OnboardingEndSideEffect.NavigateToBack)
                is OnboardingEndContract.OnboardingEndEvent.OnCompleteBtnClick -> {
                    val onboardingInfo = runBlocking { userPreferencesFlow.map { it.onboardingInfo }.firstOrNull() }
                    Timber.d("[온보딩] -> $onboardingInfo")
                    postOnboardingInfo(onboardingInfo = onboardingInfo)
                }
            }
        }

        override fun reduceState(
            state: OnboardingEndContract.OnboardingEndState,
            reduce: OnboardingEndContract.OnboardingEndReduce,
        ): OnboardingEndContract.OnboardingEndState {
            return when (reduce) {
                is OnboardingEndContract.OnboardingEndReduce.UpdateState -> reduce.state
            }
        }

        private fun postOnboardingInfo(onboardingInfo: org.android.bbangzip.OnboardingInfo?) {
            viewModelScope.launch {
                Timber.d("[온보딩] 온보딩 POST -> ${onboardingInfo?.userName}, ${onboardingInfo?.year}, ${onboardingInfo?.semester}, ${onboardingInfo?.subjectName} ")
                postOnboardingUseCase(
                    OnboardingEntity(
                        nickname = onboardingInfo?.userName ?: "",
                        year = onboardingInfo?.year ?: 0,
                        semester = onboardingInfo?.semester ?: "",
                        subjectName = onboardingInfo?.subjectName ?: "",
                    ),
                ).onSuccess {
                    saveIsOnboardingCompleted(isOnboardingCompleted = true)
                    setSideEffect(OnboardingEndContract.OnboardingEndSideEffect.NavigateToSubject)
                }.onFailure {
                    Timber.d("[온보딩] 서버통신 실패 -> $error")
                }
            }
        }

        private fun saveIsOnboardingCompleted(isOnboardingCompleted: Boolean) {
            viewModelScope.launch {
                userLocalRepository.setIsOnboardingCompleted(isOnboardingCompleted = isOnboardingCompleted)
            }
        }
    }
