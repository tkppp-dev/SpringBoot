package com.ex.aws_springboot.web.dto

import com.ex.aws_springboot.domain.post.Post

data class PostSaveRequestDto(
    val title: String,
    val content: String,
    val author: String? = null
) {
    fun toEntity(): Post = Post(
        title = title,
        content = content,
        author = author
    )
}