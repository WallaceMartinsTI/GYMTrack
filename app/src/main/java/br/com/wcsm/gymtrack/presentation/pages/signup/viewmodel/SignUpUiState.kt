package br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel

data class SignUpUiState(
    val isLoading: Boolean = false,
    val keepLogin: Boolean = false,
    val error: String? = null,
    val signUpParams: SignUpParams = SignUpParams()
)
