package org.android.bbangzip.presentation.ui.my.bbangzipdetail

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.domain.usecase.FetchBbangZipUseCase
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BbangZipDetailViewModel
    @Inject
    constructor(
        private val fetchBbangZipUseCase: FetchBbangZipUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<BbangZipDetailContract.BbangZipDetailEvent, BbangZipDetailContract.BbangZipDetailState, BbangZipDetailContract.BbangZipDetailReduce, BbangZipDetailContract.BbangZipDetailSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): BbangZipDetailContract.BbangZipDetailState {
            return savedState as? BbangZipDetailContract.BbangZipDetailState ?: BbangZipDetailContract.BbangZipDetailState()
        }

        init {
            setEvent(BbangZipDetailContract.BbangZipDetailEvent.Initialize)
        }

        override fun handleEvent(event: BbangZipDetailContract.BbangZipDetailEvent) {
            when (event) {
                is BbangZipDetailContract.BbangZipDetailEvent.Initialize -> launch { initDataLoad() }
                is BbangZipDetailContract.BbangZipDetailEvent.OnClickBackBtn -> setSideEffect(BbangZipDetailContract.BbangZipDetailSideEffect.PopBackStack)
            }
        }

        override fun reduceState(
            state: BbangZipDetailContract.BbangZipDetailState,
            reduce: BbangZipDetailContract.BbangZipDetailReduce,
        ): BbangZipDetailContract.BbangZipDetailState {
            return when (reduce) {
                is BbangZipDetailContract.BbangZipDetailReduce.UpdateMyBbangZipList -> state.copy(bbangZipList = reduce.bbangZipList)
            }
        }

        private suspend fun initDataLoad() {
            fetchBbangZipUseCase()
                .onSuccess { data ->
                    Timber.tag("[마이페이지] fetch 성공 ->").d("$data")
                    updateState(
                        BbangZipDetailContract.BbangZipDetailReduce.UpdateMyBbangZipList(
                            bbangZipList = data.levelDetails.map { it.toBbangZip() },
                        ),
                    )
                }.onFailure {
                    Timber.d("[마이페이지] fetch 실패 -> $error")
                }
        }
    }
