package org.android.bbangzip.presentation.util.casting

fun pageToInt(
    page: String,
): Int {
    if (page.last() == 'p') {
        return page.dropLast(2).toInt()
    } else {
        return page.toInt()
    }
}
