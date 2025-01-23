package org.android.bbangzip.presentation.ui.subject

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.type.CardViewType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<SubjectContract.SubjectEvent, SubjectContract.SubjectState, SubjectContract.SubjectReduce, SubjectContract.SubjectSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): SubjectContract.SubjectState {
            return savedState as? SubjectContract.SubjectState ?: SubjectContract.SubjectState()
        }

        init {
            setEvent(SubjectContract.SubjectEvent.Initialize)
        }

        override fun handleEvent(event: SubjectContract.SubjectEvent) {
            when (event) {
                is SubjectContract.SubjectEvent.Initialize -> {}

                is SubjectContract.SubjectEvent.OnClickDeleteButton -> {
                    updateState(SubjectContract.SubjectReduce.UpdateDeletedSet(event.subjectId))
                }

                is SubjectContract.SubjectEvent.OnClickDeleteModeCard -> {
                    updateState(SubjectContract.SubjectReduce.UpdateSubjectCard(event.subjectId))
                    updateState(SubjectContract.SubjectReduce.UpdateDeletedSet(event.subjectId))
                }

                is SubjectContract.SubjectEvent.OnClickTrashIcon -> {
                    updateState(SubjectContract.SubjectReduce.UpdateToDeleteMode)
                }

                is SubjectContract.SubjectEvent.OnClickCancleIcon -> {
                    updateState(SubjectContract.SubjectReduce.UpdateToDefaultMode)
                }

                is SubjectContract.SubjectEvent.OnClickStudyCard -> {
                    setSideEffect(SubjectContract.SubjectSideEffect.NavigateToSubjectDetail(event.subjectId))
                }
            }
        }

        override fun reduceState(
            state: SubjectContract.SubjectState,
            reduce: SubjectContract.SubjectReduce,
        ): SubjectContract.SubjectState {
            return when (reduce) {
                is SubjectContract.SubjectReduce.UpdateSubjectCard -> {
                    state.copy(
                        subjectList =
                            state.subjectList.map { item ->
                                if (item.state == BbangZipCardState.CHECKABLE && item.subjectId == reduce.subjectId) {
                                    item.copy(state = BbangZipCardState.CHECKED)
                                } else if (item.state == BbangZipCardState.CHECKED && item.subjectId == reduce.subjectId) {
                                    item.copy(state = BbangZipCardState.CHECKABLE)
                                } else {
                                    item
                                }
                            },
                    )
                }

                is SubjectContract.SubjectReduce.UpdateToDeleteMode -> {
                    state.copy(
                        subjectList =
                            state.subjectList.map {
                                it.copy(state = BbangZipCardState.CHECKABLE)
                            },
                        cardViewType = CardViewType.DELETE,
                    )
                }

                is SubjectContract.SubjectReduce.UpdateToDefaultMode -> {
                    state.copy(
                        subjectList =
                            state.subjectList.map {
                                it.copy(state = BbangZipCardState.DEFAULT)
                            },
                        cardViewType = CardViewType.DEFAULT,
                        subjectSetToDelete = setOf(),
                    )
                }

                is SubjectContract.SubjectReduce.UpdateDeletedSet -> {
                    state.copy(
                        subjectSetToDelete =
                            run {
                                val targetSubject = state.subjectList.find { it.subjectId == reduce.subjectId }
                                when (targetSubject?.state) {
                                    BbangZipCardState.CHECKED -> {
                                        state.subjectSetToDelete.plus(targetSubject.subjectId)
                                    }

                                    BbangZipCardState.CHECKABLE -> {
                                        state.subjectSetToDelete.minus(targetSubject.subjectId)
                                    }

                                    else -> {
                                        state.subjectSetToDelete
                                    }
                                }
                            },
                    )
                }
            }
        }
    }
