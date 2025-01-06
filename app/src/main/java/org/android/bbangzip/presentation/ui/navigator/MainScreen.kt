package org.android.bbangzip.presentation.ui.navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import okhttp3.internal.toImmutableList
import org.android.bbangzip.presentation.type.BottomNavigationType
import org.android.bbangzip.presentation.ui.navigator.component.BottomNavigationBar
import org.android.bbangzip.ui.theme.BBANGZIPTheme

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    MainScreenContent(
        navigator = navigator,
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
) {
    Scaffold(
        modifier = modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding,
            )
        },
        bottomBar = {
            BottomNavigationBar(
                isVisible = navigator.showBottomBar(),
                modifier =
                    Modifier
                        .background(Color.Green)
                        .padding(top = 8.dp, bottom = 10.dp),
                bottomNaviBarItems = BottomNavigationType.entries.toImmutableList(),
                currentNaviBarItemSelected = navigator.currentBottomNavigationBarItem,
                onBottomNaviBarItemSelected = { navigator.navigateBottomNavigation(it) },
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    BBANGZIPTheme {
        MainScreen()
    }
}
