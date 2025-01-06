package org.android.bbangzip.presentation.ui.dummy

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DummyRoute(
    snackbarHostState: SnackbarHostState,
    viewModel: DummyViewModel = hiltViewModel(),
) {
    val dummyState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.loading.collectAsStateWithLifecycle(false)

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is DummyContract.DummySideEffect.ShowSnackBar -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    when (success) {
        true ->
            DummyScreen(
                dummyState = dummyState,
                onClickNextBtn = { dummy ->
                    viewModel.setEvent(DummyContract.DummyEvent.OnClickNextBtn(dummy = dummy))
                },
            )
        else -> {
            // TODO 로딩화면
        }
    }
}
