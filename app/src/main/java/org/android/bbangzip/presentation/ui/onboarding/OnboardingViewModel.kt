package org.android.bbangzip.presentation.ui.onboarding

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<OnboardingContract.OnboardingEvent, OnboardingContract.OnboardingState, OnboardingContract.OnboardingReduce, OnboardingContract.OnboardingSideEffect>(
    savedStateHandle = savedStateHandle
) {
    override fun createInitialState(savedState: Parcelable?): OnboardingContract.OnboardingState {
        return savedState as? OnboardingContract.OnboardingState ?: OnboardingContract.OnboardingState()
    }

    override fun handleEvent(event: OnboardingContract.OnboardingEvent) {
        when (event) {
            is OnboardingContract.OnboardingEvent.OnChangeUserName -> {
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateUserName(
                        userName = event.userName
                    )
                )
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateUserNameTextFieldSate(
                        userName = event.userName
                    )
                )
            }

            is OnboardingContract.OnboardingEvent.OnChangeUserNameFocused -> {
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateUserNameFocused(
                        isFocused = event.isFocused
                    )
                )
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateUserNameTextFieldSate(
                        userName = currentUiState.userName ?: ""
                    )
                )
            }

            is OnboardingContract.OnboardingEvent.OnChangeSemester -> {
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateSemester(
                        semester = event.semester
                    )
                )
            }

            is OnboardingContract.OnboardingEvent.OnChangeSubject -> {
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateSubject(
                        subject = event.subject
                    )
                )
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateSubjectNameTextFieldSate(
                        subject = event.subject
                    )
                )
            }

            is OnboardingContract.OnboardingEvent.OnChangeSubjectFocused -> {
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateSubjectFocused(
                        isFocused = event.isFocused
                    )
                )
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateSubjectNameTextFieldSate(
                        subject = currentUiState.subjectName ?: ""
                    )
                )
            }

            is OnboardingContract.OnboardingEvent.OnChangeCurrentPage -> {
                updateState(
                    OnboardingContract.OnboardingReduce.UpdateCurrentPage(
                        nextPage = event.currentPage
                    )
                )
            }

            is OnboardingContract.OnboardingEvent.OnClickDeleteUserName -> {
                updateState(OnboardingContract.OnboardingReduce.DeleteUserName)
            }

            is OnboardingContract.OnboardingEvent.OnClickDeleteSubject -> {
                updateState(OnboardingContract.OnboardingReduce.DeleteSubject)
            }

            is OnboardingContract.OnboardingEvent.OnClickBackBtn -> {
                val previousPage = currentUiState.currentPage - 1
                when (currentUiState.currentPage) {
                    1 -> {
                        updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = previousPage))
                        setSideEffect(OnboardingContract.OnboardingSideEffect.NavigateToOnboardingStart)
                    }

                    2 -> updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = previousPage))
                    3 -> updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = previousPage))
                    4 -> {
                        updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = previousPage))
                        setSideEffect(OnboardingContract.OnboardingSideEffect.NavigateToOnboarding)
                    }
                }
            }

            is OnboardingContract.OnboardingEvent.OnClickNextBtn -> {
                val nextPage = currentUiState.currentPage + 1
                Timber.d("[온보딩] 현재 페이지 -> ${currentUiState.currentPage}")
                Timber.d("[온보딩] 다음 페이지 -> $nextPage")
                when (currentUiState.currentPage) {
                    0 -> {
                        updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = nextPage))
                        setSideEffect(OnboardingContract.OnboardingSideEffect.NavigateToOnboarding)
                        Timber.d("[온보딩] 현재 페이지 -> ${currentUiState.currentPage}")
                    }

                    1 -> updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = nextPage))
                    2 -> {
                        updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = nextPage))
                        setSideEffect(OnboardingContract.OnboardingSideEffect.NavigateToOnboardingEnd)
                    }
                }
            }

            is OnboardingContract.OnboardingEvent.OnClickFinishBtn ->
                setSideEffect(OnboardingContract.OnboardingSideEffect.NavigateToSubject)
        }
    }

    override fun reduceState(state: OnboardingContract.OnboardingState, reduce: OnboardingContract.OnboardingReduce): OnboardingContract.OnboardingState {
        return when (reduce) {
            is OnboardingContract.OnboardingReduce.UpdateState -> reduce.state
            is OnboardingContract.OnboardingReduce.UpdateUserName -> state.copy(userName = reduce.userName)
            is OnboardingContract.OnboardingReduce.UpdateSemester -> state.copy(semester = reduce.semester)
            is OnboardingContract.OnboardingReduce.UpdateSubject -> state.copy(subjectName = reduce.subject)
            is OnboardingContract.OnboardingReduce.DeleteUserName -> state.copy(userName = null)
            is OnboardingContract.OnboardingReduce.DeleteSubject -> state.copy(subjectName = null)
            is OnboardingContract.OnboardingReduce.UpdateUserNameFocused -> state.copy(userNameFocusedState = reduce.isFocused)
            is OnboardingContract.OnboardingReduce.UpdateSubjectFocused -> state.copy(subjectNameFocusedState = reduce.isFocused)
            is OnboardingContract.OnboardingReduce.UpdateCurrentPage -> state.copy(currentPage = reduce.nextPage)
            is OnboardingContract.OnboardingReduce.UpdateUserNameTextFieldSate -> {
                val checkedText = determineTextFieldType(reduce.userName)
                Timber.d("[온보딩] 텍스트 필드 -> ${reduce.userName} -> $checkedText")
                state.copy(userNameTextFieldState = checkedText)
            }

            is OnboardingContract.OnboardingReduce.UpdateSubjectNameTextFieldSate -> {
                val checkedText = determineTextFieldType(reduce.subject)
                Timber.d("[온보딩] 텍스트 필드 -> ${reduce.subject} -> $checkedText")
                state.copy(subjectNameTextFieldState = checkedText)
            }
        }
    }

    private fun determineTextFieldType(
        text: String,
        isFocused: Boolean = true,
        isCompleted: Boolean = false
    ): BbangZipTextFieldInputState {
        return when {
            text.isEmpty() && !isFocused -> BbangZipTextFieldInputState.Default
            text.isEmpty() && isFocused -> BbangZipTextFieldInputState.Placeholder
            text.contains(Regex("[0-9!@#\$%^&*(),.?\":{}|<>]")) -> BbangZipTextFieldInputState.Alert
            text.isNotEmpty() && isFocused -> BbangZipTextFieldInputState.Typing
            isCompleted -> BbangZipTextFieldInputState.Field
            else -> BbangZipTextFieldInputState.Default
        }
    }
}