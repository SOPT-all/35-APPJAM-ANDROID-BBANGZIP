package org.android.bbangzip.presentation.util.casting

fun pageToInt(
    page: String,
): Int {
    return if (page.last() == 'p') {
        page.dropLast(1).toInt()
    } else {
        page.toInt()
    }
}
