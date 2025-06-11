package br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.usecase.signin.SignUpUseCase
import br.com.wcsm.gymtrack.presentation.model.BaseFlowViewModel
import br.com.wcsm.gymtrack.utils.UnitCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
): BaseFlowViewModel<SignUpState, SignUpUiState>(
    initialUiState = SignUpUiState()
) {
    fun handleUserName(userName: String) = updateSignUpParams(
        _uiState.value.signUpParams.copy(userName = userName)
    )

    fun handleEmail(email: String) = updateSignUpParams(
        _uiState.value.signUpParams.copy(email = email)
    )

    fun handlePassword(password: String) = updateSignUpParams(
        _uiState.value.signUpParams.copy(password = password)
    )

    fun handleConfirmPassword(confirmPassword: String) = updateSignUpParams(
        _uiState.value.signUpParams.copy(confirmPassword = confirmPassword)
    )

    private fun updateSignUpParams(signUpParams: SignUpParams) = _uiState.update { state ->
        state.copy(signUpParams = signUpParams)
    }

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

    fun signUp() {
        handleHideError()

        viewModelScope.launch(Dispatchers.IO) {
            if(isSignUpValid()) {
                signUpUseCase(
                    userName = uiState.value.signUpParams.userName,
                    email = uiState.value.signUpParams.email,
                    password = uiState.value.signUpParams.password
                ).collect { result ->
                    when(result) {
                        is BaseResponse.Loading -> onLoadingResponse()
                        is BaseResponse.Error -> onErrorResponse(result.errorMessage)
                        is BaseResponse.Success -> onSuccessResponse {
                            emitState(SignUpState.SignUpSuccess)
                        }
                    }
                }
            }
        }
    }

    private fun isSignUpValid(): Boolean {
        val userName = uiState.value.signUpParams.userName
        val email = uiState.value.signUpParams.email
        val password = uiState.value.signUpParams.password
        val confirmPassword = uiState.value.signUpParams.confirmPassword

        val isUserNameValid = SignUpValidator.validateUserName(userName)
        val isEmailValid = SignUpValidator.validateEmail(email)
        val isPasswordValid = SignUpValidator.validatePassword(password)
        val isConfirmPasswordValid = SignUpValidator.validateConfirmPassword(
            password = password,
            confirmPassword = confirmPassword
        )

        updateSignUpParams(
            _uiState.value.signUpParams.copy(
                userNameErrorMessage = isUserNameValid.second,
                emailErrorMessage = isEmailValid.second,
                passwordErrorMessage = isPasswordValid.second,
                confirmPasswordErrorMessage = isConfirmPasswordValid.second
            )
        )

        return isUserNameValid.first
                && isEmailValid.first
                && isPasswordValid.first
                && isConfirmPasswordValid.first
    }
}