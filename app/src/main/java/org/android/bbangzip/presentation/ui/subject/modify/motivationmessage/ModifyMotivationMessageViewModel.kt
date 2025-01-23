package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ModifyMotivationMessageViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ModifyMotivationMessageContract.ModifyMotivationMessageEvent, ModifyMotivationMessageContract.ModifyMotivationMessageState, ModifyMotivationMessageContract.ModifyMotivationMessageReduce, ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedState: Parcelable?): ModifyMotivationMessageContract.ModifyMotivationMessageState {
        return savedState as? ModifyMotivationMessageContract.ModifyMotivationMessageState ?: ModifyMotivationMessageContract.ModifyMotivationMessageState()
    }

    override fun handleEvent(event: ModifyMotivationMessageContract.ModifyMotivationMessageEvent) {
        when (event){
            is ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnChangeMotivationMessage -> {
                updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessage(MotivationMessage = event.motivationMessage))
                updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsButtonEnabled)
                updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessageInputState)
            }
            ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickBackBtn -> {

            }
            ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickModifyBtn -> {

            }
            is ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnFocusTextField -> {
                updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsTextFieldFocused(event.isTextFieldFocused))
                updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessageInputState)
            }

            ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickDeleteBtn -> {
                updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.ResetSubjectNamge)
                updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessageInputState)
                updateState(ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsButtonEnabled)
            }
        }
    }
    override fun reduceState(
        state: ModifyMotivationMessageContract.ModifyMotivationMessageState,
        reduce: ModifyMotivationMessageContract.ModifyMotivationMessageReduce
    ) : ModifyMotivationMessageContract.ModifyMotivationMessageState
    {
        return when(reduce){
            ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsButtonEnabled -> {
                state.copy(
                    isButtonEnable = state.motivationMessage.isNotEmpty()
                )
            }
            is ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateIsTextFieldFocused -> {
                state.copy(
                    isTextFieldFocused = reduce.isTextFieldFocused
                )
            }
            is ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessage -> {
                state.copy(
                    motivationMessage = reduce.MotivationMessage
                )
            }
            ModifyMotivationMessageContract.ModifyMotivationMessageReduce.UpdateMotivationMessageInputState -> {
                state.copy(
                    motivationMessageTextFieldState = determineTextFieldType(
                        state.motivationMessage,
                        state.isTextFieldFocused
                    )
                )
            }

            ModifyMotivationMessageContract.ModifyMotivationMessageReduce.ResetSubjectNamge -> {
                state.copy(
                    motivationMessage = ""
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
}