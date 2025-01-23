package org.android.bbangzip.presentation.ui.my.mybadgecategory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.BadgeCategory
import org.android.bbangzip.presentation.model.BadgeDetail
import org.android.bbangzip.presentation.util.base.BaseContract

class MyBadgeCategoryContract {
    @Parcelize
    data class MyBadgeCategoryState(
        val badgeCategoryList1: List<BadgeCategory> =
            listOf(
                BadgeCategory(
                    name = "Badge 1",
                    categoryName = "Category 1",
                    isLocked = true,
                    imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
                ),
                BadgeCategory(
                    name = "Badge 2",
                    categoryName = "Category 1",
                    isLocked = false,
                    imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
                ),
                BadgeCategory(
                    name = "Badge 3",
                    categoryName = "Category 1",
                    isLocked = true,
                    imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
                ),
            ),
        val badgeCategoryList2: List<BadgeCategory> =
            listOf(
                BadgeCategory(
                    name = "Badge 4",
                    categoryName = "Category 2",
                    isLocked = true,
                    imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
                ),
                BadgeCategory(
                    name = "Badge 5",
                    categoryName = "Category 2",
                    isLocked = false,
                    imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
                ),
                BadgeCategory(
                    name = "Badge 6",
                    categoryName = "Category 2",
                    isLocked = true,
                    imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
                ),
            ),
        val badgeCategoryList3: List<BadgeCategory> = listOf(
            BadgeCategory(
                name = "Badge 4",
                categoryName = "Category 2",
                isLocked = true,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
            BadgeCategory(
                name = "Badge 5",
                categoryName = "Category 2",
                isLocked = false,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
            BadgeCategory(
                name = "Badge 6",
                categoryName = "Category 2",
                isLocked = true,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
        ),
        val badgeCategoryList4: List<BadgeCategory> = listOf(
            BadgeCategory(
                name = "Badge 4",
                categoryName = "Category 2",
                isLocked = true,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
            BadgeCategory(
                name = "Badge 5",
                categoryName = "Category 2",
                isLocked = false,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
            BadgeCategory(
                name = "Badge 6",
                categoryName = "Category 2",
                isLocked = true,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
        ),
        val badgeDetailBottomSheetState: Boolean = false,
        val badgeDetail: BadgeDetail = BadgeDetail(),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface MyBadgeCategoryEvent : BaseContract.Event {
        data object Initialize : MyBadgeCategoryEvent

        data object OnBackIconClicked : MyBadgeCategoryEvent

        data object OnBadgeDetailBottomSheetDismissButtonClicked : MyBadgeCategoryEvent

        data object OnBadgeDetailBottomSheetDismissRequest : MyBadgeCategoryEvent

        data class OnBadgeCardClicked(val badgeName: String) : MyBadgeCategoryEvent
    }

    sealed interface MyBadgeCategoryReduce : BaseContract.Reduce {
        data class UpdateBadgeCategoryList(
            val badgeCategoryList1: List<BadgeCategory>,
            val badgeCategoryList2: List<BadgeCategory>,
            val badgeCategoryList3: List<BadgeCategory>,
            val badgeCategoryList4: List<BadgeCategory>,
        ) : MyBadgeCategoryReduce

        data class UpdateBadgeDetailBottomSheetState(val badgeDetailBottomSheetState: Boolean) : MyBadgeCategoryReduce

        data class UpdateBadgeDetail(val badgeDetail: BadgeDetail) : MyBadgeCategoryReduce
    }

    sealed interface MyBadgeCategorySideEffect : BaseContract.SideEffect {
        data object NavigateToBack : MyBadgeCategorySideEffect
    }
}
