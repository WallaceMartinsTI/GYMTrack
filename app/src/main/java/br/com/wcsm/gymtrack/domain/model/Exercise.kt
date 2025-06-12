package br.com.wcsm.gymtrack.domain.model

data class Exercise(
    val id: String,
    val workoutId: String,
    val title: String,
    val imageUrl: String,
    val notes: String
)
