package org.android.bbangzip.presentation.ui.my.mybadgecategory

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyBadgeCategoryViewModel
    @Inject
    constructor(
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
                    // 서버에서 get하거 bottomsheet 띄우기
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

        private fun initData() {
        }
    }
