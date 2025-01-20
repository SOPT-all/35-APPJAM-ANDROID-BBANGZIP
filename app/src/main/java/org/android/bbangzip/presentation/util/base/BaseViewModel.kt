package org.android.bbangzip.presentation.util.base

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : BaseContract.Event, State : BaseContract.State, Reduce : BaseContract.Reduce, Effect : BaseContract.SideEffect>(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val initialState: State by lazy { createInitialState(savedStateHandle[STATE_KEY]) }

    abstract fun createInitialState(savedState: Parcelable?): State

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()
    protected val currentUiState: State get() = _uiState.value

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()
    val uiEvent: SharedFlow<Event> get() = _uiEvent.asSharedFlow()

    private val _reducer: MutableSharedFlow<Reduce> = MutableSharedFlow()
    val reducer: SharedFlow<Reduce> get() = _reducer.asSharedFlow()

    private val _uiSideEffect: Channel<Effect> = Channel()
    val uiSideEffect = _uiSideEffect.receiveAsFlow()

    val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            setLoading(false)
            setError(throwable.message ?: "Unknown error")
        }

    inline fun launch(crossinline action: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(coroutineExceptionHandler) {
            setLoading(true)
            runCatching {
                action(this)
            }.onSuccess {
                setSuccess(true)
            }.also {
                setLoading(false)
            }
        }

    private val _success: Channel<Boolean> = Channel()
    val success = _success.receiveAsFlow()

    fun setSuccess(success: Boolean = false) {
        viewModelScope.launch {
            _success.send(success)
        }
    }

    private val _loading: Channel<Boolean> = Channel()
    val loading = _loading.receiveAsFlow()

    fun setLoading(loading: Boolean) {
        viewModelScope.launch {
            _loading.send(loading)
        }
    }

    private val _error: Channel<String> = Channel()
    val error = _error.receiveAsFlow()

    private fun setError(error: String) {
        viewModelScope.launch {
            _error.send(error)
        }
    }

    init {
        _uiEvent.onEach(::handleEvent)
            .launchIn(viewModelScope)

        _reducer.onEach { reducer ->
            _uiState.value =
                reduceState(currentUiState, reducer).also { state ->
                    savedStateHandle[STATE_KEY] = state.toParcelable()
                }
        }.launchIn(viewModelScope)
    }

    protected fun updateState(
        reduce: Reduce,
        onComplete: (() -> Unit)? = null,
    ) {
        viewModelScope.launch {
            _reducer.emit(reduce)
            onComplete?.invoke()
        }
    }

    protected fun setSideEffect(effect: Effect) {
        viewModelScope.launch {
            _uiSideEffect.send(effect)
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

    abstract fun handleEvent(event: Event)

    abstract fun reduceState(
        state: State,
        reduce: Reduce,
    ): State

    companion object {
        private const val STATE_KEY = "viewState"
    }
}
