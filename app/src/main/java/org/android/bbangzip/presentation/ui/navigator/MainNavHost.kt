package org.android.bbangzip.presentation.ui.navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.android.bbangzip.presentation.ui.friend.friendNavGraph
import org.android.bbangzip.presentation.ui.login.loginNavGraph
import org.android.bbangzip.presentation.ui.my.myNavGraph
import org.android.bbangzip.presentation.ui.subject.navigateSubject
import org.android.bbangzip.presentation.ui.subject.subjectNavGraph
import org.android.bbangzip.presentation.ui.todo.todoNavGraph
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
    snackBarHostState: SnackbarHostState,
) {
    Box(
        modifier =
            modifier
                .padding(top = padding.calculateTopPadding())
                .fillMaxSize()
                .background(BbangZipTheme.colors.backgroundNormal_FFFFFF),
    ) {
        NavHost(
            navController = navigator.navHostController,
            startDestination = navigator.startDestination,
        ) {
            loginNavGraph(
                navigateToSubject = { navigator.navHostController.navigateSubject() },
                navigateToOnboarding = {
                    navigator.navHostController.navigateSubject()
                    // TODO onboarding 만들어서 넣기
                }
            )

            friendNavGraph()

            myNavGraph()

            subjectNavGraph()

            todoNavGraph()
        }
    }
}
