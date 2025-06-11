package br.com.wcsm.gymtrack.presentation.pages.signin.viewmodel

sealed class SignInState {
    data object GoToHome : SignInState()
    data object GoToSignUp : SignInState()
}