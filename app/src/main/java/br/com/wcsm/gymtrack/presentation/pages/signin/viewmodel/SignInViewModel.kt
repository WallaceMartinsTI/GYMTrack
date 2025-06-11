package br.com.wcsm.gymtrack.presentation.pages.signin.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.usecase.signin.SignInUseCase
import br.com.wcsm.gymtrack.presentation.model.BaseFlowViewModel
import br.com.wcsm.gymtrack.utils.UnitCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : BaseFlowViewModel<SignInState, SignInUiState>(
    initialUiState = SignInUiState()
) {
    fun handleEmail(email: String) = _uiState.update { state -> state.copy(email = email) }

    fun handlePassword(password: String) = _uiState.update { state -> state.copy(password = password) }

    fun handleKeepLogin(keepLogin: Boolean) = _uiState.update { state -> state.copy(keepLogin = keepLogin) }

    fun handleSignUp() = emitState(SignInState.GoToSignUp)

    private fun handleHideError() = _uiState.update { state -> state.copy(error = null) }

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

    fun signIn() {
        handleHideError()

        viewModelScope.launch(Dispatchers.IO) {
            if(isSignInValid()) {
                signInUseCase(
                    email = uiState.value.email,
                    password = uiState.value.password
                ).collect { result ->
                    when(result) {
                        is BaseResponse.Loading -> onLoadingResponse()
                        is BaseResponse.Error -> onErrorResponse(result.errorMessage)
                        is BaseResponse.Success -> onSuccessResponse {
                            emitState(SignInState.GoToHome)
                        }
                    }
                }
            }
        }
    }

    private fun isSignInValid(): Boolean {
        val isEmailValid = validateEmail(uiState.value.email)
        val isPasswordValid = validatePassword(uiState.value.password)

        _uiState.update { state -> state.copy(
            emailErrorMessage = isEmailValid.second,
            passwordErrorMessage = isPasswordValid.second
        ) }

        return isEmailValid.first && isPasswordValid.first
    }

    private fun validateEmail(email: String): Pair<Boolean, String> {
        return when {
            email.isBlank() -> false to "Você deve informar seu e-mail."
            else -> true to ""
        }
    }

    private fun validatePassword(password: String): Pair<Boolean, String> {
        return when {
            password.isBlank() -> false to "Você deve inforar sua senha."
            else -> true to ""
        }
    }
}