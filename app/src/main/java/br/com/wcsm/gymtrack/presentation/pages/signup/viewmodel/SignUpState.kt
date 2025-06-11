package br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel

sealed class SignUpState {
    data object SignUpSuccess : SignUpState()
}