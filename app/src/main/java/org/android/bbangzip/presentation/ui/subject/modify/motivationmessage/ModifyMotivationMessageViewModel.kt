package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.data.dto.request.RequestSubjectOptions
import org.android.bbangzip.domain.usecase.PutSubjectOptionsUseCase
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ModifyMotivationMessageViewModel
    @Inject
    constructor(
        private val putSubjectOptionsUseCase: PutSubjectOptionsUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<ModifyMotivationMessageContract.ModifyMotivationMessageEvent, ModifyMotivationMessageContract.ModifyMotivationMessageState, ModifyMotivationMessageContract.ModifyMotivationMessageReduce, ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): ModifyMotivationMessageContract.ModifyMotivationMessageState {
            return savedState as? ModifyMotivationMessageContract.ModifyMotivationMessageState ?: ModifyMotivationMessageContract.ModifyMotivationMessageState()
        }

        override fun handleEvent(event: ModifyMotivationMessageContract.ModifyMotivationMessageEvent) {
            when (event) {
                is ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnChangeMotivationMessage -> {
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessage(motivationMessage = event.motivationMessage))
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsButtonEnabled)
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessageInputState)
                }

                ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickBackBtn -> {
                }

                is ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickModifyBtn -> {
                    viewModelScope.launch {
                        putMotivationMessage(event.subjectId, event.subjectName)
                    }
                }

                is ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnFocusTextField -> {
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsTextFieldFocused(event.isTextFieldFocused))
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessageInputState)
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsButtonEnabled)
                }

                ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickDeleteBtn -> {
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.ResetSubjectNamge)
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessageInputState)
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsButtonEnabled)
                }

                is ModifyMotivationMessageContract.ModifyMotivationMessageEvent.Initialize ->
                    updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateSubjectInfo(event.subjectId, event.subjectName))
            }
        }

        override fun reduceState(
            state: ModifyMotivationMessageContract.ModifyMotivationMessageState,
            reduce: ModifyMotivationMessageContract.ModifyMotivationMessageReduce,
        ): ModifyMotivationMessageContract.ModifyMotivationMessageState {
            return when (reduce) {
                ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsButtonEnabled -> {
                    state.copy(
                        isButtonEnable = state.motivationMessage.isNotEmpty() && state.motivationMessageTextFieldState != BbangZipTextFieldInputState.Alert,
                    )
                }

                is ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsTextFieldFocused -> {
                    state.copy(
                        isTextFieldFocused = reduce.isTextFieldFocused,
                    )
                }

                is ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessage -> {
                    state.copy(
                        motivationMessage = reduce.motivationMessage,
                    )
                }

                ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessageInputState -> {
                    state.copy(
                        motivationMessageTextFieldState =
                            determineTextFieldType(
                                state.motivationMessage,
                                state.isTextFieldFocused,
                            ),
                    )
                }

                ModifyMotivationMessageContract.ModifyMotivationMessageReduce.ResetSubjectNamge -> {
                    state.copy(
                        motivationMessage = "",
                    )
                }

                is ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateSubjectInfo -> {
                    state.copy(
                        subjectId = reduce.subjectId,
                        subjectName = reduce.subjectName,
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
                text.contains(Regex("[\\p{So}\\p{Cn}]+")) -> BbangZipTextFieldInputState.Alert
                text.isNotEmpty() && isFocused -> BbangZipTextFieldInputState.Typing
                else -> BbangZipTextFieldInputState.Field
            }
        }

        private suspend fun putMotivationMessage(
            subjectId: Int,
            subjectName: String,
        ) {
            putSubjectOptionsUseCase(
                subjectId = subjectId,
                options = "motivationMessage",
                requestSubjectOptions =
                    RequestSubjectOptions(
                        value = currentUiState.motivationMessage,
                    ),
            ).onSuccess {
                Timber.tag("motivate").d("각오 한 마디 저장")
                setSideEffect(ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect.NavigateSubjectDetail(subjectId = subjectId, subjectName = subjectName))
                setSideEffect(ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect.ShowSnackBar("각오 한 마디 작성 완료!"))
            }.onFailure { error ->
                Timber.tag("motivate").d(error)
            }
        }
    }
