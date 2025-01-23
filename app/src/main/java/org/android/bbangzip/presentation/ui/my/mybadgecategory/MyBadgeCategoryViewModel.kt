package org.android.bbangzip.presentation.ui.my.mybadgecategory

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.domain.usecase.GetBadgeCategoryListUseCase
import org.android.bbangzip.domain.usecase.GetBadgeDetailUseCase
import org.android.bbangzip.presentation.model.BadgeCategory
import org.android.bbangzip.presentation.model.BadgeDetail
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyBadgeCategoryViewModel
@Inject
constructor(
    private val getBadgeCategoryListUseCase: GetBadgeCategoryListUseCase,
    private val getBadgeDetailUseCase: GetBadgeDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<MyBadgeCategoryContract.MyBadgeCategoryEvent, MyBadgeCategoryContract.MyBadgeCategoryState, MyBadgeCategoryContract.MyBadgeCategoryReduce, MyBadgeCategoryContract.MyBadgeCategorySideEffect>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedState: Parcelable?): MyBadgeCategoryContract.MyBadgeCategoryState {
        return savedState as? MyBadgeCategoryContract.MyBadgeCategoryState
            ?: MyBadgeCategoryContract.MyBadgeCategoryState()
    }

    init {
        setEvent(MyBadgeCategoryContract.MyBadgeCategoryEvent.Initialize)
    }

    override fun handleEvent(event: MyBadgeCategoryContract.MyBadgeCategoryEvent) {
        when (event) {
            MyBadgeCategoryContract.MyBadgeCategoryEvent.Initialize -> launch { initData() }
            MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBackIconClicked ->
                setSideEffect(MyBadgeCategoryContract.MyBadgeCategorySideEffect.NavigateToBack)

            is MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeCardClicked -> {
                viewModelScope.launch { getBadgeDetail(event.badgeName) }
                updateState(
                    MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeDetailBottomSheetState(
                        badgeDetailBottomSheetState = true,
                    ),
                )
            }

            MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeDetailBottomSheetDismissButtonClicked ->
                updateState(
                    MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeDetailBottomSheetState(
                        badgeDetailBottomSheetState = false,
                    ),
                )

            MyBadgeCategoryContract.MyBadgeCategoryEvent.OnBadgeDetailBottomSheetDismissRequest ->
                updateState(
                    MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeDetailBottomSheetState(
                        badgeDetailBottomSheetState = false,
                    ),
                )
        }
    }

    override fun reduceState(
        state: MyBadgeCategoryContract.MyBadgeCategoryState,
        reduce: MyBadgeCategoryContract.MyBadgeCategoryReduce,
    ): MyBadgeCategoryContract.MyBadgeCategoryState {
        return when (reduce) {
            is MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeCategoryList ->
                state.copy(
                    badgeCategoryList1 = reduce.badgeCategoryList1,
                    badgeCategoryList2 = reduce.badgeCategoryList2,
                    badgeCategoryList3 = reduce.badgeCategoryList3,
                    badgeCategoryList4 = reduce.badgeCategoryList4,
                )

            is MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeDetail ->
                state.copy(
                    badgeDetail = reduce.badgeDetail,
                )

            is MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeDetailBottomSheetState ->
                state.copy(
                    badgeDetailBottomSheetState = reduce.badgeDetailBottomSheetState,
                )
        }
    }

    private suspend fun initData() {
        getBadgeCategoryList()
    }

    private suspend fun getBadgeCategoryList() {
        getBadgeCategoryListUseCase()
            .onSuccess { data ->
                val badgeLists =
                    data.badgeList.map { badge ->
                        BadgeCategory(
                            name = badge.badgeName,
                            categoryName = badge.badgeCategory,
                            isLocked = badge.badgeIsLocked,
                            imageUrl = badge.badgeImage,
                        )
                    }
                val badgeCategoryList1 =
                    badgeLists.filter { badge ->
                        badge.categoryName == "시작이 빵이다"
                    }
                val badgeCategoryList2 =
                    badgeLists.filter { badge ->
                        badge.categoryName == "미룬이 탈출"
                    }
                val badgeCategoryList3 =
                    badgeLists.filter { badge ->
                        badge.categoryName == "미룬이 겨우 탈출"
                    }
                val badgeCategoryList4 =
                    badgeLists.filter { badge ->
                        badge.categoryName == "인싸 사장님"
                    }

                updateState(
                    MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeCategoryList(
                        badgeCategoryList1 = badgeCategoryList1,
                        badgeCategoryList2 = badgeCategoryList2,
                        badgeCategoryList3 = badgeCategoryList3,
                        badgeCategoryList4 = badgeCategoryList4,
                    ),
                )
            }.onFailure { error ->
                Timber.tag("badges").e(error)
            }
    }

    private suspend fun getBadgeDetail(badgeName: String) {
        getBadgeDetailUseCase(badgeName)
            .onSuccess { data ->
                updateState(
                    MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeDetail(
                        badgeDetail =
                        BadgeDetail(
                            categoryName = data.badgeName,
                            imageUrl = data.badgeImage,
                            hashTags = data.hashTags,
                            achievementCondition = data.achievementCondition,
                            reward = data.reward,
                            isLocked = data.badgeIsLocked,
                        ),
                    ),
                )
                updateState(
                    MyBadgeCategoryContract.MyBadgeCategoryReduce.UpdateBadgeDetailBottomSheetState(
                        badgeDetailBottomSheetState = true,
                    ),
                )
            }
            .onFailure { error ->
                Timber.tag("badges").e(error)
            }
    }
}
