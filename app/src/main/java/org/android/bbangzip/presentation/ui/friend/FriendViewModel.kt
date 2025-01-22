package org.android.bbangzip.presentation.ui.friend

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import org.android.bbangzip.presentation.ui.todo.TodoContract
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

class FriendViewModel @Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<FriendContract.FriendEvent, FriendContract.FriendState, FriendContract.FriendReduce, FriendContract.FriendSideEffect>(
    savedStateHandle = savedStateHandle,
)  {
    override fun createInitialState(savedState: Parcelable?): FriendContract.FriendState {
        return savedState as? FriendContract.FriendState ?: FriendContract.FriendState()
    }

    override fun handleEvent(event: FriendContract.FriendEvent) {
        TODO("Not yet implemented")
    }

    override fun reduceState(state: FriendContract.FriendState, reduce: FriendContract.FriendReduce): FriendContract.FriendState {
        TODO("Not yet implemented")
    }
}