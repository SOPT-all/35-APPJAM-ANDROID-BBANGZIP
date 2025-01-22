package org.android.bbangzip.presentation.ui.friend.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.ui.friend.FriendRoute

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
