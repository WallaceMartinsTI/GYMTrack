package br.com.wcsm.gymtrack.presentation.pages.home.viewmodel

sealed class HomeState {
    data object CurrentUserAvailable: HomeState()
    data object GoToAddWorkout : HomeState()
    data object ShowSignOutDialog : HomeState()
    data object SignOutUser : HomeState()
}