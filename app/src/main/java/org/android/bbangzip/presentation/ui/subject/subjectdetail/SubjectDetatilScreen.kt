package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar

@Composable
fun SubjectDetailScreen(
    padding: PaddingValues,
){
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val bottomBarPadding = padding.calculateBottomPadding()
    val backgroundHeight = (screenHeightDp * 0.32).toInt()

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        BbangZipBaseTopBar()
    }
}