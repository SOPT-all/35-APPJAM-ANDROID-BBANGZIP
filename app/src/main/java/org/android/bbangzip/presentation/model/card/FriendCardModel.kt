package org.android.bbangzip.presentation.model.card

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FriendCardModel(
    val friendId: Int,
    val friendName: String,
    val imageUrl: String,
) : Parcelable