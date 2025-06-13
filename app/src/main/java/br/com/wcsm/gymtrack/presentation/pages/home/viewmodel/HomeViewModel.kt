package br.com.wcsm.gymtrack.presentation.pages.home.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.usecase.authentication.GetCurrentUserUseCase
import br.com.wcsm.gymtrack.domain.usecase.authentication.SignOutUseCase
import br.com.wcsm.gymtrack.presentation.model.BaseFlowViewModel
import br.com.wcsm.gymtrack.utils.UnitCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,

    private val signOutUseCase: SignOutUseCase
) : BaseFlowViewModel<HomeState, HomeUiState>(
    initialUiState = HomeUiState()
) {
    fun loadPage() {
        getCurrentUser()
    }

    fun handleSignOut() = emitState(HomeState.ShowSignOutDialog)

    fun resetErrorMessage() = _uiState.update { state -> state.copy(error = null) }

    private fun onLoadingResponse() = _uiState.update { state -> state.copy(isLoading = true) }

    private fun onErrorResponse(errorMessage: String) = _uiState.update { state ->
        state.copy(
            isLoading = false,
            error = errorMessage
        )
    }

    private fun onSuccessResponse(onSuccessCallback: UnitCallback) {
        onSuccessCallback()

        _uiState.update { state ->
            state.copy(
                isLoading = false
            )
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            signOutUseCase().collect { result ->
                when(result) {
                    is BaseResponse.Loading -> onLoadingResponse()
                    is BaseResponse.Error -> onErrorResponse(result.errorMessage)
                    is BaseResponse.Success -> onSuccessResponse {
                        _uiState.update { state -> state.copy(currentUserId = null) }
                        emitState(HomeState.SignOutUser)
                    }
                }
            }
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentUserUseCase().collect { result ->
                when(result) {
                    is BaseResponse.Loading -> onLoadingResponse()
                    is BaseResponse.Error -> onErrorResponse(result.errorMessage)
                    is BaseResponse.Success -> onSuccessResponse {
                        val userId = result.data

                        _uiState.update { state -> state.copy(
                            currentUserId = userId
                        ) }

                        emitState(HomeState.CurrentUserAvailable)
                    }
                }
            }
        }
    }
}