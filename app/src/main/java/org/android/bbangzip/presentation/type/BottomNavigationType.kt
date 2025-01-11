package org.android.bbangzip.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import org.android.bbangzip.R
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.model.Route

enum class BottomNavigationType(
    @DrawableRes val bottomNaviIcon: Int,
    @StringRes val bottomNaviTitle: Int,
    val route: BottomNavigationRoute,
) {
    SUBJECT(
        bottomNaviIcon = R.drawable.ic_book_default_24,
        bottomNaviTitle = R.string.bottomNavigation_first_tab_title,
        route = BottomNavigationRoute.Subject,
    ),
    TODO(
        bottomNaviIcon = R.drawable.ic_file_check_default_24,
        bottomNaviTitle = R.string.bottomNavigation_second_tab_title,
        route = BottomNavigationRoute.Todo,
    ),
    FRIEND(
        bottomNaviIcon = R.drawable.ic_messagebox_default_24,
        bottomNaviTitle = R.string.bottomNavigation_third_tab_title,
        route = BottomNavigationRoute.Friend,
    ),
    MY(
        bottomNaviIcon = R.drawable.ic_user_one_default_24,
        bottomNaviTitle = R.string.bottomNavigation_fourth_tab_title,
        route = BottomNavigationRoute.My,
    ),
    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (BottomNavigationRoute) -> Boolean): BottomNavigationType? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun any(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
