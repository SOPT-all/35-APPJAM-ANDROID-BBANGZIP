package org.android.bbangzip.presentation.ui.friend

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun FriendRoute(
    viewModel: FriendViewModel = hiltViewModel(),
) {
    val friendState by viewModel.uiState.collectAsStateWithLifecycle()
    val view = LocalView.current
    val activity = view.context as Activity

    activity.window.statusBarColor = BbangZipTheme.colors.backgroundAccent_FFDAA0.toArgb()

    FriendScreen(friendState = friendState)
}
