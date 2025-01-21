package org.android.bbangzip.presentation.ui.onboarding

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.domain.repository.local.UserRepository
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.type.SemesterType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<OnboardingContract.OnboardingEvent, OnboardingContract.OnboardingState, OnboardingContract.OnboardingReduce, OnboardingContract.OnboardingSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        private fun setUserOnboardingInfo(
            userName: String,
            year: Int,
            semester: String,
            subject: String,
        ) {
            viewModelScope.launch { userRepository.setOnboardingInfo(userName, year, subject, semester) }
        }

        override fun createInitialState(savedState: Parcelable?): OnboardingContract.OnboardingState {
            return savedState as? OnboardingContract.OnboardingState ?: OnboardingContract.OnboardingState()
        }

        override fun handleEvent(event: OnboardingContract.OnboardingEvent) {
            when (event) {
                is OnboardingContract.OnboardingEvent.OnChangeUserName -> {
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateUserName(
                            userName = event.userName,
                        ),
                    )
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateUserNameTextFieldSate(
                            userName = event.userName,
                        ),
                    )
                    updateState(OnboardingContract.OnboardingReduce.UpdateButtonEnabled)
                }

                is OnboardingContract.OnboardingEvent.OnChangeUserNameFocused -> {
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateUserNameFocused(
                            isFocused = event.isFocused,
                        ),
                    )
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateUserNameTextFieldSate(
                            userName = currentUiState.userName ?: "",
                        ),
                    )
                }

                is OnboardingContract.OnboardingEvent.OnChangeSemester -> {
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateSemester(
                            semester = event.semester,
                        ),
                    )
                    updateState(OnboardingContract.OnboardingReduce.UpdateButtonEnabled)
                }

                is OnboardingContract.OnboardingEvent.OnChangeSubject -> {
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateSubject(
                            subject = event.subject,
                        ),
                    )
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateSubjectNameTextFieldSate(
                            subject = event.subject,
                        ),
                    )
                    updateState(OnboardingContract.OnboardingReduce.UpdateButtonEnabled)
                }

                is OnboardingContract.OnboardingEvent.OnChangeSubjectFocused -> {
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateSubjectFocused(
                            isFocused = event.isFocused,
                        ),
                    )
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateSubjectNameTextFieldSate(
                            subject = currentUiState.subjectName ?: "",
                        ),
                    )
                }

                is OnboardingContract.OnboardingEvent.OnChangeCurrentPage -> {
                    updateState(
                        OnboardingContract.OnboardingReduce.UpdateCurrentPage(
                            nextPage = event.currentPage,
                        ),
                    )
                    updateState(OnboardingContract.OnboardingReduce.UpdateButtonEnabled)
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
                    when (currentUiState.currentPage) {
                        0 -> {
                            updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = nextPage))
                            setSideEffect(OnboardingContract.OnboardingSideEffect.NavigateToOnboarding)
                        }

                        1 -> updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = nextPage))
                        2 -> {
                            updateState(OnboardingContract.OnboardingReduce.UpdateCurrentPage(nextPage = nextPage))
                            setUserOnboardingInfo(currentUiState.userName ?: "", currentUiState.semester.year.toInt(), currentUiState.semester.semester.text, currentUiState.subjectName ?: "")
                            setSideEffect(OnboardingContract.OnboardingSideEffect.NavigateToOnboardingEnd)
                        }
                    }
                }

                is OnboardingContract.OnboardingEvent.OnClickFinishBtn ->
                    setSideEffect(OnboardingContract.OnboardingSideEffect.NavigateToSubject)
            }
        }

        override fun reduceState(
            state: OnboardingContract.OnboardingState,
            reduce: OnboardingContract.OnboardingReduce,
        ): OnboardingContract.OnboardingState {
            return when (reduce) {
                is OnboardingContract.OnboardingReduce.UpdateState -> reduce.state
                is OnboardingContract.OnboardingReduce.UpdateUserName -> state.copy(userName = reduce.userName)
                is OnboardingContract.OnboardingReduce.UpdateSemester -> state.copy(semester = reduce.semester)
                is OnboardingContract.OnboardingReduce.UpdateSubject -> state.copy(subjectName = reduce.subject)
                is OnboardingContract.OnboardingReduce.DeleteUserName -> {
                    Timber.d("[온보딩] -> ${currentUiState.userName}")
                    state.copy(userName = null, buttonEnabled = false)
                }

                is OnboardingContract.OnboardingReduce.DeleteSubject -> {
                    state.copy(subjectName = null, buttonEnabled = false)
                }

                is OnboardingContract.OnboardingReduce.UpdateUserNameFocused -> {
                    Timber.d("[TextField] UpdateUserNameFocused -> ${reduce.isFocused}")
                    state.copy(userNameFocusedState = reduce.isFocused)
                }

                is OnboardingContract.OnboardingReduce.UpdateSubjectFocused -> state.copy(subjectNameFocusedState = reduce.isFocused)
                is OnboardingContract.OnboardingReduce.UpdateCurrentPage -> state.copy(currentPage = reduce.nextPage)
                is OnboardingContract.OnboardingReduce.UpdateUserNameTextFieldSate -> {
                    val checkedText = determineTextFieldType(reduce.userName, currentUiState.userNameFocusedState)
                    state.copy(userNameTextFieldState = checkedText)
                }

                is OnboardingContract.OnboardingReduce.UpdateSubjectNameTextFieldSate -> {
                    val checkedText = determineTextFieldType(reduce.subject, currentUiState.subjectNameFocusedState)
                    state.copy(subjectNameTextFieldState = checkedText)
                }

                is OnboardingContract.OnboardingReduce.UpdateButtonEnabled -> {
                    val buttonEnabled: Boolean =
                        when (currentUiState.currentPage) {
                            0 -> {
                                currentUiState.userNameTextFieldState == BbangZipTextFieldInputState.Typing || currentUiState.userNameTextFieldState == BbangZipTextFieldInputState.Field
                            }

                            1 -> {
                                currentUiState.semester.year == "2025" && currentUiState.semester.semester == SemesterType.FIRST
                            }

                            2 -> {
                                currentUiState.subjectNameTextFieldState == BbangZipTextFieldInputState.Typing || currentUiState.subjectNameTextFieldState == BbangZipTextFieldInputState.Field
                            }

                            else -> false
                        }
                    state.copy(buttonEnabled = buttonEnabled)
                }
            }
        }

        private fun determineTextFieldType(
            text: String,
            isFocused: Boolean,
        ): BbangZipTextFieldInputState {
            return when {
                text.isEmpty() && !isFocused -> BbangZipTextFieldInputState.Default
                text.isEmpty() && isFocused -> BbangZipTextFieldInputState.Placeholder
                text.contains(Regex("[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9 ]")) -> BbangZipTextFieldInputState.Alert
                text.isNotEmpty() && isFocused -> BbangZipTextFieldInputState.Typing
                else -> BbangZipTextFieldInputState.Field
            }
        }
    }
