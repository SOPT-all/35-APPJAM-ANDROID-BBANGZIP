package org.android.bbangzip.presentation.ui.navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.android.bbangzip.presentation.ui.friend.navigation.friendNavGraph
import org.android.bbangzip.presentation.ui.login.loginNavGraph
import org.android.bbangzip.presentation.ui.my.bbangzipdetail.navigation.bbangZipDetailNavGraph
import org.android.bbangzip.presentation.ui.my.mybadgecategory.navigation.myBadgeCategoryNavGraph
import org.android.bbangzip.presentation.ui.my.navigation.myNavGraph
import org.android.bbangzip.presentation.ui.onboarding.navigation.onboardingEndNavGraph
import org.android.bbangzip.presentation.ui.onboarding.navigation.onboardingNavGraph
import org.android.bbangzip.presentation.ui.onboarding.navigation.onboardingStartNavGraph
import org.android.bbangzip.presentation.ui.splash.navigation.splashNavGraph
import org.android.bbangzip.presentation.ui.subject.addstudy.addStudyNavGraph
import org.android.bbangzip.presentation.ui.subject.addstudy.navigateAddStudy
import org.android.bbangzip.presentation.ui.subject.addsubject.addSubjectNavGraph
import org.android.bbangzip.presentation.ui.subject.modify.motivationmessage.modifyMotivationMessageNavGraph
import org.android.bbangzip.presentation.ui.subject.modify.subjectname.modifySubjectNameNavGraph
import org.android.bbangzip.presentation.ui.subject.splitstudy.navigateSplitStudy
import org.android.bbangzip.presentation.ui.subject.splitstudy.splitStudyNavGraph
import org.android.bbangzip.presentation.ui.subject.subjectNavGraph
import org.android.bbangzip.presentation.ui.subject.subjectdetail.subjectDetailNavGraph
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
            splashNavGraph(
                navigateToLogin = { navigator.navigateToLogin() },
                navigateToOnboardingStart = { navigator.navigateToOnboardingStart() },
                navigateToSubject = { navigator.navigateToSubject() },
            )

            loginNavGraph(
                navigateToSubject = { navigator.navigateToSubject() },
                navigateToOnboarding = {
                    navigator.navigateToOnboardingStart()
                },
            )

            onboardingStartNavGraph(
                navigateToOnboarding = { navigator.navigateToOnboarding() },
            )

            onboardingNavGraph(
                navigateToOnboardingEnd = { navigator.navigateToOnboardingEnd() },
            )

            onboardingEndNavGraph(
                navigateToSubject = { navigator.navigateToSubject() },
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

            myNavGraph(
                padding = padding,
                navigateToBbangZipDetail = { navigator.navigateToBbangZipDetail() },
                navigateToLogin = { navigator.navigateToLogin() },
                navigateToBadgeDetail = { navigator.navigateToMyBadgeCategory() },
            )

            bbangZipDetailNavGraph(
                popBackStack = { navigator.popBackStackIfNotSubject() },
            )

            myBadgeCategoryNavGraph(
                popBackStack = { navigator.popBackStackIfNotSubject() },
            )

            subjectNavGraph(
                navigateAddStudy = { navigator.navigateToAddStudy(it) },
                navigateToSubjectDetail = { id, name -> navigator.navigateToSubjectDetail(id, name) },
                navigateToAddSubject = { navigator.navigateToAddSubject() },
                padding = padding,
            )

            addStudyNavGraph(
                padding = padding,
                popBackStack = { navigator.popBackStackIfNotSubject() },
                navigateSplitStudy = { navigator.navigateToSplitStudy(it) },
            )

            modifySubjectNameNavGraph(
                navigateToSubjectDetail = { id, name -> navigator.navigateToSubjectDetail(id, name) },
            )

            modifyMotivationMessageNavGraph(
                navigateToSubjectDetail = { id, name -> navigator.navigateToSubjectDetail(id, name) },
                snackbarHostState = snackBarHostState,
            )

            addSubjectNavGraph(
                navigateSubject = { navigator.navigateToSubject() },
            )

            splitStudyNavGraph(
                navigateBack = { navigator.popBackStackIfNotSubject() },
                navigateAddStudy = { navigator.navigateToAddStudy(it) },
            )

            todoNavGraph(
                snackBarHostState = snackBarHostState,
                bottomPadding = padding,
                navigateToAddToDo = { navigator.navigateToToDoAdd() },
                navigateToAddPendingToDo = { navigator.navigateToToDoAddPending() },
            )
            subjectDetailNavGraph(
                padding = padding,
                navigateToBack = { navigator.popBackStackIfNotSubject() },
                navigateToModifyMotivation = { id, name -> navigator.navigateToModifyMotivationMessage(id, name) },
                navigateToModifySubjectName = { id, name -> navigator.navigateToModifySubjectName(id, name) },
            )

            todoNavGraph(
                snackBarHostState = snackBarHostState,
                bottomPadding = padding,
                navigateToAddToDo = { navigator.navigateToToDoAdd() },
                navigateToAddPendingToDo = { navigator.navigateToToDoAddPending() },
            )
        }
    }
}
