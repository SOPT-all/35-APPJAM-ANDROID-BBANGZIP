package org.android.bbangzip.presentation.ui.dummy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.Dummy
import org.android.bbangzip.presentation.util.base.BaseContract

class DummyContract {
    @Parcelize
    data class DummyState(
        val loading: Boolean = false,
        val dummy: Dummy? = null,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface DummyEvent : BaseContract.Event {
        data object Initialize : DummyEvent

        data class OnClickNextBtn(val dummy: String) : DummyEvent
    }

    sealed interface DummyReduce : BaseContract.Reduce {
        data class UpdateState(val state: DummyState) : DummyReduce

        data class UpdateLoading(val loading: Boolean) : DummyReduce

        data class UpdateDummy(val dummy: Dummy) : DummyReduce
    }

    sealed interface DummySideEffect : BaseContract.SideEffect {
        data class ShowSnackBar(val message: String) : DummySideEffect
    }
}
