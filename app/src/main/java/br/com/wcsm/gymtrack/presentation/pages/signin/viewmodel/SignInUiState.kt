package br.com.wcsm.gymtrack.presentation.pages.signin.viewmodel

data class SignInUiState(
    val isLoading: Boolean = false,
    val keepLogin: Boolean = false,
    val error: String? = null,
    val email: String = "test@test.com",
    val emailErrorMessage: String = "",
    val password: String = "Test12345",
    val passwordErrorMessage: String = ""
)
