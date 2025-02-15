package org.android.bbangzip.presentation.ui.subject.modify.subjectname

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
class ModifySubjectNameViewModel
    @Inject
    constructor(
        private val putSubjectOptionsUseCase: PutSubjectOptionsUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<ModifySubjectNameContract.ModifySubjectNameEvent, ModifySubjectNameContract.ModifySubjectNameState, ModifySubjectNameContract.ModifySubjectNameReduce, ModifySubjectNameContract.ModifySubjectNameSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): ModifySubjectNameContract.ModifySubjectNameState {
            return savedState as? ModifySubjectNameContract.ModifySubjectNameState ?: ModifySubjectNameContract.ModifySubjectNameState()
        }

        override fun handleEvent(event: ModifySubjectNameContract.ModifySubjectNameEvent) {
            when (event) {
                is ModifySubjectNameContract.ModifySubjectNameEvent.Initialize -> {
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateSubjectData(subjectId = event.subjectId, subjectName = event.subjectName))
                }

                is ModifySubjectNameContract.ModifySubjectNameEvent.OnChangeSubjectName -> {
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateSubjectName(subjectName = event.subjectName))
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateIsButtonEnabled)
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateSubjectNameInputState)
                }
                ModifySubjectNameContract.ModifySubjectNameEvent.OnClickBackBtn -> {
                }
                is ModifySubjectNameContract.ModifySubjectNameEvent.OnClickModifyBtn -> {
                    viewModelScope.launch {
                        putModifySubjectName(currentUiState.subjectId, currentUiState.subjectName)
                    }
                }
                is ModifySubjectNameContract.ModifySubjectNameEvent.OnFocusTextField -> {
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateIsTextFieldFocused(event.isTextFieldFocused))
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateSubjectNameInputState)
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateIsButtonEnabled)
                }

                ModifySubjectNameContract.ModifySubjectNameEvent.OnClickDeleteBtn -> {
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.ResetSubjectNamge)
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateSubjectNameInputState)
                    updateState(ModifySubjectNameContract.ModifySubjectNameReduce.UpdateIsButtonEnabled)
                }
            }
        }

        override fun reduceState(
            state: ModifySubjectNameContract.ModifySubjectNameState,
            reduce: ModifySubjectNameContract.ModifySubjectNameReduce,
        ): ModifySubjectNameContract.ModifySubjectNameState {
            return when (reduce) {
                ModifySubjectNameContract.ModifySubjectNameReduce.UpdateIsButtonEnabled -> {
                    state.copy(
                        isButtonEnable = state.subjectName.isNotEmpty() && state.subjectNameTextFieldState != BbangZipTextFieldInputState.Alert,
                    )
                }
                is ModifySubjectNameContract.ModifySubjectNameReduce.UpdateIsTextFieldFocused -> {
                    state.copy(
                        isTextFieldFocused = reduce.isTextFieldFocused,
                    )
                }
                is ModifySubjectNameContract.ModifySubjectNameReduce.UpdateSubjectName -> {
                    state.copy(
                        subjectName = reduce.subjectName,
                    )
                }
                ModifySubjectNameContract.ModifySubjectNameReduce.UpdateSubjectNameInputState -> {
                    state.copy(
                        subjectNameTextFieldState =
                            determineTextFieldType(
                                state.subjectName,
                                state.isTextFieldFocused,
                            ),
                    )
                }

                ModifySubjectNameContract.ModifySubjectNameReduce.ResetSubjectNamge -> {
                    state.copy(
                        subjectName = "",
                    )
                }

                is ModifySubjectNameContract.ModifySubjectNameReduce.UpdateSubjectData -> {
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
                text.contains(Regex("[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9 ]")) -> BbangZipTextFieldInputState.Alert
                text.isNotEmpty() && isFocused -> BbangZipTextFieldInputState.Typing
                else -> BbangZipTextFieldInputState.Field
            }
        }

        private suspend fun putModifySubjectName(
            subjectId: Int,
            subjectName: String,
        ) {
            putSubjectOptionsUseCase(
                subjectId = subjectId,
                options = "subjectName",
                requestSubjectOptions =
                    RequestSubjectOptions(
                        value = subjectName,
                    ),
            ).onSuccess {
                Timber.tag("motivate").d("과목명 저장")
                setSideEffect(ModifySubjectNameContract.ModifySubjectNameSideEffect.NavigationSubjectDetail(subjectId = subjectId, subjectName = subjectName))
            }.onFailure { error ->
                Timber.tag("motivate").d(error)
            }
        }
    }
