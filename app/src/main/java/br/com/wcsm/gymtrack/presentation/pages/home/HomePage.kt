package br.com.wcsm.gymtrack.presentation.pages.home

import androidx.compose.runtime.Composable
import br.com.wcsm.gymtrack.presentation.atomic.templates.HomeTemplate

@Composable
fun HomePage(
) {
    HomeTemplate(
        isPageLoading = false,
        workouts = emptyList(),
        onWorkoutClick = {},
        onAddWorkoutClick = {}
    )
}