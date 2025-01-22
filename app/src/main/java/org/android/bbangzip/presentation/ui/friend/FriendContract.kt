package org.android.bbangzip.presentation.ui.friend

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.FriendCardModel
import org.android.bbangzip.presentation.type.FriendMenuType
import org.android.bbangzip.presentation.util.base.BaseContract

class FriendContract {
    @Parcelize
    data class FriendState(
        val friendList: List<FriendCardModel> = listOf(
            FriendCardModel(
                friendId = 1,
                friendName = "이승범",
                imageUrl = "https://example.com/alice.jpg",
                state = BbangZipCardState.DEFAULT
            ),
            FriendCardModel(
                friendId = 2,
                friendName = "김재민",
                imageUrl = "https://example.com/bob.jpg",
                state = BbangZipCardState.DEFAULT
            ),
            FriendCardModel(
                friendId = 3,
                friendName = "하지은",
                imageUrl = "https://example.com/charlie.jpg",
                state = BbangZipCardState.DEFAULT
            ), FriendCardModel(
                friendId = 4,
                friendName = "신우연",
                imageUrl = "https://example.com/alice.jpg",
                state = BbangZipCardState.DEFAULT
            ),
            FriendCardModel(
                friendId = 5,
                friendName = "김재민",
                imageUrl = "https://example.com/bob.jpg",
                state = BbangZipCardState.DEFAULT
            ),
            FriendCardModel(
                friendId = 6,
                friendName = "하지은",
                imageUrl = "https://example.com/charlie.jpg",
                state = BbangZipCardState.DEFAULT
            ), FriendCardModel(
                friendId = 7,
                friendName = "이승범",
                imageUrl = "https://example.com/alice.jpg",
                state = BbangZipCardState.DEFAULT
            ),

        ),
        val menuFilterType: FriendMenuType = FriendMenuType.ALL,
        val selectedFilter :String = "이름순"
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface FriendEvent : BaseContract.Event {
    }

    sealed interface FriendReduce : BaseContract.Reduce {
    }

    sealed interface FriendSideEffect : BaseContract.SideEffect {

    }
}
