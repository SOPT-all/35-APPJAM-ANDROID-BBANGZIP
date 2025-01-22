package org.android.bbangzip.domain.model

import org.android.bbangzip.presentation.model.BbangZip

data class BbangZipEntity (
    val level: Int,
    val levelName: String,
    val levelDescription: String,
    val levelImage: String,
    val levelIsLocked: Boolean
) {
    fun toBbangZip() = BbangZip(
        level = level.toString(),
        name = levelName,
        description = levelDescription,
        imageUrl = levelImage,
        isLocked = levelIsLocked
    )
}