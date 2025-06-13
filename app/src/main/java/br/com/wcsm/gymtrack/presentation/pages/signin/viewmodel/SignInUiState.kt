package br.com.wcsm.gymtrack.presentation.pages.signin.viewmodel

data class SignInUiState(
    val isLoading: Boolean = false,
    val keepLogin: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val emailErrorMessage: String = "",
    val password: String = "",
    val passwordErrorMessage: String = ""
)
