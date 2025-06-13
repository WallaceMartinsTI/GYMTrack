package br.com.wcsm.gymtrack.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Workout(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val exercisesCount: Int = 0,
    val imageUrl: String = "",
    val date: Long = System.currentTimeMillis()
)
