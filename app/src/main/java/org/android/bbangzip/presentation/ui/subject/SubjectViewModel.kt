package org.android.bbangzip.presentation.ui.subject

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.data.dto.request.RequestDeleteSubjectsDto
import org.android.bbangzip.domain.usecase.DeleteSubjectsUseCase
import org.android.bbangzip.domain.usecase.GetSubjectInfoUseCase
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.SubjectCardModel
import org.android.bbangzip.presentation.type.CardViewType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel
    @Inject
    constructor(
        private val getSubjectInfoUseCase: GetSubjectInfoUseCase,
        private val deleteSubjectsUseCase: DeleteSubjectsUseCase,
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
                SubjectContract.SubjectEvent.Initialize -> launch { getSubjectInfo() }

                SubjectContract.SubjectEvent.OnDeleteButtonClick -> {
                    viewModelScope.launch {
                        deleteSubjects()
                    }
                }

                is SubjectContract.SubjectEvent.OnDeleteModeSubjectCardClick -> {
                    updateState(SubjectContract.SubjectReduce.UpdateSubjectCard(event.subjectId))
                    updateState(SubjectContract.SubjectReduce.UpdateDeletedSet(event.subjectId))
                }

                SubjectContract.SubjectEvent.OnTrashIconClick -> {
                    updateState(SubjectContract.SubjectReduce.UpdateToDeleteMode)
                }

                SubjectContract.SubjectEvent.OnCancleIconClick -> {
                    updateState(SubjectContract.SubjectReduce.UpdateToDefaultMode)
                }

                is SubjectContract.SubjectEvent.OnDefaultModeSubjectCardClick -> {
                    setSideEffect(SubjectContract.SubjectSideEffect.NavigateToSubjectDetail(event.subjectId, event.subjectName))
                }

                SubjectContract.SubjectEvent.OnAddSubjectCardClick -> {
                    setSideEffect(SubjectContract.SubjectSideEffect.NavigateToAddSubject)
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
                        subjectCardList =
                            state.subjectCardList.map { item ->
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

                SubjectContract.SubjectReduce.UpdateToDeleteMode -> {
                    state.copy(
                        subjectCardList =
                            state.subjectCardList.map {
                                it.copy(state = BbangZipCardState.CHECKABLE)
                            },
                        subjectCardViewType = CardViewType.DELETE,
                    )
                }

                SubjectContract.SubjectReduce.UpdateToDefaultMode -> {
                    state.copy(
                        subjectCardList =
                            state.subjectCardList.map {
                                it.copy(state = BbangZipCardState.DEFAULT)
                            },
                        subjectCardViewType = CardViewType.DEFAULT,
                        subjectIdSetToDelete = setOf(),
                    )
                }

                is SubjectContract.SubjectReduce.UpdateDeletedSet -> {
                    state.copy(
                        subjectIdSetToDelete =
                            run {
                                val targetSubject = state.subjectCardList.find { it.subjectId == reduce.subjectId }
                                when (targetSubject?.state) {
                                    BbangZipCardState.CHECKED -> {
                                        state.subjectIdSetToDelete.plus(targetSubject.subjectId)
                                    }

                                    BbangZipCardState.CHECKABLE -> {
                                        state.subjectIdSetToDelete.minus(targetSubject.subjectId)
                                    }

                                    else -> {
                                        state.subjectIdSetToDelete
                                    }
                                }
                            },
                    )
                }

                is SubjectContract.SubjectReduce.UpdateSubjectCardList -> {
                    state.copy(
                        subjectCardList = reduce.subjectList,
                    )
                }

                SubjectContract.SubjectReduce.ResetSubjectIdSetToDelete -> {
                    state.copy(
                        subjectIdSetToDelete = setOf(),
                    )
                }
            }
        }

        private suspend fun getSubjectInfo() {
            getSubjectInfoUseCase()
                .onSuccess { data ->
                    val subjectCardList =
                        data.subjectList.map {
                            val firstStudy = it.studyList.firstOrNull()
                            if (firstStudy == null) {
                                SubjectCardModel(
                                    subjectName = it.subjectName,
                                    examName = "",
                                    pendingCount = 0,
                                    inProgressCount = 0,
                                    subjectId = it.subjectId,
                                    examRemainingDays = 0,
                                )
                            } else {
                                SubjectCardModel(
                                    subjectName = it.subjectName,
                                    examName = firstStudy.examName ?: "",
                                    pendingCount = firstStudy.pendingCount ?: 0,
                                    inProgressCount = firstStudy.remainingCount ?: 0,
                                    subjectId = it.subjectId,
                                    examRemainingDays = firstStudy.examDday ?: 0,
                                )
                            }
                        }
                    updateState(SubjectContract.SubjectReduce.UpdateSubjectCardList(subjectList = subjectCardList))
                }.onFailure { error ->
                    Timber.tag("SubjectInfo").d(error)
                }
        }

        private suspend fun deleteSubjects() {
            deleteSubjectsUseCase(
                RequestDeleteSubjectsDto(
                    subjectIds = currentUiState.subjectIdSetToDelete.toList(),
                    year = 2025,
                    semester = "1학기",
                ),
            )
                .onSuccess {
                    updateState(SubjectContract.SubjectReduce.UpdateToDefaultMode)
                    updateState(SubjectContract.SubjectReduce.ResetSubjectIdSetToDelete)
                    getSubjectInfo()
                }
                .onFailure { error ->
                    Timber.tag("delete").d(error)
                }
        }
    }
