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
            listOf(),
        val badgeCategoryList2: List<BadgeCategory> =
            listOf(),
        val badgeCategoryList3: List<BadgeCategory> =
            listOf(),
        val badgeCategoryList4: List<BadgeCategory> =
            listOf(),
        val badgeDetailBottomSheetState: Boolean = false,
        val badgeDetail: BadgeDetail = BadgeDetail(),
        val nickname: String = "",
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

        data class UpdateNickName(val nickname: String) : MyBadgeCategoryReduce
    }

    sealed interface MyBadgeCategorySideEffect : BaseContract.SideEffect {
        data object NavigateToBack : MyBadgeCategorySideEffect
    }
}
