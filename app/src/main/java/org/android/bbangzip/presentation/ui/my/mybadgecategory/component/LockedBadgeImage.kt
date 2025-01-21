package org.android.bbangzip.presentation.ui.my.mybadgecategory.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.android.bbangzip.R
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun LockedBadgeImage(
    imgUrl: String, modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imgUrl,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(24.dp))
            .blur(24.dp),
    )

    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock_default_28),
        contentDescription = null,
        tint = BbangZipTheme.colors.staticWhite_FFFFFF
    )
}

