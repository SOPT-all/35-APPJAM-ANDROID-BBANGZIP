package org.android.bbangzip.presentation.ui.my.mybadgecategory

import android.app.Activity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun MyBadgeCategoryRoute(
    navigateToBack: () -> Unit = {},
    viewModel: MyBadgeCategoryViewModel = hiltViewModel(),
) {
    val badgeCategoryState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)
    val view = LocalView.current
    val activity = view.context as Activity

    activity.window.statusBarColor = BbangZipTheme.colors.backgroundAccent_FFDAA0.toArgb()

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                MyBadgeCategoryContract.MyBadgeCategorySideEffect.NavigateToBack ->
                    navigateToBack()
            }
        }
    }

    when (success) {
        true ->
            MyBadgeCategoryScreen(
                badgeCategoryState = badgeCategoryState,
                onBackIconClicked = {
                    viewModel.setEvent(MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBackIconClicked)
                },
                onBadgeCardClicked = { badgeName ->
                    viewModel.setEvent(
                        MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeCardClicked(
                            badgeName = badgeName
                        )
                    )
                },
                onBadgeDetailBottomSheetDismissRequest = {
                    viewModel.setEvent(MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeDetailBottomSheetDismissRequest)
                },
                onBadgeDetailBottomSheetDismissButtonClicked = {
                    viewModel.setEvent(MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeDetailBottomSheetDismissButtonClicked)
                }
            )

        false ->
            Text("땡!")
    }
}