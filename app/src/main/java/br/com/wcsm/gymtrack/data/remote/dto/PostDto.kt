package br.com.wcsm.gymtrack.data.remote.dto

import br.com.wcsm.gymtrack.domain.model.Post

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

fun PostDto.toPost(): Post {
    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}