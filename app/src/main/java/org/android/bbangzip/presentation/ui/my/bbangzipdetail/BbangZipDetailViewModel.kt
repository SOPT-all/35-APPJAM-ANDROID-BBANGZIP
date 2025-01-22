package org.android.bbangzip.presentation.ui.my.bbangzipdetail

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.domain.usecase.FetchBbangZipUseCase
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BbangZipDetailViewModel @Inject constructor(
    private val fetchBbangZipUseCase: FetchBbangZipUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BbangZipDetailContract.BbangZipDetailEvent, BbangZipDetailContract.BbangZipDetailState, BbangZipDetailContract.BbangZipDetailReduce, BbangZipDetailContract.BbangZipDetailSideEffect>(
    savedStateHandle = savedStateHandle
) {
    override fun createInitialState(savedState: Parcelable?): BbangZipDetailContract.BbangZipDetailState {
        return savedState as? BbangZipDetailContract.BbangZipDetailState ?: BbangZipDetailContract.BbangZipDetailState()
    }

    override fun handleEvent(event: BbangZipDetailContract.BbangZipDetailEvent) {
        when (event) {
            is BbangZipDetailContract.BbangZipDetailEvent.Initialize -> launch { initDataLoad() }
            is BbangZipDetailContract.BbangZipDetailEvent.OnClickBackBtn -> setSideEffect(BbangZipDetailContract.BbangZipDetailSideEffect.PopBackStack)
        }
    }

    override fun reduceState(state: BbangZipDetailContract.BbangZipDetailState, reduce: BbangZipDetailContract.BbangZipDetailReduce): BbangZipDetailContract.BbangZipDetailState {
        TODO("Not yet implemented")
    }

    private fun initDataLoad() {
        fetchMyData()
    }

    private fun fetchMyData() {
        viewModelScope.launch {
            fetchBbangZipUseCase()
                .onSuccess { data ->
                    updateState(
                        BbangZipDetailContract.BbangZipDetailReduce.UpdateMyBbangZipList(
                            bbangZipList = data.levelDetails.map { it.toBbangZip() }
                        )
                    )
                }.onFailure {
                    Timber.d("[마이페이지] fetch 실패 -> $error")
                }
        }
    }
}
