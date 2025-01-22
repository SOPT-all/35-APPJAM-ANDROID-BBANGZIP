package org.android.bbangzip.presentation.ui.my.bbangzipdetail

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BbangZipDetailRoute(
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BbangZipDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { state.bbangZipList.size })

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is BbangZipDetailContract.BbangZipDetailSideEffect.PopBackStack -> popBackStack()
            }
        }
    }

    BbangZipDetailScreen(
        modifier = modifier,
        state = state,
        pagerState = pagerState,
        popBackStack = popBackStack
    )
}