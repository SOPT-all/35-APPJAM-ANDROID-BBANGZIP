package org.android.bbangzip.presentation.ui.dummy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import org.android.bbangzip.domain.usecase.FetchDummyUseCase
import org.android.bbangzip.presentation.model.Dummy
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DummyViewModel
    @Inject
    constructor(
        private val userLocalRepository: UserLocalRepository,
        private val fetchDummyUseCase: FetchDummyUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<DummyContract.DummyEvent, DummyContract.DummyState, DummyContract.DummyReduce, DummyContract.DummySideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        val userPreferencesFlow: Flow<UserPreferences> = userLocalRepository.userPreferenceFlow

        fun setUserData(accessToken: String) {
            viewModelScope.launch { userLocalRepository.setAccessToken(accessToken) }
        }

        fun clearAccessToken() {
            viewModelScope.launch {
                userLocalRepository.clearAccessToken()
            }
        }

        override fun createInitialState(savedState: Parcelable?): DummyContract.DummyState {
            return savedState as? DummyContract.DummyState ?: DummyContract.DummyState()
        }

        init {
            setEvent(DummyContract.DummyEvent.Initialize)
        }

        override fun handleEvent(event: DummyContract.DummyEvent) {
            when (event) {
                is DummyContract.DummyEvent.Initialize -> launch { initDataLoad() }
                is DummyContract.DummyEvent.OnClickNextBtn -> {
                    setSideEffect(DummyContract.DummySideEffect.ShowSnackBar("메세지 전송"))
                    updateState(DummyContract.DummyReduce.UpdateDummy(dummy = Dummy(dummyA = "", dummyB = "")))
                }
            }
        }

        override fun reduceState(
            state: DummyContract.DummyState,
            reduce: DummyContract.DummyReduce,
        ): DummyContract.DummyState {
            return when (reduce) {
                is DummyContract.DummyReduce.UpdateState -> reduce.state
                is DummyContract.DummyReduce.UpdateLoading -> state.copy(loading = reduce.loading)
                is DummyContract.DummyReduce.UpdateDummy -> state.copy(dummy = Dummy(dummyA = "", dummyB = ""))
            }
        }

        private suspend fun initDataLoad() {
            fetchDummy()
            fetchDummy2()
        }

        private suspend fun fetchDummy() {
            fetchDummyUseCase()
                .onSuccess { data ->
                    updateState(
                        DummyContract.DummyReduce.UpdateDummy(
                            Dummy(
                                dummyA = "data.dummyName",
                                dummyB = "data.dummyName" + "이런 식으로 넣어요",
                            ),
                        ),
                    )
                }.onFailure {
                    setSideEffect(DummyContract.DummySideEffect.ShowSnackBar("오류남"))
                }
        }

        private suspend fun fetchDummy2() { // fetchDummy()와 다른 API라고 가정
            fetchDummyUseCase().onSuccess { data ->
                updateState(
                    DummyContract.DummyReduce.UpdateDummy(
                        Dummy(
                            dummyA = "data!!dummyName,",
                            dummyB = data!!.dummyName + "이런 식으로 넣어요",
                        ),
                    ),
                )
            }.onFailure {
                setSideEffect(DummyContract.DummySideEffect.ShowSnackBar("오류남"))
            }
        }
    }
