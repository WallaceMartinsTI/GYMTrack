package br.com.wcsm.gymtrack.domain.model

import com.google.firebase.Timestamp

data class Workout(
    val id: String,
    val title: String,
    val description: String,
    val exercisesCount: Int,
    val imageUrl: String,
    val date: Timestamp = Timestamp.now()
)
