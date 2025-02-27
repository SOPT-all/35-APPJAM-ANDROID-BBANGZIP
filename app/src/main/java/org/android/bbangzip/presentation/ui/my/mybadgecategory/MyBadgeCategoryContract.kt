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
            emptyList(),
        val badgeCategoryList2: List<BadgeCategory> =
            emptyList(),
        val badgeCategoryList3: List<BadgeCategory> =
            emptyList(),
        val badgeCategoryList4: List<BadgeCategory> =
            emptyList(),
        val isBadgeDetailBottomSheetVisible: Boolean = false,
        val badgeDetail: BadgeDetail = BadgeDetail(),
        val nickname: String = "",
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface MyBadgeCategoryEvent : BaseContract.Event {
        data object Initialize : MyBadgeCategoryEvent

        data object OnBackIconClick : MyBadgeCategoryEvent

        data object OnBadgeDetailBottomSheetDismissBtnClick : MyBadgeCategoryEvent

        data object OnBadgeDetailBottomSheetDismissRequest : MyBadgeCategoryEvent

        data class OnBadgeCardClick(val badgeName: String) : MyBadgeCategoryEvent
    }

    sealed interface MyBadgeCategoryReduce : BaseContract.Reduce {
        data class UpdateBadgeCategoryList(
            val badgeCategoryList1: List<BadgeCategory>,
            val badgeCategoryList2: List<BadgeCategory>,
            val badgeCategoryList3: List<BadgeCategory>,
            val badgeCategoryList4: List<BadgeCategory>,
        ) : MyBadgeCategoryReduce

        data class UpdateBadgeDetailBottomSheetState(val isBadgeDetailBottomSheetVisible: Boolean) : MyBadgeCategoryReduce

        data class UpdateBadgeDetail(val badgeDetail: BadgeDetail) : MyBadgeCategoryReduce

        data class UpdateNickName(val nickname: String) : MyBadgeCategoryReduce
    }

    sealed interface MyBadgeCategorySideEffect : BaseContract.SideEffect {
        data object NavigateToBack : MyBadgeCategorySideEffect
    }
}
