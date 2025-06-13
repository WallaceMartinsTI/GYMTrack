package br.com.wcsm.gymtrack.presentation.pages.home.viewmodel

data class HomeUiState(
    val currentUserId: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)