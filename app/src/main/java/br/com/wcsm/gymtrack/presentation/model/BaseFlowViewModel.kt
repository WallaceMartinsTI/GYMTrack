package br.com.wcsm.gymtrack.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseFlowViewModel<State, UiState>(
    initialUiState: UiState
) : ViewModel() {
    protected val _state = MutableSharedFlow<State>()
    val state: SharedFlow<State> = _state.asSharedFlow()

    protected val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    protected fun emitState(state: State) {
        viewModelScope.launch { _state.emit(state) }
    }
}