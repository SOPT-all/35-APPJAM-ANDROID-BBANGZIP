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
import org.android.bbangzip.presentation.ui.onboarding.navigation.navigateOnboarding
import org.android.bbangzip.presentation.ui.onboarding.navigation.navigateOnboardingEnd
import org.android.bbangzip.presentation.ui.onboarding.navigation.navigateOnboardingStart
import org.android.bbangzip.presentation.ui.onboarding.navigation.onboardingEndNavGraph
import org.android.bbangzip.presentation.ui.onboarding.navigation.onboardingNavGraph
import org.android.bbangzip.presentation.ui.onboarding.navigation.onboardingStartNavGraph
import org.android.bbangzip.presentation.ui.subject.addstudy.addStudyNavGraph
import org.android.bbangzip.presentation.ui.subject.navigateSubject
import org.android.bbangzip.presentation.ui.subject.splitstudy.navigateSplitStudy
import org.android.bbangzip.presentation.ui.subject.splitstudy.splitStudyNavGraph
import org.android.bbangzip.presentation.ui.subject.subjectNavGraph
import org.android.bbangzip.presentation.ui.todo.navigation.todoNavGraph
import org.android.bbangzip.presentation.ui.todo.pendingtodoadd.navigation.todoAddPendingNavGraph
import org.android.bbangzip.presentation.ui.todo.todoadd.navigation.todoAddNavGraph
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
                    navigator.navHostController.navigateOnboardingStart()
                },
            )

            onboardingStartNavGraph(
                navigateToOnboarding = { navigator.navHostController.navigateOnboarding() },
            )

            onboardingNavGraph(
                navigateToOnboardingEnd = { navigator.navHostController.navigateOnboardingEnd() },
            )

            onboardingEndNavGraph(
                navigateToSubject = { navigator.navHostController.navigateSubject() },
            )

            todoAddNavGraph(
                snackBarHostState = snackBarHostState,
                navigateToBack = { navigator.popBackStackIfNotSubject() },
                navigateToToDo = { navigator.popBackStackIfNotSubject() },
            )

            todoAddPendingNavGraph(
                snackBarHostState = snackBarHostState,
                navigateToBack = { navigator.popBackStackIfNotSubject() },
                navigateToToDo = { navigator.popBackStackIfNotSubject() },
            )

            friendNavGraph()

            myNavGraph()

            subjectNavGraph()

            addStudyNavGraph(
                padding = padding,
                navigateSplitStudy = {navigator.navHostController.navigateSplitStudy(it)}
            )

            splitStudyNavGraph()

            todoNavGraph(
                snackBarHostState = snackBarHostState,
                bottomPadding = padding,
                navigateToAddToDo = { navigator.navigateToToDoAdd() },
                navigateToAddPendingToDo = { navigator.navigateToToDoAddPending() },
            )
        }
    }
}
