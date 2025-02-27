package org.android.bbangzip.presentation.ui.my.mybadgecategory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.component.indicator.BbangZipLoadingIndicator

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
                onBackIconClick = {
                    viewModel.setEvent(MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBackIconClick)
                },
                onBadgeCardClick = { badgeName ->
                    viewModel.setEvent(
                        MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeCardClick(
                            badgeName = badgeName,
                        ),
                    )
                },
                onBadgeDetailBottomSheetDismissRequest = {
                    viewModel.setEvent(MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeDetailBottomSheetDismissRequest)
                },
                onBadgeDetailBottomSheetDismissBtnClick = {
                    viewModel.setEvent(MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeDetailBottomSheetDismissBtnClick)
                },
            )

        false ->
            BbangZipLoadingIndicator()
    }
}
