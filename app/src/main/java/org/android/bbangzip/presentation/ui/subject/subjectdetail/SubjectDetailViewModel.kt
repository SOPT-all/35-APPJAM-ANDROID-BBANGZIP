package org.android.bbangzip.presentation.ui.subject.subjectdetail

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.type.PieceViewType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SubjectDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<SubjectDetailContract.SubjectDetailEvent, SubjectDetailContract.SubjectDetailState, SubjectDetailContract.SubjectDetailReduce, SubjectDetailContract.SubjectDetailSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): SubjectDetailContract.SubjectDetailState {
            return savedState as? SubjectDetailContract.SubjectDetailState ?: SubjectDetailContract.SubjectDetailState()
        }

        init {
            setEvent(SubjectDetailContract.SubjectDetailEvent.Initialize)
        }

        override fun handleEvent(event: SubjectDetailContract.SubjectDetailEvent) {
            when (event) {
                is SubjectDetailContract.SubjectDetailEvent.Initialize -> {}

                is SubjectDetailContract.SubjectDetailEvent.OnTrashIconClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateToDeleteMode)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnCloseIconClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateToDefaultMode)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnDeleteModeCardClicked -> {
                    Timber.d("OnDeleteModeCardClicked: ${event.pieceId}")
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDeleteModeCardState(event.pieceId))
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDeleteSet(event.pieceId))
                    Timber.d("OnDeleteModeCardClicked: ${event.pieceId}")
                }

                is SubjectDetailContract.SubjectDetailEvent.OnDefaultCardClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDefaultCardState(event.pieceId))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnCompleteCardClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateSelectedId(event.pieceId))
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetApproveButtonClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateCompleteCardState)
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissButtonClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDissmissRequest -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState)
                }

                else -> {}
            }
        }

        override fun reduceState(
            state: SubjectDetailContract.SubjectDetailState,
            reduce: SubjectDetailContract.SubjectDetailReduce,
        ): SubjectDetailContract.SubjectDetailState {
            return when (reduce) {
                is SubjectDetailContract.SubjectDetailReduce.UpdateToDeleteMode -> {
                    state.copy(
                        todoList =
                            state.todoList.map {
                                if (it.cardState == BbangZipCardState.COMPLETE) it else it.copy(cardState = BbangZipCardState.CHECKABLE)
                            },
                        pieceViewType = PieceViewType.DELETE,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateToDefaultMode -> {
                    state.copy(
                        todoList =
                            state.todoList.map {
                                if (it.cardState == BbangZipCardState.COMPLETE) it else it.copy(cardState = BbangZipCardState.DEFAULT)
                            },
                        pieceViewType = PieceViewType.DEFAULT,
                        selectedItemSet = setOf(),
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateDeleteSet -> {
                    Timber.d("[update] ${state.selectedItemSet}")

                    state.copy(
                        selectedItemSet =
                            run {
                                val targetPiece = state.todoList.find { it.pieceId == reduce.pieceId }

                                when (targetPiece?.cardState) {
                                    BbangZipCardState.CHECKED -> {
                                        state.selectedItemSet.plus(targetPiece.pieceId)
                                    }

                                    BbangZipCardState.CHECKABLE -> {
                                        state.selectedItemSet.minus(targetPiece.pieceId)
                                    }

                                    else -> {
                                        state.selectedItemSet
                                    }
                                }
                            },
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateDeleteModeCardState -> {
                    state.copy(
                        todoList =
                            state.todoList.map { item ->
                                if (item.cardState == BbangZipCardState.CHECKABLE && item.pieceId == reduce.pieceId) {
                                    item.copy(cardState = BbangZipCardState.CHECKED)
                                } else if (item.cardState == BbangZipCardState.CHECKED && item.pieceId == reduce.pieceId) {
                                    item.copy(cardState = BbangZipCardState.CHECKABLE)
                                } else {
                                    item
                                }
                            },
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateDefaultCardState -> {
                    state.copy(
                        todoList =
                            state.todoList.map { item ->
                                if (item.cardState == BbangZipCardState.DEFAULT && item.pieceId == reduce.pieceId) {
                                    item.copy(cardState = BbangZipCardState.COMPLETE)
                                } else {
                                    item
                                }
                            },
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState -> {
                    state.copy(
                        revertCompleteBottomSheetState = !state.revertCompleteBottomSheetState,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateSelectedId -> {
                    state.copy(
                        selectedItemId = reduce.pieceId,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateCompleteCardState -> {
                    state.copy(
                        todoList =
                            state.todoList.map { item ->
                                if (item.pieceId == state.selectedItemId) {
                                    item.copy(cardState = BbangZipCardState.DEFAULT)
                                } else {
                                    item
                                }
                            },
                    )
                }

                else -> state
            }
        }
    }
