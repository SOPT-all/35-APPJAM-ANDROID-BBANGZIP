package org.android.bbangzip.presentation.ui.my.bbangzipdetail

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class BbangZipDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BbangZipDetailContract.BbangZipDetailEvent, BbangZipDetailContract.BbangZipDetailState, BbangZipDetailContract.BbangZipDetailReduce, BbangZipDetailContract.BbangZipDetailSideEffect>(
    savedStateHandle = savedStateHandle
) {
    override fun createInitialState(savedState: Parcelable?): BbangZipDetailContract.BbangZipDetailState {
        return savedState as? BbangZipDetailContract.BbangZipDetailState ?: BbangZipDetailContract.BbangZipDetailState()
    }

    override fun handleEvent(event: BbangZipDetailContract.BbangZipDetailEvent) {
        when (event) {
            is BbangZipDetailContract.BbangZipDetailEvent.OnClickBackBtn -> setSideEffect(BbangZipDetailContract.BbangZipDetailSideEffect.PopBackStack)
        }
    }

    override fun reduceState(state: BbangZipDetailContract.BbangZipDetailState, reduce: BbangZipDetailContract.BbangZipDetailReduce): BbangZipDetailContract.BbangZipDetailState {
        TODO("Not yet implemented")
    }
}