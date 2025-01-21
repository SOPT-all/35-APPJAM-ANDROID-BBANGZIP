package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BadgeDetail(
    val name: String = "안녕",
    val categoryName: String = "안녕",
    val imageUrl: String = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
    val hashTags: List<String> = listOf("안녕","안녕"),
    val achievementCondition: String = "안녕",
    val reward: Int = 50,
    val isLocked: Boolean = true,
) : Parcelable
