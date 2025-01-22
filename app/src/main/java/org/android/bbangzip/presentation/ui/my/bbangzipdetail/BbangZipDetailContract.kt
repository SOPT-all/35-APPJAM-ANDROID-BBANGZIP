package org.android.bbangzip.presentation.ui.my.bbangzipdetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.BbangZip
import org.android.bbangzip.presentation.util.base.BaseContract

class BbangZipDetailContract {
    @Parcelize
    data class BbangZipDetailState(
        val bbangZipList: List<BbangZip> = emptyList(),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface BbangZipDetailEvent : BaseContract.Event {
        data object OnClickBackBtn : BbangZipDetailEvent
    }

    sealed interface BbangZipDetailReduce : BaseContract.Reduce {

    }

    sealed interface BbangZipDetailSideEffect : BaseContract.SideEffect {
        data object PopBackStack : BbangZipDetailSideEffect
    }
}