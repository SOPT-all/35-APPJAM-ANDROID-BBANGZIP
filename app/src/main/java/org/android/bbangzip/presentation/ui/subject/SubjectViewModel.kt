package org.android.bbangzip.presentation.ui.subject

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.data.dto.request.RequestDeleteSubjectsDto
import org.android.bbangzip.domain.usecase.DeleteSubjectsUseCase
import org.android.bbangzip.domain.usecase.GetSubjectInfoUseCase
import org.android.bbangzip.domain.usecase.PostAddSubjectNameUseCase
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
                is SubjectContract.SubjectEvent.Initialize -> launch { getSubjectInfo() }

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
                    setSideEffect(SubjectContract.SubjectSideEffect.NavigateToSubjectDetail(event.subjectId, event.subjectName))
                }

                is SubjectContract.SubjectEvent.OnClickAddSubject -> {
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

                is SubjectContract.SubjectReduce.UpdateSubjectCardList -> {
                    Timber.tag("이승범").d("뭐가 문제야%s", reduce.subjectList.toString())
                    state.copy(
                        subjectList = reduce.subjectList,
                    )
                }

                is SubjectContract.SubjectReduce.RestoreDeletedSet -> {
                    state.copy(
                        subjectSetToDelete = setOf(),
                    )
                }
            }
        }

        private suspend fun getSubjectInfo() {
            getSubjectInfoUseCase()
                .onSuccess { data ->
                    Timber.tag("이승범").d(data.toString())
                    val subjectCardList =
                        data.subjectList.map {
                            if (it.studyList.isEmpty()) {
                                return@map SubjectCardModel(
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
                                    examName = it.studyList[0]?.examName ?: "",
                                    pendingCount = it.studyList[0]?.pendingCount ?: 0,
                                    inProgressCount = it.studyList[0]?.remainingCount ?: 0,
                                    subjectId = it.subjectId,
                                    examRemainingDays = it.studyList[0]?.examDday ?: 0,
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
                    subjectIds = currentUiState.subjectSetToDelete.toList(),
                    year = 2025,
                    semester = "1학기",
                )
            )
                .onSuccess {
                    updateState(SubjectContract.SubjectReduce.UpdateToDefaultMode)
                    getSubjectInfo()
                }
                .onFailure { error ->
                    Timber.tag("이승범").d(error)
                }
        }
    }
