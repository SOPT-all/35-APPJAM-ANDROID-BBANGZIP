package org.android.bbangzip.presentation.type

import androidx.annotation.StringRes
import org.android.bbangzip.R

enum class FriendMenuType(
    @StringRes name: Int,
    isSelected: Boolean
) {
    All(
        name = R.string.friend_type_all,
        isSelected = true
    ),
    GROUP(
        name = R.string.friend_type_group,
        isSelected = false
    ),
    PENDING(
        name = R.string.friend_type_pending,
        isSelected = false
    ),
}