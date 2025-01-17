package org.android.bbangzip.presentation.ui.dummy

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.presentation.ui.dummy.DummyContract.DummyState
import timber.log.Timber

@Composable
fun DummyScreen(
    dummyState: DummyState,
    onClickNextBtn: (String) -> Unit = {},
    viewModel: DummyViewModel = hiltViewModel(),
) {
    val userPreferences by viewModel.userPreferencesFlow.collectAsStateWithLifecycle(initialValue = UserPreferences.getDefaultInstance())

    // Timber로 Access Token 로그 출력
    LaunchedEffect(userPreferences) {
        Timber.d("[Access Token]: ${userPreferences.accessToken}")
    }

    Text(
        text = "안녕? 클릭하면 액세스 토큰 등록한다",
        modifier =
            Modifier
                .clickable { viewModel.setUserData("newAccessToken") },
    )

    Text(
        text = "이거 클릭하면 액세스 토큰 사라진다!",
        modifier =
            Modifier
                .clickable { viewModel.clearAccessToken() },
    )
}
