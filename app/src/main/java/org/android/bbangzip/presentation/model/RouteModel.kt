package org.android.bbangzip.presentation.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route

@Serializable
sealed interface BottomNavigationRoute : Route {
    @Serializable
    data object Subject : BottomNavigationRoute

    @Serializable
    data object Todo : BottomNavigationRoute

    @Serializable
    data object Friend : BottomNavigationRoute

    @Serializable
    data object My : BottomNavigationRoute

    @Serializable
    data object Dummy : BottomNavigationRoute
}
