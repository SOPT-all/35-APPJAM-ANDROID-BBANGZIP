package org.android.bbangzip.presentation.ui.navigator

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.model.Route
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.presentation.type.BottomNavigationType
import org.android.bbangzip.presentation.ui.friend.navigation.navigateFriend
import org.android.bbangzip.presentation.ui.login.LoginRoute
import org.android.bbangzip.presentation.ui.login.navigateLogin
import org.android.bbangzip.presentation.ui.my.bbangzipdetail.navigation.navigateBbangZipDetail
import org.android.bbangzip.presentation.ui.my.mybadgecategory.navigation.navigateToMyBadgeCategory
import org.android.bbangzip.presentation.ui.my.navigation.navigateMy
import org.android.bbangzip.presentation.ui.onboarding.navigation.navigateOnboarding
import org.android.bbangzip.presentation.ui.onboarding.navigation.navigateOnboardingEnd
import org.android.bbangzip.presentation.ui.onboarding.navigation.navigateOnboardingStart
import org.android.bbangzip.presentation.ui.subject.addstudy.AddStudyRoute
import org.android.bbangzip.presentation.ui.subject.addstudy.navigateAddStudy
import org.android.bbangzip.presentation.ui.subject.addsubject.AddSubjectRoute
import org.android.bbangzip.presentation.ui.subject.addsubject.navigateAddSubject
import org.android.bbangzip.presentation.ui.subject.modify.motivationmessage.ModifyMotivationMessageRoute
import org.android.bbangzip.presentation.ui.subject.modify.subjectname.ModifySubjectNameRoute
import org.android.bbangzip.presentation.ui.subject.navigateSubject
import org.android.bbangzip.presentation.ui.subject.splitstudy.navigateSplitStudy
import org.android.bbangzip.presentation.ui.subject.subjectdetail.navigateSubjectDetail
import org.android.bbangzip.presentation.ui.todo.navigation.navigateTodo
import org.android.bbangzip.presentation.ui.todo.pendingtodoadd.navigation.navigateTodoAddPending
import org.android.bbangzip.presentation.ui.todo.todoadd.navigation.navigateTodoAdd
import timber.log.Timber

class MainNavigator(
    val navHostController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navHostController.currentBackStackEntryAsState().value?.destination

    val startDestination = ModifyMotivationMessageRoute

    val currentBottomNavigationBarItem: BottomNavigationType?
        @Composable get() =
            BottomNavigationType.find { mainBottomNavigationRoute ->
                currentDestination?.route == mainBottomNavigationRoute::class.qualifiedName
            }

    @SuppressLint("RestrictedApi")
    fun navigateBottomNavigation(bottomNavigationType: BottomNavigationType) {
        Timber.d("[navigation] currentDestination -> ${navHostController.currentDestination}")
        navOptions {
            popUpTo(BottomNavigationRoute.Subject::class.qualifiedName.orEmpty()) {
                saveState = true
                Timber.d("[navigation] restoreState -> $saveState")
            }
            launchSingleTop = true
            restoreState = true
            Timber.d("[navigation] restoreState -> $restoreState")
        }.let { navOptions ->
            when (bottomNavigationType) {
                BottomNavigationType.SUBJECT -> navHostController.navigateSubject()
                BottomNavigationType.TODO -> navHostController.navigateTodo(navOptions)
                BottomNavigationType.FRIEND -> navHostController.navigateFriend(navOptions)
                BottomNavigationType.MY -> navHostController.navigateMy(navOptions)
                else -> Unit
            }
            Timber.d("[navigation] currentDestination -> ${navHostController.currentDestination}")

            Timber.d("[navigation] currentBackStack -> ${navHostController.currentBackStack.value}")
        }
    }

    fun navigateToLogin() {
        navHostController.navigateLogin()
    }

    fun navigateToSubject() {
        navHostController.navigateSubject()
    }

    fun navigateToSubjectDetail(){
        navHostController.navigateSubjectDetail()
    }

    fun navigateToAddStudy(splitStudyData: SplitStudyData){
        navHostController.navigateAddStudy(splitStudyData = splitStudyData)
    }

    fun navigateToSplitStudy(addStudyData: AddStudyData){
        navHostController.navigateSplitStudy(addStudyData = addStudyData)
    }

    fun navigateToOnboardingStart() {
        navHostController.navigateOnboardingStart()
    }

    fun navigateToMyBadgeCategory() {
        navHostController.navigateToMyBadgeCategory()
    }

    fun navigateToOnboarding() {
        navHostController.navigateOnboarding()
    }

    fun navigateToOnboardingEnd() {
        navHostController.navigateOnboardingEnd()
    }

    fun navigateToToDoAdd() {
        navHostController.navigateTodoAdd()
    }

    fun navigateToToDoAddPending() {
        navHostController.navigateTodoAddPending()
    }

    fun navigateToBbangZipDetail() {
        navHostController.navigateBbangZipDetail()
    }

    private fun popBackStack() {
        navHostController.popBackStack()
    }

    fun popBackStackIfNotSubject() {
        if (!isSameCurrentDestination<BottomNavigationRoute.Subject>()) {
            popBackStack()
        }
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navHostController.currentDestination?.route == T::class.qualifiedName

    @Composable
    fun showBottomBar(): Boolean =
        BottomNavigationType.any {
            currentDestination?.route == it::class.qualifiedName
        }
}

@Composable
fun rememberMainNavigator(
    navHostController: NavHostController = rememberNavController(),
): MainNavigator =
    remember(navHostController) {
        MainNavigator(navHostController = navHostController)
    }
