package org.android.bbangzip.presentation.ui.subject.addsubject

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.data.dto.request.RequestAddSubjectsDto
import org.android.bbangzip.data.dto.request.RequestSubjectOptions
import org.android.bbangzip.domain.usecase.PostAddSubjectNameUseCase
import org.android.bbangzip.domain.usecase.PutSubjectOptionsUseCase
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.ui.subject.modify.motivationmessage.ModifyMotivationMessageContract
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddSubjectViewModel
    @Inject
    constructor(
        private val postAddSubjectNameUseCase: PostAddSubjectNameUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<AddSubjectContract.AddSubjectEvent, AddSubjectContract.AddSubjectState, AddSubjectContract.AddSubjectReduce, AddSubjectContract.AddSubjectSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): AddSubjectContract.AddSubjectState {
            return savedState as? AddSubjectContract.AddSubjectState ?: AddSubjectContract.AddSubjectState()
        }

        override fun handleEvent(event: AddSubjectContract.AddSubjectEvent) {
            when (event) {
                is AddSubjectContract.AddSubjectEvent.OnChangeSubjectName -> {
                    updateState(AddSubjectContract.AddSubjectReduce.UpdateSubjectName(subjectName = event.subjectName))
                    updateState(AddSubjectContract.AddSubjectReduce.UpdateIsButtonEnabled)
                    updateState(AddSubjectContract.AddSubjectReduce.UpdateSubjectInputState)
                }

                AddSubjectContract.AddSubjectEvent.OnClickBackBtn -> {
                    setSideEffect(AddSubjectContract.AddSubjectSideEffect.NavigateSubjectDetail)
                }

                AddSubjectContract.AddSubjectEvent.OnClickAddBtn -> {
                    viewModelScope.launch {
                        putMotivationMessage()
                    }
                }

                is AddSubjectContract.AddSubjectEvent.OnFocusTextField -> {
                    updateState(AddSubjectContract.AddSubjectReduce.UpdateIsTextFieldFocused(event.isTextFieldFocused))
                    updateState(AddSubjectContract.AddSubjectReduce.UpdateSubjectInputState)
                    updateState(AddSubjectContract.AddSubjectReduce.UpdateIsButtonEnabled)
                }

                AddSubjectContract.AddSubjectEvent.OnClickDeleteBtn -> {
                    updateState(AddSubjectContract.AddSubjectReduce.ResetSubjectName)
                    updateState(AddSubjectContract.AddSubjectReduce.UpdateSubjectInputState)
                    updateState(AddSubjectContract.AddSubjectReduce.UpdateIsButtonEnabled)
                }
            }
        }

        override fun reduceState(
            state: AddSubjectContract.AddSubjectState,
            reduce: AddSubjectContract.AddSubjectReduce,
        ): AddSubjectContract.AddSubjectState {
            return when (reduce) {
                AddSubjectContract.AddSubjectReduce.UpdateIsButtonEnabled -> {
                    state.copy(
                        isButtonEnable = state.subjectName.isNotEmpty() && state.subjectTextFieldState != BbangZipTextFieldInputState.Alert,
                    )
                }
                is AddSubjectContract.AddSubjectReduce.UpdateIsTextFieldFocused -> {
                    state.copy(
                        isTextFieldFocused = reduce.isTextFieldFocused,
                    )
                }
                is AddSubjectContract.AddSubjectReduce.UpdateSubjectName -> {
                    state.copy(
                        subjectName = reduce.subjectName,
                    )
                }
                AddSubjectContract.AddSubjectReduce.UpdateSubjectInputState -> {
                    state.copy(
                        subjectTextFieldState =
                            determineTextFieldType(
                                state.subjectName,
                                state.isTextFieldFocused,
                            ),
                    )
                }

                AddSubjectContract.AddSubjectReduce.ResetSubjectName -> {
                    state.copy(
                        subjectName = "",
                    )
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

    private suspend fun putMotivationMessage(
    ) {
        postAddSubjectNameUseCase(
           requestAddSubjectsDto = RequestAddSubjectsDto(
                subjectName = currentUiState.subjectName,
               year = 2025,
               semester = "1학기"
            )
        ).onSuccess {
            Timber.tag("motivate").d("과목명 저장")
            setSideEffect(AddSubjectContract.AddSubjectSideEffect.NavigateSubjectDetail)
        }.onFailure {error->
            Timber.tag("motivate").d(error)
        }
    }
    }
