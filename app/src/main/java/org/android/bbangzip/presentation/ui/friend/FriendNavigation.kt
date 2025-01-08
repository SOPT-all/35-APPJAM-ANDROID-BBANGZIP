package org.android.bbangzip.presentation.ui.friend

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute

fun NavController.navigateFriend(navOptions: NavOptions) {
    navigate(
        route = BottomNavigationRoute.Friend,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.friendNavGraph() {
    composable<BottomNavigationRoute.Friend> {
        FriendRoute()
    }
}
