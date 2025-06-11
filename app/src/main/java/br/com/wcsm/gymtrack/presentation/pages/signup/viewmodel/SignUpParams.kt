package br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel

data class SignUpParams(
    val userName: String = "",
    val userNameErrorMessage: String = "",
    val email: String = "",
    val emailErrorMessage: String = "",
    val password: String = "",
    val passwordErrorMessage: String = "",
    val confirmPassword: String = "",
    val confirmPasswordErrorMessage: String = ""
)
