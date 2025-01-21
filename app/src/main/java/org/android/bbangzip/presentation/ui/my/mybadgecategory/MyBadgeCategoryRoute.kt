package org.android.bbangzip.presentation.ui.my.mybadgecategory

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MyBadgeCategoryRoute(
    navigateToBack: () -> Unit = {},
    viewModel: MyBadgeCategoryViewModel = hiltViewModel(),
) {
    val badgeCategoryState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)

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