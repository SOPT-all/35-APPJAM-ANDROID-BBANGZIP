package org.android.bbangzip.presentation.type

import androidx.annotation.StringRes
import org.android.bbangzip.R

enum class ToDoFilterType(
    @StringRes val filter: Int,
    val id: String,
) {
    RECENT(
        filter = R.string.todo_filter_recent,
        id = "recent",
    ),
    VOLUME(
        filter = R.string.todo_filter_least_volume,
        id = "leastVolume",
    ),
    DEADLINE(
        filter = R.string.todo_filter_deadline,
        id = "nearestDeadline",
    ),
}
