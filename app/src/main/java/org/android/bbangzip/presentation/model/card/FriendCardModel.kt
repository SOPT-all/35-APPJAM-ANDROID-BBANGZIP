package org.android.bbangzip.presentation.model.card

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState

@Parcelize
data class FriendCardModel(
    val friendId: Int,
    val friendName: String,
    val imageUrl: String,
    val state: BbangZipCardState,
    @DrawableRes val imgSrc: Int = 0,
) : Parcelable
