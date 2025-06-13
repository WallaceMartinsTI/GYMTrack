package br.com.wcsm.gymtrack.domain.model

data class Exercise(
    val id: String,
    val workoutId: String,
    val title: String,
    val notes: String,
    val imageUrl: String
)
