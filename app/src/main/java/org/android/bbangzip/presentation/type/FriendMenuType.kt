package org.android.bbangzip.presentation.type

import androidx.annotation.StringRes
import org.android.bbangzip.R

enum class FriendMenuType(
    @StringRes val id: Int,
) {
    ALL(
        id = R.string.friend_type_all
    ),
    GROUP(
        id = R.string.friend_type_group
    ),
    PENDING(
        id = R.string.friend_type_pending
    )
}