package org.android.bbangzip.presentation.type

import androidx.annotation.StringRes
import org.android.bbangzip.R

enum class ToDoFilterType(
    val filter: String,
    val id: String,
) {
    RECENT(
        filter = "최근 등록 순",
        id = "recent",
    ),
    VOLUME(
        filter = "분량 적은 순",
        id = "leastVolume",
    ),
    DEADLINE(
        filter = "마감 기한 빠른 순",
        id = "nearestDeadline",
    ),
}
