package org.android.bbangzip.presentation.ui.dummy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import org.android.bbangzip.presentation.model.Dummy
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DummyContract.DummyEvent, DummyContract.DummyState, DummyContract.DummyReduce, DummyContract.DummySideEffect>(
    savedStateHandle = savedStateHandle
) {
    override fun createInitialState(savedState: Parcelable?): DummyContract.DummyState {
        return savedState as DummyContract.DummyState
    }

    init {
        setEvent(DummyContract.DummyEvent.Initialize)
    }

    override fun handleEvent(event: DummyContract.DummyEvent) {
        when (event) {
            is DummyContract.DummyEvent.Initialize -> launch { initDataLoad() }
            is DummyContract.DummyEvent.OnClickNextBtn -> {
                setSideEffect(DummyContract.DummySideEffect.ShowSnackBar("메세지 전송"))
                updateState(DummyContract.DummyReduce.UpdateDummy(dummy = Dummy()))
            }
        }
    }

    override fun reduceState(state: DummyContract.DummyState, reduce: DummyContract.DummyReduce): DummyContract.DummyState {
        return when (reduce) {
            is DummyContract.DummyReduce.UpdateState -> reduce.state
            is DummyContract.DummyReduce.UpdateLoading -> state.copy(loading = reduce.loading)
            is DummyContract.DummyReduce.UpdateDummy -> state.copy(dummy = Dummy())
        }
    }

    private fun initDataLoad() {
        runBlocking {
            launch {
                // TODO 초기 데이터 로드
            }
        }
    }
}